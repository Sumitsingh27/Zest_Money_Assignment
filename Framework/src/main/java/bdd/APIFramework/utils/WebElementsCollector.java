package bdd.APIFramework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementsCollector {

	public static boolean status;

	// property class and input stream reference
	public static Properties prop;
	public static FileInputStream fis;

	// String array to hold the return value before it is assigned to the
	// locator method and path
	public static String arr[];

	// String to hold the property value retrieved from the property file
	public static String propertyValue;

	/// Creating Object for Logger
	static Logger log = Logger.getLogger(WebElementsCollector.class.getName());

	// page object pattern implementation with Reflection
	public static WebElement getWebElement(WebDriver driver, String fileName, String elementName) {

		try {
			String locatorpath;

			prop = new Properties();
			fis = new FileInputStream(fileName);
			prop.load(fis);

			propertyValue = prop.getProperty(elementName);
			arr = propertyValue.split("\\$");

//			if (!varPath.equalsIgnoreCase("")) {
//				locatorpath = arr[1] + varPath + arr[2];
//				System.out.println(locatorpath);
//
//			} else {
//				locatorpath = arr[1];
//			}

			locatorpath = arr[1];

			// getting an instance for the method of By class that will be
			// stored in the
			// externalLinkMethod variable
			Method method = null;
			try {
				method = By.class.getDeclaredMethod(arr[0], String.class);
			} catch (NoSuchMethodException e) {
				log.error(arr[0] + " This method cannot be found");
				e.printStackTrace();
			} catch (SecurityException e) {
				log.error("This is Security Exception");
				e.printStackTrace();
			}

			// passing an instance of the concrete class Dummy that extends the
			// abstract class
			// By so that the method is present in JVM at runtime
			try {
				return driver.findElement((By) method.invoke(new Dummy(), locatorpath));
			} catch (IllegalAccessException e) {
				log.error("Illegal Access Exception");
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				log.error("Illegal Argument Exception in Method: " + method);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				log.error("Invocation Target Exception");
				e.printStackTrace();
			}

		}

		catch (FileNotFoundException e) {

			log.error(fileName + " :File is not present in the specified path");
			e.printStackTrace();
		}

		catch (IOException e) {

			log.error(fileName + " :File is present but unable to load it");
			e.printStackTrace();
		}

		return null;

	}
	
	// page object pattern implementation with Reflection
		public static WebElement getWebElement(WebDriver driver, String elementName) {

				String locatorpath;
				arr = elementName.split("\\$");

				locatorpath = arr[1];

				// getting an instance for the method of By class that will be
				// stored in the
				// externalLinkMethod variable
				Method method = null;
				try {
					method = By.class.getDeclaredMethod(arr[0], String.class);
				} catch (NoSuchMethodException e) {
					log.error(arr[0] + " This method cannot be found");
					e.printStackTrace();
				} catch (SecurityException e) {
					log.error("This is Security Exception");
					e.printStackTrace();
				}

				// passing an instance of the concrete class Dummy that extends the
				// abstract class
				// By so that the method is present in JVM at runtime
				try {
					return driver.findElement((By) method.invoke(new Dummy(), locatorpath));
				} catch (IllegalAccessException e) {
					log.error("Illegal Access Exception");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					log.error("Illegal Argument Exception in Method: " + method);
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					log.error("Invocation Target Exception");
					e.printStackTrace();
				}


			return null;

		}

}
