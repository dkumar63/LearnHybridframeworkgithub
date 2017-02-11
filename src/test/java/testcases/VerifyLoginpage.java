package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePageObject;
import pages.LoginPageObject;

public class VerifyLoginpage 
{
	WebDriver driver;
	LoginPageObject loginpgobj;
	
	@BeforeMethod
	public void setup()
	{
		driver = BrowserFactory.getBrowser("Chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());	 
	}

	@Test(priority=1)
	public void VerifyLogin()
	{
		HomePageObject homepgobj = PageFactory.initElements(driver, HomePageObject.class);
		homepgobj.ClickOnLoginLink();
		loginpgobj = PageFactory.initElements(driver, LoginPageObject.class);
		loginpgobj.LoginApplication(DataProviderFactory.getExcel().getData(0, 0, 0),DataProviderFactory.getExcel().getData(0, 0, 1));
		loginpgobj.VerifyMyAccountLink();
		//loginpgobj.VerifyLogoutLinkLink();
	}
	
	@AfterMethod
	public void Closebrower()
	{
		BrowserFactory.CloseBrowser(driver);
	}
}
