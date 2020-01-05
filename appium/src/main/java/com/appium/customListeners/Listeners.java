package com.appium.customListeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.appium.testBase.TestBase;

public class Listeners extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		Reporter.log("Test is finished:" + arg0.getName());

	}// com.test.automation.uiAutomation.customListner.Listener

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "\\src\\main\\java\\com\\appium\\failedScreenshots\\";
		if (!result.isSuccess()) {
			captureScreen(testName, reportDirectory);
		}

	}

	public void onTestSkipped(ITestResult arg0) {
		Reporter.log("Test is skipped:" + arg0.getMethod().getMethodName());

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "\\src\\main\\java\\com\\appium\\successScreenshots\\";
		if (result.isSuccess()) {
			captureScreen(testName, reportDirectory);
		}
	}

}
