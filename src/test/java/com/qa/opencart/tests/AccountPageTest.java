package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountPageTest extends BaseTest{

	@BeforeClass
	public void accountSetup() {
		accPage=login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Description("Tesing acc page title-----")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accountPageTitleTest() {
		String actTitle=accPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, Constants.acc_Page_Title_Fraction);
	}
	@Description("Tesing acc page url-----")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accountPageURLTest() {
		String actURL=accPage.getAccountPageURL();
		Assert.assertEquals(actURL.contains(Constants.acc_Page_URL_Fraction),true);
	}
	@Description("Tesing presence of logout button-----")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isLogoutButtonExist() {
		Assert.assertTrue(accPage.LogoutButtonExist());
	}
	@Description("Tesing presence of MyAccountLink-----")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isMyAccountLinkExist() {
		Assert.assertTrue(accPage.MyAccountLinkExist());
	}
	@Description("Tesing headers-----")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void verifyHeaders() {
		List<String> headers=accPage.getHeaders();
		System.out.println(headers);
	}
	@Description("product searching-----")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void searchProduct() {
		accPage.doSearchItem("macbook");
		
	}
	
}
