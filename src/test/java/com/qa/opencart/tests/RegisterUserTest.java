package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegisterUserTest extends BaseTest{

	@BeforeClass
	public void RegisterUserTestSetup() {
		RegisterUserPage=login.navigateToRegisterPage();
	}
	@DataProvider
	public Object[][] getUserRegisterTestData() {
		return new Object[][] {
			{"allu","arjun","1234567890","Test@12345","No"},
			{"pawan","kheda","1234567890","Test@12345","Yes"}		
		};
	}
	@DataProvider(name="userdataCSV")
	public Object[][] getUserRegisterTestDataFromCSV() {
		return CSVUtil.getTestDatafromCSV();
	}
	@DataProvider(name="userdataexl")
	public Object[][] getUserRegisterTestDataFromExcel() {
		return ExcelUtil.getTestData("register");
	}
	@Test(dataProvider = "userdataexl")
	public void registerUserTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		boolean flag=RegisterUserPage.doRegister(firstName,lastName,StringUtil.getRandomEmail(),telephone,password,subscribe);
		Assert.assertTrue(flag);
	}
}


