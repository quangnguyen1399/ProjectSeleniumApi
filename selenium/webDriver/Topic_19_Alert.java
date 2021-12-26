package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Alert {
	WebDriver driver;
	Actions action;


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_Hover_Mouse_Tooltoip() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		String hoveText = driver.findElement(By.xpath("//div[@class='ui-tooltip-content]")).getText();
		
		Assert.assertEquals(hoveText, "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_Mouse_Menu() {
		
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		
		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());

	}

	@Test
	public void TC_03_() {
		
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}