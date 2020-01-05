package com.appium.tasks;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DeviceSelection {
	public AndroidDriver<AndroidElement> driver = null;

	// @Test
	public void deviceSelectionOperation() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
			// "emulator-5554");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
			capabilities.setCapability(MobileCapabilityType.APP,
					System.getProperty("user.dir") + "\\APKFiles\\ApiDemos-debug.apk");
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, capabilities);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void browserTest() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android
		// device");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, capabilities);

		driver.get("https://m.facebook.com/");
		driver.findElement(By.name("email")).sendKeys("user1");
		driver.findElement(By.name("pass")).sendKeys("user2");
		driver.findElement(By.name("login")).click();
	}

}
