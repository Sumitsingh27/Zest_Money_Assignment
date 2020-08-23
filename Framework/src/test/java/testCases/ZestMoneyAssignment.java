package testCases;

import org.testng.annotations.Test;

import bdd.APIFramework.browser.BrowserSelection;
import stepDefinations.BaseTestClass;
import stepDefinations.WebStepDefination;

public class ZestMoneyAssignment extends BaseTestClass  {

	
	@Test
	public void testCaseOne() throws InterruptedException{
		BrowserSelection browser = new BrowserSelection();
		WebStepDefination step = new WebStepDefination();
		
		
		browser.launchUrl("amazonUrl");
		step.verifyAmazonLogo();
		step.searchItemsInAmazon("iPhone 11 (64GB) - Yellow");
		step.validateItemSearchInAmazon("iPhone 11 (64GB) - Yellow");
		step.getItemSearchedPriceInAmazon("iPhone 11 (64GB) - Yellow");
		
		browser.launchUrl("flipkartUrl");
		step.verifyFlipKartLogo();
		step.searchItemsInFlipkart("iPhone 11 Yellow 64 GB");
		step.validateItemSearchInFlipKart("iPhone 11 (Yellow, 64 GB)");
		step.getItemSearchedPriceInFlipkart("iPhone 11 (Yellow, 64 GB)");
		step.compareThePrices();
	}
}
