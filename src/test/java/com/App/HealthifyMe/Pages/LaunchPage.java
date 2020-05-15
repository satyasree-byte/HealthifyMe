package com.App.HealthifyMe.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Helpers.ObjectRepoReader;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LaunchPage extends BaseDriver{

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void VerifyLaunchSucessful(AppiumDriver<MobileElement> driver) throws IOException {
		Wait waitObj = new FluentWait(driver)
				. withTimeout(60, TimeUnit.SECONDS)
				. pollingEvery(2, TimeUnit.SECONDS)
				. ignoring(Exception. class);
		
		waitObj.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    try {
					return driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			  }
			});
		
		if (driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login"))).isDisplayed()) {
			System.out.println("App Launch Verified successfully");
			logger.log(Status.PASS, "App Launch Verified successfully");
		}
		else
			logger.log(Status.FAIL, "App Launch failed");

	}


	public void clickOnAlreadyMemberSignin(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login"))));
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login"))).click();
		
	}

}
