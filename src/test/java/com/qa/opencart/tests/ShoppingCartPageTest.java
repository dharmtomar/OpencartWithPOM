package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ShoppingCartPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoPageTestSetup() {
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void shoppingCartPageTitleTest() {
		resPage = accPage.doSearchItem("macbook");
		ProductInfoPage = resPage.selectProduct("MacBook");
		ProductInfoPage.doAddToCart(1);
		cartPage = ProductInfoPage.goToShpopingCart();
		String shoppingCartTitle = cartPage.getShpopingCartPageTitle();
		Assert.assertEquals(shoppingCartTitle, Constants.Shopping_Cart_Page_Title);

	}

	@Test
	public void totalPriceTest() {
		resPage = accPage.doSearchItem("macbook");
		ProductInfoPage = resPage.selectProduct("MacBook");
		ProductInfoPage.doAddToCart(1);
		cartPage = ProductInfoPage.goToShpopingCart();
		Assert.assertEquals(cartPage.getTotalPrice(), "$602.00");
	}
}
