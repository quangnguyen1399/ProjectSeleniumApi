package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Custom_Dropdown {
	WebDriver driver;
	Select select;
	
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String[] months = {"January", "March", "June"};
	String[] newmonths = {"January", "March", "June", "October", "December"};
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		//driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 100);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/selectmenu/");
		
		selectTheItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		
		selectTheItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(3);
		
		selectTheItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInSecond(3);
		
		selectTheItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "1");
		sleepInSecond(3);
	}
	public void TC_02_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/#/bootstrap5/drop-down-list/data-binding");
		
		selectTheItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']//li", "Basketball");
		sleepInSecond(3);
		System.out.println(getHiddenText("select[id='games_hidden']>option"));
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Basketball");
		
		selectTheItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']//li", "Rugby");
		sleepInSecond(3);
		System.out.println(getHiddenText("select[id='games_hidden']>option"));
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Rugby");
		
		selectTheItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']//li", "American Football");
		sleepInSecond(3);
		System.out.println(getHiddenText("select[id='games_hidden']>option"));
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "American Football");
		
	}

	
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectTheItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText() , "Justen Kitsune");
		
		selectTheItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText() , "Christian");
		
		selectTheItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText() , "Elliot Fu");
	}
	
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectTheItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText() , "Second Option");

		selectTheItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText() , "First Option");

		
		selectTheItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText() , "Third Option");

	}

	public void TC_05_Editable_Part_I() {
		driver.get("https://indrimuska.github.io/jquery-editable-select/");
		
		selectTheItemInEditableDropdown("//div[@id='default-place']/input", "//ul[@class='es-list']/li", "Land Rover");
		sleepInSecond(2);
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"), "Land Rover");

		selectTheItemInEditableDropdown("//div[@id='default-place']/input", "//ul[@class='es-list']/li", "Audi");
		sleepInSecond(2);
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"), "Audi");
		
		selectTheItemInEditableDropdown("//div[@id='default-place']/input", "//ul[@class='es-list']/li", "BMW");
		sleepInSecond(2);
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"), "BMW");


	}
	
	public void TC_05_Editable_Part_II() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectTheItemInEditableDropdown("//input[@class='search']", "//div[@role='listbox']//span", "Afghanistan");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Afghanistan");

		selectTheItemInEditableDropdown("//input[@class='search']", "//div[@role='listbox']//span", "Anguilla");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Anguilla");
		
		selectTheItemInEditableDropdown("//input[@class='search']", "//div[@role='listbox']//span", "Austria");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Austria");


	}
	@Test
	public void TC_06_Multible_Select() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=192&url=basic.html");
		
		selectTheMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", months);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(months));
		
		driver.navigate().refresh();
		
		selectTheMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", newmonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(newmonths));

	}

	
	// H??m common (d??ng chung) - ko fix c???ng d??? li???u trong h??m
	public void selectTheItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
		// 1 Click v??o th??? cha ????? cho n?? x??? ra t???t c??? c??c item
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		// 2 Ch??? cho t???t c??? c??c Item ??c load ra h???t 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// 3 L???y h???t t???t c??? c??c item n??y ????a v??o 1 c??i List<WebElement>
		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		
		// 4 Duy???t qua c??i List n??y t???ng item
		for (WebElement actualItem : childItems) {
			
			// 5 M???i l???n duy???t qua s??? ki???m tra c??i item text c???a n?? c?? b???ng v???i item m??nh c???n ch???n hay kh??ng
			if(actualItem.getText().trim().equals(expectedItem)) {
				
				// 6 N???u nh?? t??m th???y item c???n click th?? scroll ?????n item ????(n???m b??n d?????i)
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(1);
				
				// 7 Click v??o item ????
				actualItem.click();
				
				// 8 Tho??t kh???i v??ng l???p
				break;
			}
		}
		
	}
	public void selectTheItemInEditableDropdown(String parentXpath, String childXpath, String expectedItem) {
		
		driver.findElement(By.xpath(parentXpath)).clear();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		// 2 Ch??? cho t???t c??? c??c Item ??c load ra h???t 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// 3 L???y h???t t???t c??? c??c item n??y ????a v??o 1 c??i List<WebElement>
		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		
		// 4 Duy???t qua c??i List n??y t???ng item
		for (WebElement actualItem : childItems) {
			
			// 5 M???i l???n duy???t qua s??? ki???m tra c??i item text c???a n?? c?? b???ng v???i item m??nh c???n ch???n hay kh??ng
			if(actualItem.getText().trim().equals(expectedItem)) {
				
				// 6 N???u nh?? t??m th???y item c???n click th?? scroll ?????n item ????(n???m b??n d?????i)
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(1);
				
				// 7 Click v??o item ????
				actualItem.click();
				
				// 8 Tho??t kh???i v??ng l???p
				break;
			}
		}
		
	}
	public void selectTheMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem ) {
		// 1 Click v??o th??? cha ????? cho n?? x??? ra t???t c??? c??c item
		driver.findElement(By.xpath(parentXpath)).click();

		
		// 2 Ch??? cho t???t c??? c??c Item ??c load ra h???t 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// 3 L???y h???t t???t c??? c??c item n??y ????a v??o 1 c??i List<WebElement>
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		// 4 Duy???t qua c??i List n??y t???ng item
		for (WebElement childElement : allItems) {
			for (String item : expectedValueItem) {
				// 5 M???i l???n duy???t qua s??? ki???m tra c??i item text c???a n?? c?? b???ng v???i item m??nh c???n ch???n hay kh??ng
				if(childElement.getText().equals(item)) {
					
					// 6 N???u nh?? t??m th???y item c???n click th?? scroll ?????n item ????(n???m b??n d?????i)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
			
		}
		
	}
	public boolean areItemSelected(String[] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : itemSelectedText) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		}
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public String getHiddenText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}