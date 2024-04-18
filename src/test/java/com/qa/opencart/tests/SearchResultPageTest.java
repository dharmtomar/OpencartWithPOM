package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultPageTest extends BaseTest {

	@BeforeClass
	public void SearchResultPageTestSetup() {
		accPage=login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {{"macbook",3},{"samsung",2}};
	}
	@Test(dataProvider = "getProductCountData")
	public void searchItemTest(String searchKey,int count) {
		resPage=accPage.doSearchItem(searchKey);
		Assert.assertEquals(resPage.getSearchResultCount(), count);
		
	}
	//@Test(priority = 1)
	public void macbookImageTest() {
		Assert.assertTrue(resPage.macbookImageExist());
	}
	@Test(priority = 1)
	public void accountLinkExist() {
		Assert.assertTrue(resPage.MyAccountLinkExist());
	}
	
}
