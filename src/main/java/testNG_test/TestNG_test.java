package testNG_test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_test {
	
	WebDriver driver;
	String browser;
	
	@BeforeClass
	public void readConfig() {
		
		Properties prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("src/main/java/config/config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);
		}catch(IOException e) {
			e.getStackTrace();
			
		}
	}
	
	@BeforeMethod
	public void init() { 
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://techfios.com/test/101/index.php");
		}
	
	@Test (priority = 1)
	public void addUserCategory() throws InterruptedException { 
		
		//find the add category search bar 
		driver.findElement(By.name("data")).click(); 
		By addCategory = By.xpath("/html/body/div[4]/input[1]");
		By clickAdd = By.xpath("/html/body/div[4]/input[2]");
		By errorField = By.xpath("/html/body/text()");
		
		driver.findElement(addCategory).sendKeys("Meds");
		driver.findElement(clickAdd).click();
		Thread.sleep(2000);
		driver.findElement(addCategory).sendKeys("Car");
		driver.findElement(clickAdd).click();
		Thread.sleep(2000);
		
		String errorExpected = driver.findElement(errorField).getText();
		Assert.assertEquals("exists", errorExpected, "The Category you want already exists!");
		
		
	}
	
	public void validateMonths() throws InterruptedException{
		
		By monthList = By.xpath("/html/body/div[4]/span/select[3]");
		
		driver.findElement(monthList).click();
		Thread.sleep(2000);
		
	}
	
	
	}


