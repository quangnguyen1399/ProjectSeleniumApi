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

public class Topic_02_Web_Browser_Element {
	WebDriver driver;
	String appUrl = "https://facebook.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_() {
		//mở 1 cái app ra
		driver.get(appUrl); //*******
		
		// lấy ra Url của trang hiện tại 
		driver.getCurrentUrl(); //*******
		
		// lấy tất cả mã code của trang hiện tại
		driver.getPageSource();
		
		// lấy ra title của trang hiện tại
		driver.getTitle(); //*******
		
		// window/ tab : id > 1
		driver.getWindowHandle(); //*******
		
		// all ID
		driver.getWindowHandles(); //*******
		
		// đóng trình duyệt
		driver.close();
		
		//đóng trình duyệt
		driver.quit(); //*******
		
		//Tìm 1 element
		driver.findElement(By.xpath(appUrl)); //*******
		
		//Tìm nhiều  element
		driver.findElements(By.xpath(appUrl)); //*******
		
		//Thời gian chờ cho vieeck tìm kiếm element (WebdriverWait)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Thời gian chờ cho page được tải xong (WebdriverWait)
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Thời gian chờ cho đoạn code JS đc thực thi xong (Javascript Exectutor)
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		// Full hết window 
		driver.manage().window().maximize(); //*******
		
		// Back về trang trước 
		driver.navigate().back();
		
		// chuyển tới trang trước 
		driver.navigate().forward();
		
		// tải lại trang (F5)
		driver.navigate().refresh();
		
		// mở ra 1 cái Url
		driver.navigate().to(appUrl);
		
		driver.switchTo().alert(); //*******
		driver.switchTo().frame("facebook"); //*******
		driver.switchTo().window("64654-56454-6456-32456-654");  //*******
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}