package Primero;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Link;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class ForgotPassword {

	@Test
	public void testemail() throws IOException, MailosaurException, InterruptedException
	{
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    
	    driver.get("https://qa-v2.primerodev.org/v2/login");
	    driver.findElement(By.id("login.forgot_password")).click();
	    Thread.sleep(2000);
    	driver.findElement(By.id("email")).sendKeys("anything@ijhu7hld.mailosaur.net");
    	
    	driver.findElement(By.id("dialog-submit")).click();
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiSnackbarContent-message")));
    	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiSnackbarContent-message")));
    	//driver.quit();
    
		Thread.sleep(5000);
	    String apiKey = "cNpP2SoPbIuA1ECSteWHyptrJdM0vQIq";
	    String serverId = "ijhu7hld";
	    String serverDomain = "ijhu7hld.mailosaur.net";

	    MailosaurClient mailosaur = new MailosaurClient(apiKey);

	    MessageSearchParams params = new MessageSearchParams();
	    params.withServer(serverId);

	    SearchCriteria criteria = new SearchCriteria();
	    criteria.withSentTo("anything@" +serverDomain);

	    Message message = mailosaur.messages().get(params, criteria);
	    
	    System.out.println(message.to().get(0).email());
	    System.out.println(message.from().get(0).email());
	  //  System.out.println(message.subject());
	    System.out.println(message.text().body());
	    
	    System.out.println("--------------Links---------------");
	    System.out.println(message.html().links().size()); // 2

	    System.out.println("--------------LinksLoops---------------");
	    List<Link> links = message.html().links();
	    String linktoclick = links.get(1).href();
	    System.out.println(linktoclick);
	    driver.switchTo().newWindow(WindowType.WINDOW);
	 
	 driver.navigate().to(linktoclick);
	 driver.findElement(By.id("user.password")).sendKeys("primer0!!");
	 driver.findElement(By.id("user.password_confirmation")).sendKeys("primer0!!");
	
 	driver.findElement(By.id("submit-form")).click();
	
	    
	    Thread.sleep(2000);
	    
	    assertNotNull(message);
	    assertEquals("Reset password instructions", message.subject());
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Home')]")));
    	
		String expectedText = driver.findElement(By.xpath("//h1[contains(text(), 'Home')]")).getText();
		Assert.assertEquals("Home", expectedText);
		Thread.sleep(2000);
		driver.close();
		
	
	}
}
