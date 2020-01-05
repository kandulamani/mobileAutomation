package com.appium.ecommerce;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.appium.tasks.TestBase_Copy;

import io.appium.java_client.android.AndroidElement;

public class LoginForm extends TestBase_Copy {

	private static final Logger log = Logger.getLogger(LoginForm.class.getName());

	@Test
	public void loginTest() throws InterruptedException {
		log.info("LoginForm Verification test is Started");
		appCapabilities("emulator");
		WebElement country = driver.findElementById("com.androidsample.generalstore:id/spinnerCountry");
		WebElement name = driver.findElementById("com.androidsample.generalstore:id/nameField");
		sendKeysOperation(name, "user1");
		driver.getKeyboard().sendKeys(Keys.RETURN);
		WebElement femaleRadio = driver.findElementById("com.androidsample.generalstore:id/radioFemale");
		clickOperation(femaleRadio);
		clickOperation(country);
		Thread.sleep(3000);
		scrollableOperations("Aruba");
		Thread.sleep(3000);
		WebElement armenia = driver.findElementByXPath("//*[@text='Armenia']");
		clickOperation(armenia);
		WebElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
		clickOperation(shopButton);
		
		 List<AndroidElement> allProducts = driver.findElementsById("com.androidsample.generalstore:id/productName");
		System.out.println(allProducts.size());
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productName\")).scrollIntoView(new UiSelector().text(\"Air Jordan 9 Retro\"));");
		log.info("LoginForm Verification test is Finished");
	}

	//@Test
	public void invalidLoginOperations() throws InterruptedException {
		log.info("LoginForm Verification test is Started");
		appCapabilities("emulator");
		WebElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
		clickOperation(shopButton);
		String message = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(message);
		Assert.assertEquals("Please enter your name", message);
	}
}
