package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Default_Dropdown {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("quang");
		driver.findElement(By.id("LastName")).sendKeys("nguyen");
		
		//date
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getOptions().size(), 32);
		
		//month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("March");
		Assert.assertEquals(select.getOptions().size(), 13);
		
		//year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1999");
		Assert.assertEquals(select.getOptions().size(), 112);
				
				
		
		driver.findElement(By.id("Email")).sendKeys("quangnguyen" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.id("Company")).sendKeys("Bac Lieu");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.name("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		driver.findElement(By.className("ico-account")).click(); 	
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "March");
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1999");
		
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {
		
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}