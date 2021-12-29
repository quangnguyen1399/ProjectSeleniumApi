package webDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Upload_Files {
	
	
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	
	String biaImageName = "bia.jpg";
	String NgânImageName = "Ngân.jpg";
	String thuongImageName = "thuong.jpg";
	
	String biaImagePath = projectFolder + "\\uploadFile\\" + biaImageName;
	String NgânImagePath = projectFolder + "\\uploadFile\\" + NgânImageName;
	String thuongImagePath = projectFolder + "\\uploadFile\\" + thuongImageName;

	String firefoxAutoITOneFile = projectFolder + "\\autoIT\\firefoxUploadOneTime.exe";
	String chromeAutoITOneFile = projectFolder + "\\autoIT\\chromeUploadOneTime.exe";
	
	String firefoxAutoITMultipleFile = projectFolder + "\\autoIT\\firefoxUploadMultiple.exe";
	String chromeAutoITMultipleFile = projectFolder + "\\autoIT\\chromeUploadMultiple.exe";

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		
		System.out.println(driver.toString());
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_Sendkey_One_File() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//Upload File Element
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		
		// 1file per time
		uploadFile.sendKeys(biaImagePath);
		sleepInSecond(3);
		
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(NgânImagePath);
		sleepInSecond(3);
		
		
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(thuongImagePath);
		sleepInSecond(3);

		
		// Verify loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + biaImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + NgânImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thuongImageName + "']")).isDisplayed());
		
		// Click Start upload at each file
		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + biaImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + NgânImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thuongImageName + "']")).isDisplayed());

	}


	public void TC_02_Sendkey_Multiple_File() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//Upload File Element
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		
		// 1file per time
		uploadFile.sendKeys(biaImagePath + "\n" + NgânImagePath + "\n" + thuongImagePath);
		sleepInSecond(3);

		
		// Verify loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + biaImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + NgânImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thuongImageName + "']")).isDisplayed());
		
		// Click Start upload at each file
		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + biaImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + NgânImageName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thuongImageName + "']")).isDisplayed());
	}


	public void TC_03_AutoIT() throws IOException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(2);
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxAutoITOneFile, biaImagePath });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chromeAutoITOneFile, biaImagePath });
		}
		sleepInSecond(5);
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