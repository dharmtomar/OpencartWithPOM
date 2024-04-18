
package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> thLocal = new ThreadLocal<WebDriver>();

	@Step("Initialyzing Driver with ThreadLocal.........")
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// System.out.pri	ntln("Launching the browser- " + browserName);
		Log.info("Launching the browser-  " + browserName);
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.chromeOptions());
			thLocal.set(new ChromeDriver(optionsManager.chromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(optionsManager.firefoxOptions());
			thLocal.set(new FirefoxDriver(optionsManager.firefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver(optionsManager.edgeOptions());
			thLocal.set(new EdgeDriver(optionsManager.edgeOptions()));
			break;
		
		default:
			// System.out.println("Browsername is incorrect, please pass corect one-- " +
			// browserName);
			Log.error("Browsername is incorrect, please pass corect one-- " + browserName);
			throw new BrowserExceptions("No BROWSER FOUND"+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static WebDriver getDriver() {
		return thLocal.get();
	}

	@Step("property initialyzing.... ")
	public Properties initProp() {

		FileInputStream fis = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("Running tests on env- " + envName);
		try {
			if (envName == null) {
				System.out.println("no env passed so running test on QA env");
				fis = new FileInputStream(new File("./src/test/resources/config/config.qa.properties"));
			} else {
				switch (envName.toLowerCase().trim()) {
				case "dev":
					fis = new FileInputStream(new File("./src/test/resources/config/config.dev.properties"));
					break;
				case "qa":
					fis = new FileInputStream(new File("./src/test/resources/config/config.qa.properties"));
					break;
				case "stage":
					fis = new FileInputStream(new File("./src/test/resources/config/config.stage.properties"));
					break;
				case "uat":
					fis = new FileInputStream(new File("./src/test/resources/config/config.uat.properties"));
					break;
				case "prod":
					fis = new FileInputStream(new File("./src/test/resources/config/config.prod.properties"));
					break;
				default:
					System.out.println("Env Name is not correct, please pass correct one- " + envName);
					throw new FrameworkException(AppErrors.ENV_NOT_FOUND);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	@Step("Getting screenshot.....")
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
