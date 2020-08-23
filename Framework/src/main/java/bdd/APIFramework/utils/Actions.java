package bdd.APIFramework.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bdd.APIFramework.browser.BrowserSelection;


public class Actions extends BrowserSelection {

	// static WebDriver driver;

	// Creating Object for Logger
	static Logger log = Logger.getLogger(Actions.class.getName());

	// This method is used to wait till the element is clickable
	public static void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// This method is used to clear the input field
	public static void clearInputField(WebElement input) {
		if (input != null) {
			input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			input.sendKeys(Keys.DELETE);
			log.debug(input + " Field gets Cleared");
		} else {
			log.error("Unable to find the Element: " + input);
			Assert.fail(input + " ELEMENT_NOT_FOUND");
		}
	}

	// This method is used to send data in the text fields
	public static void sendKeysToElement(WebElement element, String text) {
		if (element != null) {
			element.sendKeys(text);
			log.debug(text + " gets entered in " + element + " field");
		} else {
			log.error("Unable to find the Element: " + element);
			Assert.fail(element + " ELEMENT_NOT_FOUND");
		}
	}

	// This method is to check whether the element is enabled
	public static boolean isElementEnabled(WebElement element) {
		return (element != null) && element.isEnabled();
	}

	// This method is used to click on elements
	public static void clickOnElement(WebElement element) {
		if (element != null) {
			waitForElementToBeClickable(element);
			element.click();
			log.info("Clicked on " + element);
		} else {
			log.error("Unable to find the Element: " + element);
			Assert.fail(element + " ELEMENT_NOT_FOUND");
		}
	}
	
	// This method is used to click on elements
		public static void clickOnElemenWithSpecificTimet(WebElement element) {
			if (element != null) {
				waitForElementToBeClickable(element);
				element.click();
				log.info("Clicked on " + element);
			} else {
				log.error("Unable to find the Element: " + element);
				Assert.fail(element + " ELEMENT_NOT_FOUND");
			}
		}

	// This method is used to get the text from the element provided
	public static String getElementText(WebElement element) {
		return (element != null) ? element.getText() : null;
	}

	// This method is used to wit till the element is visible
	public static void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// This method is used to wit till the element is visible
		public static void waitForElementToBeVisible(WebElement element, long timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
		}

	// This method is used to check the element is displayed or not
	public static boolean isElementDisplayed(WebElement element) {
		try {
			waitForElementToBeVisible(element);
		} catch (Exception e) {
			log.error("" + e.getMessage());
			return false;
		}
		return true;
	}
	
	public static void clickUsingJavaScript(WebElement element) {
		(new WebDriverWait(driver, 8)).until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		
	}

}
