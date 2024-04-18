package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic: opencart features testing...")
@Feature("Feature: login feature.....")
@Story("US 101: design login page...")
public class LoginPageTest extends BaseTest {
	WebDriver driver;
	// Properties prop;

	@Description("checking the login  page title---")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = login.getLoginPageTitle();
		Assert.assertEquals(title, Constants.login_Page_Title_Fraction, AppErrors.APP_TITLE_NOT_FOUND);
	}

	@Description("checking the login page URL---")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String URL = login.getLoginPageURL();
		Assert.assertEquals(URL.contains(Constants.login_Page_URL_Fraction), true, AppErrors.APP_URL_NOT_FOUND);
	}

	@Description("checking user is able to login---")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), Constants.acc_Page_Title_Fraction,
				AppErrors.APP_TITLE_NOT_FOUND);
	}
}
