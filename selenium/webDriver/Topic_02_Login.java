package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Login {
	
	
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");

		
	}
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("1234@123.123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	
	public void TC_03_Login_With_Password_Less_6characters() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	public void TC_04_Login_With_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456999");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
		
	}
	@Test
	public void TC_05_Create_New_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("quang");
		driver.findElement(By.id("middlename")).sendKeys("nhat");
		driver.findElement(By.id("lastname")).sendKeys("nguyen");
		driver.findElement(By.id("email_address")).sendKeys("quangne" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My Dashboard']")), "My Dashboard");
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Hello, quang nhat nguyen!']")), "Hello, quang nhat nguyen!");
		

		
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
				
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