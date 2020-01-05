package com.appium.testscripts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appium.pageObjects.HomePageObjects;
import com.appium.testBase.TestBase;

public class TC001_HomePageTest extends TestBase {

	@BeforeMethod
	public void appLaunch() {
		appiumStartServer();
		appCapabilities();
	}

	@Test
	public void launchAppium() {

		HomePageObjects homePageObjects = new HomePageObjects(driver);
		homePageObjects.getPreference().click();
		//homePageObjects.getAnimation().click();
		System.out.println("Test completed");
	}

	@Test
	public void sampleOperation() {
		HomePageObjects homePageObjects = new HomePageObjects(driver);
		homePageObjects.getAnimation().click();
		System.out.println("Test completed");
	}

}
