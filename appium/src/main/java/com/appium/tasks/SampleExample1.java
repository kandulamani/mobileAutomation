package com.appium.tasks;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SampleExample1 extends TestBase_Copy {

	@Test
	public void baseOperation() {
		appCapabilities("");
		try {

			driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"))
					.click();
			Thread.sleep(3000);
			// driver.findElementById("android:id/checkbox").click();
			driver.findElement(By.xpath("//android.widget.CheckBox[@package='io.appium.android.apis']")).click();
			Thread.sleep(4000);
			driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
			Thread.sleep(4000);
			driver.findElementByClassName("android.widget.EditText").sendKeys("user1");
			Thread.sleep(4000);
			//driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
			driver.findElementsByClassName("android.widget.Button").get(1).click();
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
