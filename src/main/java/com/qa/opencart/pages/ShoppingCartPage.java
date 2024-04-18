package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By shoppingCart=By.linkText("shopping cart");
	private By totalPrice=By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[4]/td[2]");
	
	public String getShpopingCartPageTitle() {
		return eleUtil.waitForTitleIs(Constants.Shopping_Cart_Page_Title, 10);
	}
	
	public String getTotalPrice() {
		String totalprice=eleUtil.getElement(totalPrice).getText();
		System.out.println("Total price of item is- "+totalprice);
		return totalprice;
	}
	
}
