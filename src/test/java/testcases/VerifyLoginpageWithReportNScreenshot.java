package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import junit.framework.Assert;
import pages.HomePageObject;
import pages.LoginPageObject;
import utility.Helper;

public class VerifyLoginpageWithReportNScreenshot 
{
	WebDriver driver;
	LoginPageObject loginpgobj;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	MediaEntityModelProvider mediaModel;
	@BeforeMethod
	public void setup()
	{
		// create HtmlExtentReport
		htmlReporter = new ExtentHtmlReporter(".\\Reports\\LoginPageReport.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // creates a toggle for the given test, adds all log events under it    
        test = extent.createTest("TC_001 ", "Verify Login Functionality ");
        // log(Status, details)        
		driver = BrowserFactory.getBrowser("Chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
		try {
			mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreesShot(driver,"ApplicationStatus")).build();
			test.pass("Application is UP and Running", mediaModel);
		} catch (IOException e) {
			System.out.println("Failed to capture screen shot");
		}
			
		//test.fail("details", MediaBuilder.createScreenCaptureFromPath("screenshot.png").build());
	}

	@Test(priority=1)
	public void VerifyLogin() throws Exception
	{
		try
		{
		//test = extent.createTest("TC_001", "Login to Application");
		HomePageObject homepgobj = PageFactory.initElements(driver, HomePageObject.class);
		Assert.assertEquals("Online Shopping India | Buy Mobiles, Electronics, Appliances, Clothing and More Online at Flipkart.com", homepgobj.GetTitle());
		mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreesShot(driver,"TitleVerification")).build();
		test.pass("Verify Page Title ", mediaModel);
		
		homepgobj.ClickOnLoginLink();
		test.pass("Click On Login Link ", mediaModel);
		//test.log(Status.INFO, "Click on Login Link");
		
		loginpgobj = PageFactory.initElements(driver, LoginPageObject.class);
		//test.log(Status.INFO, "Application is up and Running(status, details)");
		
		
		loginpgobj.LoginApplication(DataProviderFactory.getExcel().getData(0, 0, 0),DataProviderFactory.getExcel().getData(0, 0, 1));
		test.pass("Login functionality verification ", mediaModel);
		//test.log(Status.INFO, "Login to Application");
		
		loginpgobj.VerifyMyAccountLink();
		test.pass("Verify My Account Link ", mediaModel);
		test.pass("Verify My Account Link ").addScreenCaptureFromPath(Helper.CaptureScreesShot(driver,"VerifyMyAccountpass"));
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to take Screen-shot "+e.getMessage());
		}
		
		//loginpgobj.VerifyLogoutLinkLink();
	}
	
	/*@Test(priority=2)
	public void VerifyMyAccountLink()
	{
		test = extent.createTest("TC_002", "VerifyMyAccountLink");
		loginpgobj.VerifyMyAccountLink();
		try 
		{
			test.pass("details").addScreenCaptureFromPath(Helper.CaptureScreesShot(driver,"VerifyMyAccountpass"));
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to take Screen-shot "+e.getMessage());
		}
		test.log(Status.PASS, "My Account Link has been successfully validated.");
	}*/
	
	@AfterMethod
	public void Closebrower(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				test.fail("details").addScreenCaptureFromPath(Helper.CaptureScreesShot(driver,result.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			/*String path=Helper.CaptureScreesShot(driver, result.getName());
			test.log(Status.FAIL,path);*/
		}
		BrowserFactory.CloseBrowser(driver);
		extent.flush();
	}
}
