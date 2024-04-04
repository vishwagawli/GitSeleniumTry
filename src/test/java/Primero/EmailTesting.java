package Primero;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Link;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class EmailTesting {

	@Test
	public void testemail() throws IOException, MailosaurException, InterruptedException
	{
		
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
	    System.out.println(message.subject());
	    System.out.println(message.text().body());
	    
	    System.out.println("--------------Links---------------");
	    System.out.println(message.html().links().size()); // 2

	    Link firstLink = message.html().links().get(0);
	    System.out.println(firstLink.text()); // "Google Search"
	    System.out.println(firstLink.href()); 
	    System.out.println("--------------LinksLoops---------------");
	    List<Link> links = message.html().links();
	    for(int i=0;i<links.size();i++)
	    {
	    	System.out.println(links.get(i).text());
	    	System.out.println(links.get(i).href());
	    }
	    String linktoclick = links.get(2).href();
	    
	    WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(linktoclick);
	    
	    Thread.sleep(2000);
	    	driver.findElement(By.id("email")).sendKeys("vishwambar.gawli@gmail.com");
	    	driver.findElement(By.id("buttons.ok")).click();
	    	
	    	String toastmsg = driver.findElement(By.className("MuiSnackbarContent-message")).getText();
	    	System.out.println(toastmsg);
	    assertNotNull(message);
	    assertEquals("Reset password instructions", message.subject());
	}
}
