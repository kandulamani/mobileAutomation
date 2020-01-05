package com.appium.tasks;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

public class MobileGestures extends TestBase_Copy {
	private static final Logger log = Logger.getLogger(MobileGestures.class.getName());

	@Test
	public void sampleTest1() throws InterruptedException {
		log.info("Tap operation is started");
		appCapabilities("real");
		WebElement views = driver.findElementByXPath("//android.widget.TextView[@text='Views']");
		clickOperation(views);
		WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		tapOperation(expandList);
		WebElement customAdapter = driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']");
		clickOperation(customAdapter);
		WebElement peopleNmaes = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		longPressOperation(peopleNmaes, 2);
		WebElement resultText = driver.findElementById("android:id/title");
		elementIsDisplayed(resultText);
		closeAppTest();
		log.info("Tap operation is Closed");
	}

	@Test
	public void swipeOperations() throws InterruptedException {
		log.info("Swipe operation is started");
		appCapabilities("emulator");
		Thread.sleep(3000);
		scrollableOperations("");
		WebElement views = driver.findElementByXPath("//android.widget.TextView[@text='Views']");
		clickOperation(views);
		WebElement dateWidgets = driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']");
		clickOperation(dateWidgets);
		WebElement inlines = driver.findElementByXPath("//android.widget.TextView[@text='2. Inline']");
		clickOperation(inlines);
		WebElement digit9 = driver.findElementByXPath("//*[@content-desc='9']");
		clickOperation(digit9);
		WebElement digit15 = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement digit45 = driver.findElementByXPath("//*[@content-desc='45']");
		swippingOperation(digit15, 2, digit45);
		log.info("Swipe operation is Completed");
	}
}
