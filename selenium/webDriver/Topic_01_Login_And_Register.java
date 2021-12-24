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

public class Topic_01_Login_And_Register {
	
	
	WebDriver driver;
	
	//Login

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
	
	//Register
	
	public void TC_01_Register_With_Emptry_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}
	public void TC_02_Register_With_Invalid_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("quang nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("123789@123.123@");
		driver.findElement(By.id("txtCEmail")).sendKeys("123789@123.123@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0946123456");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("quang nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("quangne123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123789@123.123@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0946123456");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
		
	}
	@Test
	public void TC_03_Register_With_Invalid_Phonenumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.id("txtFirstname")).sendKeys("quang nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("quangne123@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("quangne123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("094612345699");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("123456");
	
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		sleepInSecond(3);
		
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