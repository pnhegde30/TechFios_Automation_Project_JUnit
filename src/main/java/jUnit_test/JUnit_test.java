package jUnit_test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnit_test {
	
	WebDriver driver; 
	
	@Before
	public void init() { 
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe" );
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://techfios.com/test/101/index.php");
	}
	
	@Test
	public void performToggle() throws InterruptedException { 
		//click the toggle all button 
		driver.findElement(By.name("allbox")).click();
		
		//wait 2 seconds
		Thread.sleep(2000);
		
		//click the toggle all button again
		driver.findElement(By.xpath("/html/body/div[3]/input[3]")).click();
		Thread.sleep(2000);
		
		//click the first option 
		driver.findElement(By.name("todo[0]")).click();
		
		//click on remove 
		driver.findElement(By.xpath("/html/body/div[3]/input[1]")).click(); 
		Thread.sleep(2000);
		
		driver.findElement(By.name("allbox")).click();
		driver.findElement(By.xpath("/html/body/div[3]/input[1]")).click();
		
	}
	
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}
	

}
