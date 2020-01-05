package com.appium.tasks;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

public class SampleExample2 extends TestBase_Copy {

	@Test
	public void androdUIAutomator() {
		try {
			appCapabilities("");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// driver.findElementByAndroidUIAutomator("attribute('value')");
			//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().property(value)"));
			driver.findElement(MobileBy.AndroidUIAutomator("text(\"Media\")")).click();
			Thread.sleep(4000);
			driver.findElement(MobileBy.AndroidUIAutomator("text(\"AudioFx\")")).click();
			Thread.sleep(4000);
			//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)"));

		} catch (Exception e) {
			System.out.println("Message :" + e.getMessage());
			System.out.println("Cause:" + e.getCause());
			e.printStackTrace();
		}
	}

}
