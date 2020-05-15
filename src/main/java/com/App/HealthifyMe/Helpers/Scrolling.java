package com.App.HealthifyMe.Helpers;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import com.App.HealthifyMe.Base.BaseDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Scrolling extends BaseDriver{
	
	public void ScrollDown() {
		
	Dimension dimension = driver.manage().window().getSize();
		
		Double ScrollHeightStart = dimension.getHeight() * 0.5;
		int ScrollStart = ScrollHeightStart.intValue();
		
		Double ScrollHeightEnd = dimension.getHeight() * 0.2;
		int ScrollEnd = ScrollHeightEnd.intValue();
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(0, ScrollStart)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(0, ScrollEnd)).release().perform();
		
		
	}
	
	public MobileElement getItemView(String View) {
		
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+View+"']"));
	}

	public List<MobileElement> MobileViewList (String View){
		return driver.findElements(By.xpath("//android.widget.TextView[@text='"+View+"']"));
	}

	
	public void scrollTillMobileView(String View) {
		
		while(MobileViewList(View).size()==0) {
			ScrollDown();
		}
		if(MobileViewList(View).size() > 0) {
			System.out.println("Element visible now...scrolling till 30% of page");
			
			Dimension dimen = driver.manage().window().getSize();
			Point eledimen = driver.findElement(By.xpath("//android.widget.TextView[@text='"+View+"']")).getLocation();
			Double ScrollHeightStart = dimen.getHeight() * 0.65;
			int ScrollStart = ScrollHeightStart.intValue();
			
			Double ScrollHeightEnd = dimen.getHeight() * 0.26;
			int ScrollEnd = ScrollHeightEnd.intValue();
			
			TouchAction touchAction = new TouchAction(driver);
			touchAction.press(PointOption.point(eledimen.getX(),eledimen.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					.moveTo(PointOption.point(0, ScrollEnd)).release().perform();
		}
		
	}
	
}
