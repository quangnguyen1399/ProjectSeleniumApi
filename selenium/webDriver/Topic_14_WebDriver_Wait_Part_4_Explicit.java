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

public class Topic_14_WebDriver_Wait_Part_4_Explicit {
	
	
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectFolder = System.getProperty("user.dir");
	String fileName = "File.pdf";
	String filePath = projectFolder + "\\uploadFile\\" + fileName;
	
	By buttonStart = By.xpath("//button[text()='Start']");
	By loading = By.xpath("//div[@id='loading']");
	By helloText = By.xpath("//div[@id='finish']/h4");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	public void TC_1_Invisible_2s() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 2);
		
		driver.findElement(buttonStart).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");	
	}
	
	public void TC_2_Invisible_10s() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.findElement(buttonStart).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");	
	}
	
	public void TC_3_Visible_2s() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 2);
		
		driver.findElement(buttonStart).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");	
	}

	public void TC_4_Visible_10s() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.findElement(buttonStart).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		
		Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");	
	}

	public void TC_5_Select_Date() {
		
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait = new WebDriverWait(driver, 10);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
				
		//driver.findElement(By.xpath("//a[text()='14']/parent::td")).click();
		// Chờ cho ngày đc chọn có thể đc click hay ko
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='14']/parent::td"))).click();
		
		// CHờ cho Ajax loading biến mất(invisible)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		// Chờ cho ngày select hiện(visible)
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='14']/parent::td")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, December 14, 2021");
	}

	@Test
	public void TC_6_Find_Elements() {
		driver.get("https://filebin.net/");
		
		explicitWait = new WebDriverWait(driver, 20);
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		
		uploadFile.sendKeys(filePath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".progress")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//i[@class='far fa-fw fa-file']//following-sibling::a")).isDisplayed());
		
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
		driver.quit();
	}
}