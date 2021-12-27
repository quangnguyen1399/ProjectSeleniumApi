package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_javascript_Executor {
	
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	String loginPageUrl, userID, password, customerName, dateOfBirthInput, addressInput, city, gender;
	String date, month, year, state, pin, phone, email, dateOfBirthOutput, addressOutput;
	By customerNameTextbox = By.name("name");
	By dobTextbox = By.name("dob");
	By addTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		customerName = "quang";
		gender = "male";
		date = "01";
		month = "03";
		year = "1999";
		dateOfBirthInput = month + "/" + date + "/" + year;
		dateOfBirthOutput = year + "-" + month + "-" + date;
		addressInput = "123 Cong Lo\nTan Binh\nHCM";
		addressOutput = addressInput.replace("\n", " ");
		city = "HCM";
		state = "Tan Binh";
		pin = "700000";
		phone = "0111222333";
		email = generateEmail();
	}
	@Test
	public void TC_01_Live_Guru() {
		navigateToUrlByJS(driver,"http://live.techpanda.org/");
		
		String liveTechpandaDomain = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals(liveTechpandaDomain, "live.techpanda.org");
		
		String liveTechpandaURL = (String) executeForBrowser(driver, "return document.URL");
		Assert.assertEquals(liveTechpandaURL, "http://live.techpanda.org/");
		
		clickToElementByJS(driver, "//a[text()='Mobile']");
		
		clickToElementByJS(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS(driver, "//a[text()='Customer Service']");
		
		String customerServicetitle = (String) executeForBrowser(driver, "return document.title");
		Assert.assertEquals(customerServicetitle, "Customer Service");
		
		scrollToElementOnTop(driver, "//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", generateEmail());
		sleepInSecond(2);
		
		clickToElementByJS(driver, "//button[@title='Subscribe']");
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));
		
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		
		String demoGuruDomain = (String) executeForBrowser(driver, "return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
		sleepInSecond(3);
		
		
	}

	@Test
	public void TC_02_demo_Guru() {
		
		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys("quang0103sh@gmail.com");
		
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(dobTextbox).sendKeys(dateOfBirthInput);
		driver.findElement(addTextArea).sendKeys(addressInput);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}

	@Test
	public void TC_03_() {
		
	}
	public Object executeForBrowser(WebDriver driver2, String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver2, String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver2, String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(WebDriver driver2, String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(WebDriver driver2, String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public String generateEmail() {
		Random rand = new Random();
		return "quang" + rand.nextInt(999) + "@gmail.com";
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