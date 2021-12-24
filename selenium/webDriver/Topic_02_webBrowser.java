package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_webBrowser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\01-Software\\Eclipse-java\\workspace\\seleniumApi_TestNG\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_Url() {
		
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/index.php/");
		String pagesource;
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		pagesource = driver.getPageSource();
		
		Assert.assertTrue(pagesource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		pagesource = driver.getPageSource();
		
		Assert.assertTrue(pagesource.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}