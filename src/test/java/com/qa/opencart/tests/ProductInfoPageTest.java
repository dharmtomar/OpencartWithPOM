package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoPageTestSetup() {
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] productImgCountData() {
		return new Object[][] { { "macbook", "MacBook", 5 }, { "macbook", "MacBook Air", 4 },
				{ "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}

	@DataProvider(name = "productdatafromexcel")
	public Object[][] productImgCountDataFromExcel() {
		return ExcelUtil.getTestData("imgcountdata");
	}

	@Test(dataProvider = "productdatafromexcel")
	public void productImgCountTest(String productKey, String productName, String imgCount) {
		resPage = accPage.doSearchItem(productKey);
		ProductInfoPage = resPage.selectProduct(productName);
		Assert.assertEquals(ProductInfoPage.getProductImgCount(), Integer.parseInt(imgCount));
	}

	@DataProvider
	public Object[][] getProductHeaderData() {
		return new Object[][] { { "macbook", "MacBook" }, { "samsung", "Samsung Galaxy Tab 10.1" } };
	}
	
	@DataProvider
	public Object[][] getProductHeaderDataFromExcel() {
		return ExcelUtil.getTestData("headerdata");
	}

	@Test(dataProvider = "getProductHeaderDataFromExcel")
	public void productHeaderTest(String productKey, String productName) {
		resPage = accPage.doSearchItem(productKey);
		ProductInfoPage = resPage.selectProduct(productName);
		Assert.assertEquals(ProductInfoPage.getProductHeader(), productName);
	}

	@Test
	public void getProductDetailsTest() {
		resPage = accPage.doSearchItem("macbook");
		ProductInfoPage = resPage.selectProduct("MacBook");
		Map<String, String> productInfoMap = ProductInfoPage.getProductMap();
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 16");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "600");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("price"), "$602.00");
		softAssert.assertEquals(productInfoMap.get("etaxPrice"), "$500.00");
		softAssert.assertAll();
	}

	@Test
	public void addToCartTest() {
		resPage = accPage.doSearchItem("macbook");
		ProductInfoPage = resPage.selectProduct("MacBook");
		ProductInfoPage.doAddToCart(2);
		Assert.assertTrue(ProductInfoPage.addToCartMsg());
	}
}
