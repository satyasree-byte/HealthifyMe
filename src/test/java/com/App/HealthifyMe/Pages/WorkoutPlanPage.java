package com.App.HealthifyMe.Pages;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Helpers.ObjectRepoReader;
import com.App.HealthifyMe.Helpers.Scrolling;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class WorkoutPlanPage extends BaseDriver{
	
	public static ArrayList<String> WorkoutListSelected;

	public void ValidateWorkOutPlanScreen(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(ObjectRepoReader.getObject("Android_Element_WorkoutType"))));

		if (driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Element_WorkoutType"))).isDisplayed()) {
			System.out.println("WorkOut Plan Page navigation Verified successfully");
			logger.log(Status.PASS, "WorkOut Plan Page navigation Verified successfully");
		}
		else
			logger.log(Status.FAIL, "WorkOut Plan Page navigation failed");
	}

	public void verifyDateOnWorkoutPlanScreen(AppiumDriver<MobileElement> driver) throws IOException {

		
		LocalDate localDate = LocalDate.now();
		String DateExpected = Integer.toString(localDate.getDayOfMonth());
		String DayExpected = localDate.getDayOfWeek().name();

		List<MobileElement> DaysList = driver.findElements(By.id(ObjectRepoReader.getObject("Android_List_Day")));
		for (WebElement Ele : DaysList) {
			String SelectFlag = Ele.getAttribute("Selected");
			if (SelectFlag.equals("true")) {
				String DayActual = Ele.getAttribute("text");
				if (DayExpected.contains(DayActual)) {
					System.out.println("Day in WorkOut plan matching with Current Day");
					logger.log(Status.PASS, "Day in WorkOut plan matching with Current Day");
					break;
				}
				else {
					logger.log(Status.FAIL, "Day in WorkOut plan not matching with Current Day");
					break;
				}
			}
		}
		List<MobileElement> DateList = driver.findElements(By.id(ObjectRepoReader.getObject("Android_List_Date")));
		for (WebElement Ele : DateList) {
			String SelectFlag = Ele.getAttribute("Selected");
			if (SelectFlag.equals("true")) {
				String DateActual = Ele.getAttribute("text");
				if (DateActual.contains(DateExpected)) {
					System.out.println("Date in WorkOut plan matching with Current Date");
					logger.log(Status.PASS, "Date in WorkOut plan matching with Current Date");
					break;
				}
				else {
					logger.log(Status.FAIL, "Date in WorkOut plan not matching with Current Date");
				}
			}
		}

	}

	public void chooseExercisesInWorkPlanAndVerifySelection(AppiumDriver<MobileElement> driver) throws IOException, InterruptedException {
		String Title;
		WorkoutListSelected = new ArrayList<String>();
		if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Element_WorkoutType"))).getAttribute("text").equals("Warm Up")) {
			List<MobileElement> WarmUpList = driver.findElements(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")));
			for (WebElement Ele : WarmUpList) {
				for(int i = 1; i<=WarmUpList.size(); i++) {
					Title = driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.TextView[contains(@resource-id,'tv_title')]")).getText();
					WorkoutListSelected.add(Title);
					//Click plus button
					driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).click();
					
					if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).isEnabled()) {
						
						System.out.println("Warm up exercise "+Title+" Selected Sucessfully");
						logger.log(Status.PASS, "Warm up exercise "+Title+" Selected Sucessfully");
					}
					else
						logger.log(Status.FAIL,"Warm up exercise "+Title+" Selection failed");
					if(i==2)
						break;
				}
				break;
			}	
		}
		
		Scrolling scroll = new Scrolling();
		scroll.scrollTillMobileView("Workout");
		Thread.sleep(2000);
		
//		TouchAction touchAction = new TouchAction(driver);
//		touchAction.press(PointOption.point(345, 960)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)))
//				.moveTo(PointOption.point(345, 340)).release().perform();
		

		if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Element_WorkoutType"))).getAttribute("text").equals("Workout")) {
			List<MobileElement> WorkoutList = driver.findElements(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")));
			for (WebElement Ele : WorkoutList) {
				for(int i = 1; i<=WorkoutList.size(); i++) {
					Title = driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.TextView[contains(@resource-id,'tv_title')]")).getText();
					WorkoutListSelected.add(Title);
					//Click plus button
					driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).click();
					
					if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).isEnabled()) {
						
						System.out.println("Work out exercise "+Title+" Selected Sucessfully");
						logger.log(Status.PASS, "Work out exercise "+Title+" Selected Sucessfully");
					}
					else
						logger.log(Status.FAIL,"Work out exercise "+Title+" Selection failed");
					
					if(i==2)
						break;
				}
				break;
			}	
		}
		
		
		scroll.scrollTillMobileView("Cool Down");
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Element_WorkoutType"))).getAttribute("text").equals("Cool Down")) {
			List<MobileElement> CoolDownList = driver.findElements(By.xpath(ObjectRepoReader.getObject("Android_List_AddWorkout")));
			for (WebElement Ele : CoolDownList) {
				for(int i = 1; i<=CoolDownList.size(); i++) {
					Title = driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.TextView[contains(@resource-id,'tv_title')]")).getText();
					WorkoutListSelected.add(Title);
					//Click plus button
					driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).click();
					
					if(driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_WorkoutTitle")+
							"["+i+"]/android.widget.FrameLayout")).isEnabled()) {
						
						System.out.println("Cool Down exercise "+Title+" Selected Sucessfully");
						logger.log(Status.PASS, "Cool Down exercise "+Title+" Selected Sucessfully");
					}
					else
						logger.log(Status.FAIL,"Cool Down exercise "+Title+" Selection failed");
					
					if(i==2)
						break;
				}
				break;
			}	
		}
		System.out.print("Workouts Selected in WorkoutPlan :"+ WorkoutListSelected);
	}

	
	public void clickBack(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_Button_Back"))).click();
		
	}

	
	

}
