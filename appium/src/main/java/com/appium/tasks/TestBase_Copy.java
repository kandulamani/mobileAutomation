package com.appium.tasks;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase_Copy {
	private static final Logger log = Logger.getLogger(TestBase_Copy.class.getName());
	public AndroidDriver<AndroidElement> driver = null;

	public AndroidDriver<AndroidElement> appCapabilities(String deviceName) {
		File file = new File("APKFiles");
		File app = new File(file, "General-Store.apk");
		try {
			PropertyConfigurator.configure("log4j.properties");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (deviceName.equalsIgnoreCase("emulator")) {
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			} else if (deviceName.equalsIgnoreCase("real")) {
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
			}
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, capabilities);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Appium session is created");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to create Appium session");
		}
		return driver;
	}

	public void tapOperation(WebElement element) {
		try {
			TouchAction action = new TouchAction(driver);
			action.tap(tapOptions().withElement(element(element))).perform();
			log.info("Tap Operation is performed on webElement :" + element);
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Tap Operation is not performed on webElement :" + element);
			e.printStackTrace();
		}
	}

	public void longPressOperation(WebElement element, int seconds) {
		try {
			TouchAction action = new TouchAction(driver);
			action.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(seconds)))
					.release().perform();
			log.info("Long Press Operation is performed on webElement :" + element);
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Long Press Operation is not performed on webElement :" + element);
			e.printStackTrace();
		}
	}

	public void swippingOperation(WebElement source, int seconds, WebElement target) {
		try {
			TouchAction action = new TouchAction(driver);
			action.longPress(longPressOptions().withElement(element(source)).withDuration(ofSeconds(seconds)))
					.moveTo(element(target)).release().perform();
			log.info("Swipping Operation is performed on webElement :" + source);
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Swipping Operation is not performed on webElement :" + source);
			e.printStackTrace();
		}
	}

	public void scrollableOperations(String text) {
		try {
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");
			log.info("Scroll Operation is performed on webElement :");
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Scroll Operation is not performed on webElement :");
			e.printStackTrace();
		}
	}

	public void clickOperation(WebElement element) {
		try {
			element.click();
			log.info("Click Operation is performed on webElement :" + element);
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Click Operation is not performed on webElement :" + element);
			e.printStackTrace();
		}
	}

	public void sendKeysOperation(WebElement element, String text) {
		try {
			element.sendKeys(text);
			log.info("Send Keys Operation is performed on webElement :" + element);
		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			log.info("Send Keys Operation is not performed on webElement :" + element);
			e.printStackTrace();
		}
	}

	public void elementIsDisplayed(WebElement element) {
		boolean elementStatus = element.isDisplayed();
		log.info("element is Displayed and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	public void elementIsEnabled(WebElement element) {
		boolean elementStatus = element.isEnabled();
		log.info("element is Enabled and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	public void elementIsSelected(WebElement element) {
		element.click();
		boolean elementStatus = element.isSelected();
		log.info("element is Selected and Status is : " + elementStatus);
		Assert.assertTrue(elementStatus);
	}

	//@AfterClass
	public void closeAppTest() {
		driver.quit();
		log.info("Appium session is closed");
	}

}
