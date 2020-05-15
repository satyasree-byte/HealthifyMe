package com.App.HealthifyMe.Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Helpers.ObjectRepoReader;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WorkoutLogsPage extends BaseDriver {
	public static ArrayList<String> WorkLogListDisplayed = null;

	public void verifyeWorkOutLogsScreen(AppiumDriver<MobileElement> driver) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(ObjectRepoReader.getObject("Android_Link_ViewAll"))));

		if (driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_ViewAll"))).isDisplayed()) {
			System.out.println("WorkOut Logs Page navigation Verified successfully");
			
			logger.log(Status.PASS, "WorkOut Logs Page navigation Verified successfully");
		}
		else
			logger.log(Status.FAIL, "WorkOut Logs Page Navigation failed");
	}

	
	public void clickViewAll(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Link_ViewAll"))).click();	
	}


	public void clickViewMore(AppiumDriver<MobileElement> driver) throws IOException {
		driver.findElement(By.id(ObjectRepoReader.getObject("Android_Button_ViewMoreOrLess"))).click();
	}


	public void validateExercisesFromWorkplanToWorkoutScreens(AppiumDriver<MobileElement> driver) throws IOException {
		String Title;
		
		List<MobileElement> WorkLogList = driver.findElements(By.xpath(ObjectRepoReader.getObject("Android_List_SelectedActivities")));
		for(int i = 1 ; i<=WorkLogList.size(); i++) {
			Title = driver.findElement(By.xpath(ObjectRepoReader.getObject("Android_List_SelectedActivities")+
					"["+i+"]/android.widget.TextView")).getAttribute("text");
			WorkLogListDisplayed = new ArrayList<String>();
			WorkLogListDisplayed.add(Title);	
		}
		
		if(WorkoutPlanPage.WorkoutListSelected.containsAll(WorkLogListDisplayed)) {
			System.out.println("All Exercises selected from WorkOut page are validated in Work log page Sucessfully");
			logger.log(Status.PASS, "All Exercises selected from WorkOut page are validated in Work log page Sucessfully");
		}
		else
			logger.log(Status.FAIL, "All Exercises selected from WorkOut page are not validated in Work log page");
	}
}