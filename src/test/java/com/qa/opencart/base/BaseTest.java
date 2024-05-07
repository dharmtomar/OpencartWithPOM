package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.factory.OptionsManager;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterUserPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.pages.ShoppingCartPage;

import io.qameta.allure.Step;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage login;
	protected RegisterUserPage RegisterUserPage;
	protected AccountPage accPage;
	protected SearchResultPage resPage;
	protected ProductInfoPage ProductInfoPage;
	protected ShoppingCartPage cartPage;
	protected SoftAssert softAssert;
	protected OptionsManager ops;
	
	@Step("launching the browser : {0}")
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browsername,String browserVersion,String testName) {
		
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browsername!=null) {
		prop.setProperty("browser", browsername);
		prop.setProperty("browserversion", browserVersion);
		prop.setProperty("testname", testName);
		}
			
		driver=df.initDriver(prop);
		
		login=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@Step("closing the browser.....")
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
