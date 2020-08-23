package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import bdd.APIFramework.browser.BrowserSelection;
import bdd.APIFramework.utils.WebDataConstants;

public class BaseTestClass {
	

	 static BrowserSelection bs ;
	 protected static WebDriver driver;
	 
	 public static WebDataConstants constants;

	@BeforeTest()
	public static void testStartUp() throws InterruptedException {

		bs = new BrowserSelection();
		driver = bs.openBrowser();
		constants = new WebDataConstants();
		
	}
	
	@AfterTest()
	public static void testCleanUp() throws InterruptedException {
		Thread.sleep(2000);
		bs.closeBrowser(driver);
		bs.quitBrowser(driver);
	
	}

}