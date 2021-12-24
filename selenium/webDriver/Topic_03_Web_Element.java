package webDriver;

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

public class Topic_03_Web_Element {
	
	
	WebDriver driver;
	WebElement element;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_() {
		driver.get("https://www.facebook.com/");
		
		// Khi chi thao tac voi element nay 1 lan
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		// Khi thao tác với element này nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear(); //***************
		emailTextbox.sendKeys("automation@testing.com");//***************
		emailTextbox.isDisplayed();//***************
		
		WebElement element = null;
		
		//Java Generic
		List<WebElement> elements = null;
		
		// Xóa dữ liệu trong 1 field (Editable): Textbox/ Textarea/ Dropdown
		element.clear();
		
		// Nhập dữ liệu trong 1 field (Editable): Textbox/ Textarea/ Dropdown
		element.sendKeys("");
		
		// Thao tác tìm kiếm và trả về 1 element
		driver.findElement(By.xpath("//form[@class='_featuredLogin_formContainer']//input[@id='email']"));
		
		// Thao tác tiềm kiếm và trả về nhiều element
		elements = driver.findElements(By.xpath("//form[@class='_featuredLogin_formContainer']//input[@id='email']"));
		
		// Lấy ra giá trị nằm trong 1 attribute nào đó của element
		String emailPlaceholderText = element.getAttribute("placeholder");//***************
		
		// Lấy ra text của element
		element.getText();//***************
		
		
		// GUI: font/ size/ color/ location/ position/... *********************************************************
		
		// Lấy ra được các giá trị của css thuộc element đó 
		element.getCssValue("background-color");
		
		element.getCssValue("font-size");
		
		element.getLocation();
		
		element.getSize();
		
		element.getRect();
		
		// CHụp hình và add vào report
		// element.getScreenshortAs(arg0)
		
		// #email
		element.getTagName();
		
		// Kiểm tra: assertTrue/ False/ Equals
		
		// Kiểm tra 1 element hiển thị: đúng cho tất cả các loại element
		Assert.assertTrue(element.isDisplayed());//***************
		Assert.assertFalse(element.isDisplayed());
		
		// Kiểm tra 1 element đã được chọn: Checkbox/ Radio Button
		Assert.assertTrue(element.isSelected());//***************
		
		// Kiểm tra 1 element có thể thao tác được hay ko (ko bị distable)
		Assert.assertTrue(element.isEnabled());
		
		// Chỉ work được với element nằm trong form (TAGNAME)
		element.submit();
		
		// Click vào 1 element: button/ checkbox/ radio/ link/ icon/ dropdown/..
		element.click();//***************
		
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