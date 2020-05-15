package com.App.HealthifyMe.Base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.App.HealthifyMe.Helpers.TestDataReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Listeners(com.App.HealthifyMe.Listeners.ScreenshotListener.class)
public class BaseDriver {

	public static AndroidDriver<MobileElement> driver;
	public static ExtentTest logger;
	public static List<List<Object>> performanceData;
	@BeforeTest
	public void Base() {
		
		@SuppressWarnings("deprecation")
		ExtentHtmlReporter Reporter = new ExtentHtmlReporter("extentReport.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(Reporter);
		logger = extent.createTest("Test_HealthifyMe","Tests Basic Flow Of HealthigyMe");
		

		try {
			logger.info("================Browser Session Started===============");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability("no", true);
			capabilities.setCapability("newCommandTimeout", 100000);
			capabilities.setCapability("no-reset", "true");
			capabilities.setCapability("full-reset", "false");
			
			// device details
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("UDID", "emulator-5554");
			capabilities.setCapability("platformVersion", "10");
			capabilities.setCapability("platformName", "Android");
			
			// Application details
			String apkpath = "C:\\Users\\satyasree\\Downloads\\com.healthifyme.basic-13.5.apk";
			File app = new File(apkpath);
			capabilities.setCapability("app", app.getAbsolutePath());

			capabilities.setCapability("appPackage", "com.healthifyme.basic");
			capabilities.setCapability("appActivity", "com.healthifyme.basic.activities.NewLoginSignupActivity");
			
			URL url = new URL(TestDataReader.getValue("BasePortURL"));
			driver = new AndroidDriver<MobileElement>(url, capabilities);
//			List<String> performanceTypes = driver.getSupportedPerformanceDataTypes();
//			System.out.println(performanceTypes);
			
			 performanceData = driver.getPerformanceData("com.healthifyme.basic", "memoryinfo", 5);
			System.out.println(performanceData);
			
			
//			String workout = "Workout";
//			String selectorString = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
//					+ ".scrollIntoView(new UiSelector().text("+ workout +"))");
//			 driver.findElement(MobileBy.AndroidUIAutomator(selectorString));
			
			
			logger.info("===============Application Started================");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			

		} catch (Exception e) {
			logger.log(Status.FAIL, "Launch Failed");
			System.out.println("Cause is" + e.getCause());
			System.out.println("Message is" + e.getMessage());
			e.printStackTrace();
		}

	}

//	@AfterTest
//	public void closeDriver() {
//		driver.close();
//		logger.info("================Application Ended=====================");
//
//	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		logger.info("==================Browser Session End=================");

	}

// Works as per listener and takes screenshots when failed.
	public void failed(String TestName) {

		try {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
			FileUtils.copyFile(srcfile, new File("C:\\Users\\satyasree\\Cognizant_Telstra\\src\\test\\Screenshots\\"
					+TestName+"_"+"test_failure.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
