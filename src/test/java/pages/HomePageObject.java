package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageObject 
{
	WebDriver driver;
	public HomePageObject(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(xpath="//a[text()='Log In']") WebElement LoginLink;
	@FindBy(xpath="//a[text()='Signup']") WebElement SignupLink;
	@FindBy(xpath="//a[text()='CART']") WebElement CartLink;
	public void ClickOnLoginLink()
	{
		LoginLink.click();
	}
	
	public void ClickOnSignupLink()
	{
		SignupLink.click();
	}
	
	public void ClickOnCartLink()
	{
		CartLink.click();
	}
	
	public String GetTitle()
	{
		return driver.getTitle();
	}
	

}
