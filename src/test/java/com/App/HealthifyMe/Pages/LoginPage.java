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
import org.testng.annotations.Listeners;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Helpers.ObjectRepoReader;
import com.App.HealthifyMe.Helpers.TestDataReader;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/*
All the required elements for the login page 
Fetching elements from the OR - properties file
*/

public class LoginPage extends BaseDriver {

	public void clickOnEmailSignIn(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Android_Link_SignInWith_Email"))));
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_SignInWith_Email"))).click();
		
	}

	public void clickOnSignin(AppiumDriver<MobileElement> driver) throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login"))));
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_AlreadyMember_Login"))).click();
	}

	
	@SuppressWarnings("unchecked")
	public void enterEmail(AppiumDriver<MobileElement> driver) throws Exception {
		String Email = TestDataReader.getValue("Email");
		
		 Wait waitObj = new FluentWait(driver)
					. withTimeout(2, TimeUnit.MINUTES)
					. pollingEvery(1, TimeUnit.SECONDS)
					. ignoring(Exception. class);
			
			waitObj.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    try {
						return driver.findElement(By.id(ObjectRepoReader.getObject("Android_TextBox_Email")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				  }
				});
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_TextBox_Email"))).click();
		Thread.sleep(1000);
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_TextBox_Email"))).setValue(Email);
	}
	
	

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void enterPassword(AppiumDriver<MobileElement> driver) throws Exception {
		 String password = TestDataReader.getValue("Password");
		 Wait waitObj = new FluentWait(driver)
					. withTimeout(2, TimeUnit.MINUTES)
					. pollingEvery(1, TimeUnit.SECONDS)
					. ignoring(Exception. class);
			
			waitObj.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    try {
						return driver.findElement(By.id(ObjectRepoReader.getObject("Android_TextBox_Password")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				  }
				});
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_TextBox_Password"))).sendKeys(password);
	}

	
	public void clickOnLogin(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Button_Login"))).click();

	}


	public void verifyLoginPageScreen(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Android_Link_SignInWith_Email"))));
		
		if (driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_SignInWith_Email"))).isDisplayed()) {
			System.out.println("Login Page navigation Verified successfully");
			logger.log(Status.PASS, "Login Page navigation Verified successfully");
		}
		else
			logger.log(Status.FAIL, "Login Page navigation Failed");
	}

	
}
