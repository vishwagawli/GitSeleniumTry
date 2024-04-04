package Primero;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Primero.TestComponents.BaseTest;
import Primero.pageobjects.LoginPage;

public class Login extends BaseTest {
	
	@Test
	public void login() throws IOException
	{
		
	/*	WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:3000/v2/dashboards");
		driver.manage().window().maximize();
		
		*/
		/*driver.findElement(By.id("user_name")).sendKeys("primero");
		driver.findElement(By.id("password")).sendKeys("primer0!");
		driver.findElement(By.id("login-form-button")).click();
		*/
		BaseTest bs= new BaseTest();
		bs.initializeDriver();
		LoginPage loginpageobj = new LoginPage(driver);
		//loginpageobj.gotoApp();
	//	LoginPage loginpageobj = launchApp();
		loginpageobj.loginApplication("primero", "primer0!");
		loginpageobj.validateElement();
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Home')]")));
    	
		//String expectedText = driver.findElement(By.xpath("//h1[contains(text(), 'Home')]")).getText();
	//	Assert.assertEquals("Home", expectedText);
		Assert.assertEquals("Home", loginpageobj.validateText());
		
		driver.close();
		
		
		
	}
	

}
