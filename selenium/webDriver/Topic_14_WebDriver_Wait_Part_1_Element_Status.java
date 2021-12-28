package webDriver;

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

public class Topic_14_WebDriver_Wait_Part_1_Element_Status {
	
	
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

	}
	@Test
	public void TC_01_Element_Visible_Displayed() {
		
		// Điều kiện để 1 element display/ visible:
		// + Element có trên UI (Bắt buộc)
		// + Element có trong DOM (Bắt buộc)
		
		// Wait My Account link at footer is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		
		// Verify My Account link is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).isDisplayed());
		
		// Click to Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Wait Email error link is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
		
		// Verify My Account link is displayed
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
				
	}

	@Test
	public void TC_02_Element_Invisible_Undisplayed_In_DOM() {
		// Điều kiện để 1 element undisplay/ invisiable:
		// + Element ko có trên UI (bắt buộc)
		// + Element có trong DOM (case 1)
		driver.navigate().refresh();
		
		// Wait My Account link at header is displayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));

		
		
	}
	@Test
	public void TC_02_Element_Invisible_Undisplayed_Not_In_DOM() {
		// Điều kiện để 1 element undisplay/ invisiable:
		// + Element ko có trên UI (bắt buộc)
		// + Element ko có trong DOM (case 2)
		driver.navigate().refresh();

		
		// Wait My Account link at header is displayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
	
	}

	@Test
	public void TC_03_Element_Presence() {
		// Điều kiện để 1 element presence:
		// + Element ko có trên UI (Case 01)
		// + Element có trên UI (Case 02)
		// + Element có trong DOM (Bắt buộc)
		driver.navigate().refresh();

		// Wait My Account link at header is presence - Element ko có trên UI (Case 01)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));

		// Wait My Account link at footer is presence - Element có trên UI (Case 02)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
	}
	@Test
	public void TC_04_Element_Stalences() {
		driver.navigate().refresh();

		// Điều kiện để 1 element stalence:
		// + Element ko có trên UI (bắt buộc)
		// + Element có trong DOM (bắt buộc)
		
		// 1 - tìm element lúc nó đang xuất hiện lưu vào 1 biến
		driver.findElement(By.xpath("//button[@id=send2]")).click();
		WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		
		driver.navigate().refresh();

		//2 - element ko còn xuất hiện nữa mình kiểm tra (chờ) nó stalence
		explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}