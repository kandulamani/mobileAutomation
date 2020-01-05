package com.appium.testBase;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TestBase {
	private static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static AndroidDriver<AndroidElement> driver;
	Properties OR = new Properties();
	public static AppiumDriverLocalService appiumDriverLocalService;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\appium\\reports\\" + formater.format(calendar.getTime()) + "_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public String captureScreen(String fileName, String reportDirectory) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getresult(ITestResult result) {
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "\\src\\main\\java\\com\\appium\\screenshots\\";
		String screen = captureScreen(result.getName(), reportDirectory);
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, result.getName() + " test is pass");
				test.log(Status.PASS, (Markup) test.addScreenCaptureFromPath(screen));
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP,
						result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
			} else if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.ERROR, result.getName() + " test is failed" + result.getThrowable());
				// String screen = captureScreen("");
				test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(screen));
			} else if (result.getStatus() == ITestResult.STARTED) {
				test.log(Status.INFO, result.getName() + " test is started");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\appium\\screenshots\\";
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.createTest(result.getName());
		test.log(Status.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		// extent.endTest(test);
		closeAppTest();
		appiumStopServer();
		extent.flush();
	}

	public void loadData(String fileName) {
		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\appium\\config\\" + fileName
					+ ".properties");
			FileInputStream fileInputStream = new FileInputStream(file);
			OR.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AndroidDriver<AndroidElement> appCapabilities() {
		loadData("config");
		PropertyConfigurator.configure("log4j.properties");
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			String device = OR.getProperty("DEVICE_NAME");
			if (device.contains("emulator")) {
				startEmulator();
			}
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, OR.getProperty("AUTOMATION_NAME"));
			capabilities.setCapability(MobileCapabilityType.APP,
					System.getProperty("user.dir") + "\\APKFiles\\" + OR.getProperty("APP"));
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
	// slf4j-simple
	// slf4j-api
	// commons-lang3
	// commons-io
	// commons-validator
	// taskkill /F /IM node.exe

	public AppiumDriverLocalService appiumStartServer() {
		boolean flag = checkIfServerIsRunning(4723);
		if (!flag) {
			appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
			appiumDriverLocalService.start();
		}
		return appiumDriverLocalService;
	}

	public void appiumStopServer() {
		appiumDriverLocalService.stop();
	}

	public boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (Exception e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;

	}

	public void startEmulator() {
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\startEmulator.bat");
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest(alwaysRun = true)
	public void killAllConnections() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))");
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

	public void closeAppTest() {
		driver.quit();
		log.info("Appium session is closed");
	}

}
