package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class LoginPageObject
{
	WebDriver driver;
	public LoginPageObject(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	@FindBy(xpath="//input[@class='_2zrpKA']") WebElement UserID;
	@FindBy(xpath="//input[@class='_2zrpKA _3v41xv']") WebElement password;
	@FindBy(xpath="//button[@class='_2AkmmA _1LctnI _7UHT_c']") WebElement LoginButtonLink;
	@FindBy(xpath="//a[text()='My Account']") WebElement MyAccLink;
	By MyAccountLink = By.xpath("//a[text()='My Account']");
	By LogoutLink = By.xpath("//a[text()='Log Out']");
	
	public void EnterUserName(String uname)
	{
		UserID.sendKeys(uname);
	}
	
	public void EnterPassword(String passwd)
	{
		password.sendKeys(passwd);
	}
	
	public void ClickOnLoginButton()
	{
		LoginButtonLink.click();
	}
	
	public void ClickOnMyAccountLink()
	{
		MyAccLink.click();
	}
	
	public void LoginApplication(String uname, String pass)
	{
		EnterUserName(uname);
		EnterPassword(pass);
		ClickOnLoginButton();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	public void VerifyMyAccountLink()
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement ele =wait.until(ExpectedConditions.presenceOfElementLocated(MyAccountLink));
		Assert.assertEquals(ele.getText(),"My Account");		
	}
	
	public void VerifyLogoutLinkLink()
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement ele =wait.until(ExpectedConditions.presenceOfElementLocated(LogoutLink));
		Assert.assertEquals(ele.getText(),"Log Out");		
	}
}
