package Primero;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_InvalidCreds {
	@Test
	public void test() throws InterruptedException
	{
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:3000/v2/dashboards");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("user_name")).sendKeys("WrongUserName");
		driver.findElement(By.id("password")).sendKeys("primer0!");
		driver.findElement(By.id("login-form-button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiSnackbarContent-message")));
    	
    	String Validationmessage = driver.findElement(By.className("MuiSnackbarContent-message")).getText();
    	System.out.println(Validationmessage);
    	String ActualMessage = "Invalid User name or password.";
    	Assert.assertEquals(ActualMessage, Validationmessage);
    	Thread.sleep(3000);
    	driver.close();
		
	}
	

}
