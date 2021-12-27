package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");


	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// Verify login button is disable
		Assert.assertFalse(isElementEnabled(loginButton));
		
		driver.findElement(By.id("login_username")).sendKeys("quang@mail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(5);
		// Verify login button is enabled
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// Verify login button is disable
		Assert.assertFalse(isElementEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// Verify login button is disabled
		Assert.assertFalse(isElementEnabled(loginButton));
		
		// Remove disable attribute of login button
		removeDisabledAtttributeByJS(loginButton);
		sleepInSecond(3);
		
		// Verify login button is enabled
		Assert.assertTrue(isElementEnabled(loginButton));
		
		clickByJS(loginButton);
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		sleepInSecond(3);
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {
		
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enable:" + by);
			return true;
		} else {
			System.out.println("Element is disable:" + by);
			return false;
		}
	}
	
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void removeDisabledAtttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
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