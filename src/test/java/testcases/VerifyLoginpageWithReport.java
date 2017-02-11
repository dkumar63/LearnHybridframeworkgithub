package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import junit.framework.Assert;
import pages.HomePageObject;
import pages.LoginPageObject;

public class VerifyLoginpageWithReport 
{
	WebDriver driver;
	LoginPageObject loginpgobj;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	@BeforeMethod
	public void setup()
	{
		//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(".\\Reports\\LoginPageReport.html");
		//logger = htmlReporter.start();
		htmlReporter = new ExtentHtmlReporter(".\\Reports\\LoginPageReport.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // creates a toggle for the given test, adds all log events under it    
        test = extent.createTest("MyFirstTest", "Sample description");
        // log(Status, details)        
		driver = BrowserFactory.getBrowser("Chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
		test.log(Status.INFO, "Application is UP and Running");
	}

	@Test(priority=1)
	public void VerifyLogin() throws Exception
	{
		HomePageObject homepgobj = PageFactory.initElements(driver, HomePageObject.class);
		Assert.assertEquals("Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com", homepgobj.GetTitle());
		test.log(Status.PASS, "Title has been verified Successfully");
		
		homepgobj.ClickOnLoginLink();
		test.log(Status.INFO, "Click on Login Link");
		
		loginpgobj = PageFactory.initElements(driver, LoginPageObject.class);
		test.log(Status.INFO, "Application is up and Running(status, details)");
		//test.log(Status.INFO,"Application is up and Running");
		
		loginpgobj.LoginApplication(DataProviderFactory.getExcel().getData(0, 0, 0),DataProviderFactory.getExcel().getData(0, 0, 1));
		test.log(Status.INFO, "Login to Application");
		
		loginpgobj.VerifyMyAccountLink();
		test.addScreenCaptureFromPath("screenshot.png");
		test.log(Status.PASS, "My Account Link has been successfully validated.");
		//loginpgobj.VerifyLogoutLinkLink();
	}
	
	@AfterMethod
	public void Closebrower()
	{
		BrowserFactory.CloseBrowser(driver);
		extent.flush();
	}
}
