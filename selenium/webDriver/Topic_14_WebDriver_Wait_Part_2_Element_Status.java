package webDriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_WebDriver_Wait_Part_2_Element_Status {
	
	
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public void TC_1_Less_Thant() {	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
	}
	public void TC_2_Greater_Than_Or_Equal() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
	}
	@Test
	public void TC_3_Static_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		sleepInSecond(10);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}