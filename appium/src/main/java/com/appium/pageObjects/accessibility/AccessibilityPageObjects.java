package com.appium.pageObjects.accessibility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.testBase.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AccessibilityPageObjects extends TestBase {

	public AccessibilityPageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@AndroidFindBy(id = "Accessibility Node Provider")
	private WebElement accessibilityNodeProvider;

	@AndroidFindBy(id = "Accessibility Node Querying")
	private WebElement accessibilityNodeQuerying;

	@AndroidFindBy(id = "Accessibility Service")
	private WebElement accessibilityService;

	@AndroidFindBy(id = "Custom View")
	private WebElement customView;

	public WebElement getAccessibilityNodeProvider() {
		return accessibilityNodeProvider;
	}

	public WebElement getAccessibilityNodeQuerying() {
		return accessibilityNodeQuerying;
	}

	public WebElement getAccessibilityService() {
		return accessibilityService;
	}

	public WebElement getCustomView() {
		return customView;
	}

}
