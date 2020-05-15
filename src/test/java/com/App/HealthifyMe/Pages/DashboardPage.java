package com.App.HealthifyMe.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Helpers.ObjectRepoReader;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DashboardPage extends BaseDriver {

	public void ClickCalorieBurnt(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Section_CalorieBurnt"))).click();
	}

	@SuppressWarnings("unchecked")
	public void verifyfyLoginSucessful(AppiumDriver<MobileElement> driver) throws IOException {
		 Wait waitObj = new FluentWait(driver)
					. withTimeout(2, TimeUnit.MINUTES)
					. pollingEvery(1, TimeUnit.SECONDS)
					. ignoring(Exception. class);
			
			waitObj.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    try {
						return driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Button_GlobalDrawer")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				  }
				});
		
		if (driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Button_GlobalDrawer"))).isEnabled()) {
			System.out.println("Login Verified successfully");
			logger.log(Status.PASS, "Login Verified successfully");
		}
		else 
			logger.log(Status.FAIL, "Login Failed");
	}

	public void verifyDashboardScreen(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectRepoReader.getObject("Android_Button_GlobalDrawer"))));
		
		if (driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Button_GlobalDrawer"))).isEnabled()) {
			
			System.out.println("Dashboard Page Navigated Verified successfully");
			logger.log(Status.PASS, "Dashboard Page Navigated Verified successfully");
		}
		else
			logger.log(Status.FAIL, "Dashboard Page Navigated failed");
		
	}

}
