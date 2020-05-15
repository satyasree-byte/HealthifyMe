package com.App.HealthifyMe.Suite;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.App.HealthifyMe.Base.BaseDriver;
import com.App.HealthifyMe.Pages.DashboardPage;
import com.App.HealthifyMe.Pages.LaunchPage;
import com.App.HealthifyMe.Pages.LoginPage;
import com.App.HealthifyMe.Pages.WorkoutLogsPage;
import com.App.HealthifyMe.Pages.WorkoutPlanPage;

import com.App.HealthifyMe.Listeners.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class TestSuite extends BaseDriver {

	LaunchPage Launch = new LaunchPage();
	LoginPage Login = new LoginPage();
	DashboardPage dashbd = new DashboardPage();
	WorkoutLogsPage WorkLog = new WorkoutLogsPage();
	WorkoutPlanPage WorkPlan = new WorkoutPlanPage();

	@Test(enabled = true)
	public void Healthify_WorkoutOperation() throws Exception {

		Launch.VerifyLaunchSucessful(driver);

		// Step - 1
		Launch.clickOnAlreadyMemberSignin(driver);
		Login.verifyLoginPageScreen(driver);

		// Step - 2
		Login.clickOnEmailSignIn(driver);
		Login.enterEmail(driver);
		Login.enterPassword(driver);
		Login.clickOnLogin(driver);
		dashbd.verifyfyLoginSucessful(driver);

		// Step - 3
		dashbd.ClickCalorieBurnt(driver);
		WorkLog.verifyeWorkOutLogsScreen(driver);

		// Step - 4
		WorkLog.clickViewAll(driver);
		WorkPlan.ValidateWorkOutPlanScreen(driver);

		// Step - 5
		WorkPlan.verifyDateOnWorkoutPlanScreen(driver);

		// Step - 6
		WorkPlan.chooseExercisesInWorkPlanAndVerifySelection(driver);

		// Step = 7
		WorkPlan.clickBack(driver);
		WorkLog.verifyeWorkOutLogsScreen(driver);
		WorkLog.clickViewMore(driver);

		// Step - 8
		WorkLog.validateExercisesFromWorkplanToWorkoutScreens(driver);

		// Step - 9
		WorkPlan.clickBack(driver);
		dashbd.verifyDashboardScreen(driver);

	}

}
