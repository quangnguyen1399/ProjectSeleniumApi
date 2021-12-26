package webDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Frame_Iframe {
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}
	
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automationfc");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		sleepInSecond(2);
	}
	public void TC_02_Iframe() {
		//Parent
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Switch vao dung cai iframe cua fb
		driver.switchTo().frame(driver.findElement(By.cssSelector(".fb_iframe_widget iframe")));
		
		// anm trong iframe cua fb
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Automation FC']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText(), "2,921 likes");
		
		// Switch to parent page
		driver.switchTo().defaultContent();
		
		// Nm trong parent page
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
		
		//Switch vao iframe cua gooogle docs
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='post-content']//iframe[contains(@src, 'docs.google.com')]")));
		
		// Nam trong iframe cua google docs
		Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
		
		
	}

	public void TC_03_Window_Tab() {
		
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		String parentWindowID = driver.getWindowHandle();
		
		
		// Click vao Elearning link -> chuyen qua tab moi
		driver.findElement(By.xpath("//a[text()='Elearning']")).click();
		// Switch qua tab nay 
		switchToWindowByID(parentWindowID);
		
		// kiem tra casi url/title cua trang moi do.
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");
		
	}
	
	@Test
	public void TC_04_Window_Tab() {
		
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Elearning']")).click();
		
		switchToWindowByTitle("Automation FC");
		
		
		Assert.assertEquals(driver.getTitle(), "Automation FC");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.vn/");
		
		// Quay ve trang parent
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		sleepInSecond(3);

		Assert.assertEquals(driver.getTitle(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");

		//Switch vao iframe cua fb truoc
		driver.switchTo().frame(driver.findElement(By.cssSelector(".fb_iframe_widget iframe")));
		
		
		// Click vao Automation FC text trong FanPage (FB)
		driver.findElement(By.xpath("//a[@title='Automation FC']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Automation FC - Home | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://m.facebook.com/automationfc/?_rdr");
		
		// quay ve trang parent
		switchToWindowByTitle("[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream) – Automation FC Blog");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.com/2020/02/18/training-online-automation-testing/");
		sleepInSecond(3);
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));
		
		driver.findElement(By.xpath("//a[text()='[Online 23] - Topic 01 (Introduction about Course and Target)']")).click();
		
		switchToWindowByTitle("[Online 23] - Topic 01 (Introduction about Course and Target) - YouTube");
		sleepInSecond(3);
		
//		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Selenium Grid");
//		sleepInSecond(3);
		
		Assert.assertTrue(closeAllWindowsWithoutParent(parentID));
		sleepInSecond(3);
	}
	
	// Switch bằng ID (chỉ đúng với tyruowngf hợp có duy nhất 2 tab / windows)
	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID đang có (tab / window)
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	
	
	
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String windowID : allWindows) {
			driver.switchTo().window(windowID);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows) {
			if(!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
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