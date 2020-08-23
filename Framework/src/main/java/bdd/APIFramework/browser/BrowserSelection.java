package bdd.APIFramework.browser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserSelection {

	public static WebDriver driver;
	public Properties prop = new Properties();
	public FileInputStream fis;
	public static File file;

	// This is to generate current time of the system

	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	// Creating Object for Logger
	static Logger log = Logger.getLogger(BrowserSelection.class.getName());

	// Opening Selected browser
	public WebDriver openBrowser() throws InterruptedException {

		String browserName = loadFile().getProperty("browser");

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			this.driver = new ChromeDriver();
			log.info("Chrome Browser get Invoked");
		}

		else if (browserName.equals("internetexplorer")) {

			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("InternetExplorer Browser get Invoked");
		} else if (browserName.equals("firefox")) {

			driver = new FirefoxDriver();
			log.info("Firefox Browser get Invoked");
		}

		else {
			log.error("This" + browserName + "Browser cant be open");
		}

		maximizeBrowser(driver);
		log.info("Browser get Maximized");
		return driver;
		
	}
	
	public Properties loadFile() {
		try {
			file = new File("Properties/Global.properties");
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error("Unable to find the file" + file);
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			log.error("The File not get Loaded");
			e.printStackTrace();
		}
		return prop;
	}
	
	public void launchUrl(String url) throws InterruptedException {

		// Opening the url
				driver.get(loadFile().getProperty(url));
				Thread.sleep(5000);
				log.info("URL get Loaded");
				// Implementing wait command
				int waitTime = Integer.parseInt(prop.getProperty("wait"));
				driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}

	public void maximizeBrowser(WebDriver driver) {

		driver.manage().window().maximize();

	}

	public void closeBrowser(WebDriver driver) {

		driver.close();
		log.info("Browser get Close");
		driver = null;

	}

	public void quitBrowser(WebDriver driver) {
		driver.quit();
		log.info("Browser get Quit");
	}
}
