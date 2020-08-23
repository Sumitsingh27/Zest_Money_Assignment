package stepDefinations;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import bdd.APIFramework.browser.BrowserSelection;
import bdd.APIFramework.utils.Actions;
import bdd.APIFramework.utils.WebDataConstants;
import bdd.APIFramework.utils.WebElementsCollector;

public class WebStepDefination extends BrowserSelection {

	public static final String file = "Properties/WebStepDefination.properties";
	static Logger log = Logger.getLogger(WebStepDefination.class.getName());


	public void verifyAmazonLogo() {
		WebElement amazonLogo = WebElementsCollector.getWebElement(driver, file, "amazonLogo");
		Actions.isElementDisplayed(amazonLogo);
	}

	public void browserClose() throws InterruptedException {
		BaseTestClass.testCleanUp();
		log.info("Web Browser Closed");
	}

	public void searchItemsInAmazon(String item) {
		WebElement field = WebElementsCollector.getWebElement(driver, file, "amazonSearchField");
		Actions.sendKeysToElement(field, item);
		Actions.sendKeysToElement(field,Keys.chord(Keys.ENTER));
		//Actions.clickOnElement(search);
	}

	public void validateItemSearchInAmazon(String item) {
		WebElement itemDisplay = WebElementsCollector.getWebElement(driver, "xpath$//span[contains(text(),'"+item+"')]");
		Actions.waitForElementToBeVisible(itemDisplay);
		Actions.isElementDisplayed(itemDisplay);
		log.debug(item +" item is displayed");

	}

	public void getItemSearchedPriceInAmazon(String item) {
		WebElement price = WebElementsCollector.getWebElement(driver, "xpath$//span[contains(text(),'"+item+"')]/../../../../../../../*[2]/*[1]/*/*/*[2]/*/*/*[1]/*[2]/*[2]");
		WebDataConstants.PRICESTORE_AMAZON = Actions.getElementText(price).replace(",", "");
		System.out.println(WebDataConstants.PRICESTORE_AMAZON);
		log.debug("Searched Item "+item+" price : " +WebDataConstants.PRICESTORE_AMAZON);

	}





	public void verifyFlipKartLogo() {
		WebElement flipkartLogo = WebElementsCollector.getWebElement(driver, file, "flipkartLogo");	
		WebElement crossTab = WebElementsCollector.getWebElement(driver, file, "crossTab");
		if(Actions.isElementDisplayed(crossTab)) {
			Actions.clickOnElement(crossTab);
		}
		Actions.isElementDisplayed(flipkartLogo);
	}

	public void searchItemsInFlipkart(String item) {
		WebElement field = WebElementsCollector.getWebElement(driver, file, "flipkartSearchField");
		Actions.sendKeysToElement(field, item);	
		Actions.sendKeysToElement(field,Keys.chord(Keys.ENTER));
	}

	public void validateItemSearchInFlipKart(String item) {
		WebElement itemDisplay = WebElementsCollector.getWebElement(driver, "xpath$//div[contains(text(),'"+item+"')]");
		Actions.waitForElementToBeVisible(itemDisplay);
		Actions.isElementDisplayed(itemDisplay);
		log.debug(item +" item is displayed");

	}

	public void getItemSearchedPriceInFlipkart(String item) {
		WebElement price = WebElementsCollector.getWebElement(driver, "xpath$//div[contains(text(),'"+item+"')]/../../*[2]/*/*/*");
		WebDataConstants.PRICESTORE_FLIPKART = Actions.getElementText(price).replace(",", "").substring(1);	
		System.out.println(WebDataConstants.PRICESTORE_FLIPKART);
		log.debug("Searched Item "+item+" price : " +WebDataConstants.PRICESTORE_FLIPKART);

	}

	public void compareThePrices() {
		float difference;
		if(Float.parseFloat(WebDataConstants.PRICESTORE_FLIPKART) > Float.parseFloat(WebDataConstants.PRICESTORE_AMAZON)) {
			difference = Float.parseFloat(WebDataConstants.PRICESTORE_FLIPKART) - Float.parseFloat(WebDataConstants.PRICESTORE_AMAZON);
			System.out.println("FlipKart Price is greater i.e, " +WebDataConstants.PRICESTORE_FLIPKART+ " by the difference of :" + difference);
			log.info("FlipKart Price is greater i.e, " +WebDataConstants.PRICESTORE_FLIPKART+ " by the difference of :" + difference);
		} else {
			difference = Float.parseFloat(WebDataConstants.PRICESTORE_AMAZON) - Float.parseFloat(WebDataConstants.PRICESTORE_FLIPKART);
			System.out.println("Amazon Price is greater i.e, " +WebDataConstants.PRICESTORE_AMAZON+ " by the difference of :" + difference);
			log.info("Amazon Price is greater i.e, " +WebDataConstants.PRICESTORE_AMAZON+ " by the difference of :" + difference);

		}

	}

}
