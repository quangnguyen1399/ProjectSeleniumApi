package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Web_Element_Exercise {
	WebDriver driver;
	WebElement element;
	
	// Biến global -  phạm vi Class
	
	// Khai báo và gán giá trị để đại diện cho By (chưa đi tìm element)
	By emailTextboxBy = By.id("mail");
	By ageUnderRadioBy = By.id("under_18");
	By educationTextareaBy = By.id("edu");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");
	By javaCheckbox = By.id("java");
	
	//Disabled
	By passwordTextboxBy = By.id("password");
	By bioTextAreaBy = By.id("bio");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/");
	}
	@Test
	public void TC_01_isDisplayed() {
		if(isElementDisplayed(emailTextboxBy)) {
			sendkeyToElement(emailTextboxBy, "Automation Testing");
		}
		if(isElementDisplayed(ageUnderRadioBy)) {
			clickToElement(ageUnderRadioBy);
		}
		if(isElementDisplayed(educationTextareaBy)) {
			sendkeyToElement(educationTextareaBy, "Automation Testing");
		}
		
		Assert.assertFalse(isElementDisplayed(user5TextBy));
		
	}

	@Test
	public void TC_02_isEnabled() {
		driver.navigate().refresh();
		
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageUnderRadioBy));
		Assert.assertTrue(isElementEnabled(educationTextareaBy));

		
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(bioTextAreaBy));

	}

	@Test
	public void TC_03_Seclected() {
		driver.navigate().refresh();
		
		clickToElement(ageUnderRadioBy);
		clickToElement(javaCheckbox);
		
		Assert.assertTrue(isElementSelected(ageUnderRadioBy));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
		clickToElement(javaCheckbox);
		
		Assert.assertTrue(isElementSelected(ageUnderRadioBy));
		Assert.assertFalse(isElementSelected(javaCheckbox));
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element is displayed:" + by);
			return true;
		} else {
			System.out.println("Element is not displayed:" + by);
			return false;
		}
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
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element is selected:" + by);
			return true;
		} else {
			System.out.println("Element is de-selected:" + by);
			return false;
		}
	}
	
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}