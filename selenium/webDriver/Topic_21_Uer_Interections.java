package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Uer_Interections {
	WebDriver driver;
	Actions action;


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_Hover_Mouse_Tooltip() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		String hoveText = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText();
		
		Assert.assertEquals(hoveText, "We ask for your age only for statistical purposes.");
	}

	
	public void TC_02_Hover_Mouse_Menu() {
		
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		
		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());

	}

	
	public void TC_03_Click_And_Hold() {
		 driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		  
		 List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		 
		 Assert.assertEquals(listNumbers.size(), 12);
		 
		 action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(3)).release().perform();
		 
		 listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		 
		 Assert.assertEquals(listNumbers.size(), 4);
		 
		 
		 
		
	}
	
	public void TC_04_Click_And_Hold_Random() {
		 driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		  
		 List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		 
		 Assert.assertEquals(listNumbers.size(), 12);
		 
		 action.keyDown(Keys.CONTROL).perform();
		 
		 action.click(listNumbers.get(1)).perform();
		 action.click(listNumbers.get(4)).perform();
		 action.click(listNumbers.get(7)).perform();
		 action.click(listNumbers.get(10)).perform();
		 
		 action.keyUp(Keys.CONTROL).perform();
		 
		 listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		 
		 Assert.assertEquals(listNumbers.size(), 4);
		 
		 
	}
	@Test
	public void TC_5_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
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