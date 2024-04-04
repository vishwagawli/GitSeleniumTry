package Primero.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Primero.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user_name")
	WebElement username; 
	
	@FindBy(id="password")
	WebElement pwd; 
	
	@FindBy(id="login-form-button")
	WebElement loginBtn;
	
	By Elmt = By.xpath("//h1[contains(text(), 'Home')]");
	
	@FindBy(xpath="//h1[contains(text(), 'Home')]")
	WebElement hometext;
	
	public void loginApplication(String email, String password)
	{
		username.sendKeys(email);
		pwd.sendKeys(password);
		loginBtn.click();
	}
	public void gotoApp()
	{
		driver.get("http://localhost:3000/v2/dashboards");
	}
	public void validateElement()
	
	{
		waitForElementToAppear(Elmt);
	}
	
	public String validateText()
	{
		return hometext.getText();
	}

	}

