package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_WebDriver_Wait_Part_5_Fluent {
	
	
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		waitAndClickToElement(By.xpath("//div[@id='start']/button"));
		
		waitAndVerifyTextEqual(By.xpath("//div[@id='finish']/h4"), "Hello World!");
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		waitAndVerifyTextEndWith(By.xpath("//div[@id='javascript_countdown_time']"), "00");

	}

	public void waitAndClickToElement(By xpath) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			// tần số mỗi 1 giây check 1 lần
			.pollingEvery(1, TimeUnit.SECONDS)
			// nếu gặp exception là find ko thấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class);
		
		WebElement element = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(xpath);
			}
		});
		element.click();
		
	}
	public void waitAndVerifyTextEqual(By xpath, String expectedText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			// tần số mỗi 1 giây check 1 lần
			.pollingEvery(1, TimeUnit.SECONDS)
			// nếu gặp exception là find ko thấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class);
		
		fluentDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(xpath).getText().equals(expectedText);
			}
		});
	}
	public void waitAndVerifyTextEndWith(By xpath, String expectedText) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tổng thời gian để chờ
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			// tần số mỗi 1 giây check 1 lần
			.pollingEvery(1, TimeUnit.SECONDS)
			// nếu gặp exception là find ko tsehấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class);
		
		fluentDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String actualText = driver.findElement(xpath).getText();
				System.out.println("Text = " + actualText);
				return actualText.endsWith(expectedText);
			}
		});
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}