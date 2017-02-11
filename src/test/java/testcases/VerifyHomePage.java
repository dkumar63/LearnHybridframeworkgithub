package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import junit.framework.Assert;
import pages.HomePageObject;

public class VerifyHomePage 
{
	WebDriver driver;
	@BeforeMethod
	public void PageSetup()
	{
		driver = BrowserFactory.getBrowser("Chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
	}
	
	@Test
	public void VerifyHomePageTitle()
	{ 
		HomePageObject homepgobj = PageFactory.initElements(driver, HomePageObject.class);
		Assert.assertEquals("Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com", homepgobj.GetTitle());
	}
	

	@AfterMethod
	public void Closebrower()
	{
		BrowserFactory.CloseBrowser(driver);
	}
}
