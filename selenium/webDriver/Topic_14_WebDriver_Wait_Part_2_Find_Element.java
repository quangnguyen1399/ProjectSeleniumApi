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

public class Topic_14_WebDriver_Wait_Part_2_Find_Element {
	
	
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automation Test Project\\02- Selenium API\\browserDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// implicitlyWait : Là khoảng thời gian để chờ cho việc tìm element
		// Wait ngầm định: cho việc tìm element
		// tìm element: findElement / findElements

	}

	public void TC_1_Find_Element() {
		// Khi đi tìm element:
		// Trường hợp 1: Có duy nhất 1 maching node
			// 1.1 - Khi bắt đầu tìm kiếm thì element xuất hiện ngay -> không cần phải chờ hết timeout
			// 1.2 - Khi mới bắt đầu tìm kiếm element chưa xuất hiện
				// Nó sẽ lặp lại element này - 0.5s tìm lại 1 lần
				// Nếu nó tìm thấy thì sẽ chuyển qua step sau luôn
		System.out.println("Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationfc@gmail.com");
		System.out.println("End: " + getDateTimeNow());
		
		
		// Trường hợp 2: Không có maching node nào
			// Tìm element - polling là 0.5s -> Chờ cho hết timeout
			// Nếu nó không tìm thấy sau khi hết timeout thì:
				// + Nó sẽ throw ra 1 exception: NoSuchElement
				// + Nó sẽ đánh fail testcase này luôn - ko chạy các step tiếp theo nữa
		System.out.println("Start: " + getDateTimeNow());
		//driver.findElement(By.xpath("//input[@id='not_found']")).sendKeys("automationfc@gmail.com");
		System.out.println("End: " + getDateTimeNow());
		
		
		// Trường hợp 3: Nhiều hơn 1 maching node
		// Nếu như tìm thấy nhiều hơn thì luôn luôn thao tác với element đầu tiên
		driver.findElement(By.xpath("//div[@class='input-box']/input")).sendKeys("automationfc@gmail.com");


				
	}

	@Test
	public void TC_2_Find_Elements() {
		List<WebElement> textboxes;
		
		// Trường hợp 1: Có duy nhất 1 maching node
		// Không cần chờ hết timeout
		// Trả về 1 list chứa element đó
		System.out.println("Start: " + getDateTimeNow());
		textboxes = driver.findElements(By.id("email"));
		System.out.println("Size = " + textboxes.size());
		System.out.println("End: " + getDateTimeNow());

		
		// Trường hợp 2: Không có maching node nào
		// Nó cũng phải chờ hết timeout 10s
		// Trong 10s này cứ 0.5s tìm lại 1 lần (polling)
		// Nếu như hết timeout mà ko tìm thấy thì ko đánh fail
		// + List rỗng
		// + Chuyển qua step tiếp theo
		System.out.println("Start: " + getDateTimeNow());
		textboxes = driver.findElements(By.id("not_found"));
		System.out.println("Size = " + textboxes.size());
		System.out.println("End: " + getDateTimeNow());

		// Trường hợp 3: Nhiều hơn 1 maching node
		// Ko cần chờ hết timeout
		// Trả về 1 list chứa element đó
		System.out.println("Start: " + getDateTimeNow());
		textboxes = driver.findElements(By.xpath("//div[@class='input-box']/input"));
		System.out.println("Size = " + textboxes.size());
		System.out.println("End: " + getDateTimeNow());

		
	}
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}