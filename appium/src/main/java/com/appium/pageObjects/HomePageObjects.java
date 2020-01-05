package com.appium.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.testBase.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePageObjects extends TestBase {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility']")
	private WebElement accessibility;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Animation']")
	private WebElement animation;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App']")
	private WebElement app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Content']")
	private WebElement content;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Graphics']")
	private WebElement graphics;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Media']")
	private WebElement media;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NFC']")
	private WebElement nFC;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='OS']")
	private WebElement oS;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	private WebElement preference;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Text']")
	private WebElement text;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Views']")
	private WebElement views;

	public HomePageObjects(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public WebElement getAccessibility() {
		return accessibility;
	}

	public WebElement getAnimation() {
		return animation;
	}

	public WebElement getApp() {
		return app;
	}

	public WebElement getContent() {
		return content;
	}

	public WebElement getGraphics() {
		return graphics;
	}

	public WebElement getMedia() {
		return media;
	}

	public WebElement getnFC() {
		return nFC;
	}

	public WebElement getoS() {
		return oS;
	}

	public WebElement getPreference() {
		return preference;
	}

	public WebElement getText() {
		return text;
	}

	public WebElement getViews() {
		return views;
	}

}
