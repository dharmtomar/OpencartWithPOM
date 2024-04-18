package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By header = By.tagName("h1");
	private By productImages = By.cssSelector("a.thumbnail");
	private By productMataData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By ProductPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	private By addToCart = By.id("button-cart");
	private By addToCartText = By.xpath("//div[contains(text(),'Success: You have added')]");
	private By enterQuantity = By.id("input-quantity");
	private By shopingCart = By.linkText("shopping cart");

	@Step("Getting product img count...")
	public int getProductImgCount() {
		int imgCount = eleUtil.waitForElementsVisible(productImages, 10).size();
		System.out.println("total img are " + imgCount);
		return imgCount;

	}
@Step("Getting product header....")
	public String getProductHeader() {
		String headerText = eleUtil.doGetElementText(header);
		System.out.println(headerText);
		return headerText;
	}

	Map<String, String> productMap = new HashMap<String, String>();

	@Step("Getting product info...")
	public void getProductMetaData() {
		List<WebElement> productDetails = eleUtil.getElements(productMataData);
		for (WebElement e : productDetails) {
			String productInfoKey = e.getText().split(":")[0].trim();
			String productInfoValue = e.getText().split(":")[1].trim();
			productMap.put(productInfoKey, productInfoValue);
		}
	}
	@Step("Getting product price info...")
	public void getProductPriceData() {
		List<WebElement> productPriceDetails = eleUtil.getElements(ProductPriceData);
		String ProductPrice = productPriceDetails.get(0).getText();
		String etaxtPrice = productPriceDetails.get(1).getText().split(":")[1].trim();
		productMap.put("price", ProductPrice);
		productMap.put("etaxPrice", etaxtPrice);
	}

	public Map<String, String> getProductMap() {

		productMap.put("ProductHeader", getProductHeader());
		productMap.put("productImages", String.valueOf(getProductImgCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("product details-  \n" + productMap);
		return productMap;
	}
	@Step("Add to cart...with qty : {0}")
	public void doAddToCart(int quantity) {
		eleUtil.getElement(enterQuantity).clear();
		eleUtil.doSendKeys(enterQuantity, String.valueOf(quantity));
		eleUtil.doClick(addToCart);
	}

	public boolean addToCartMsg() {
		String text = eleUtil.waitForElementVisible(addToCartText, 5).getText();
		if (text.contains("Success: You have added")) {
			return true;
		} else {
			return false;
		}
	}
	@Step("Navigating to shoping cart page...")
	public ShoppingCartPage goToShpopingCart() {
		
		eleUtil.waitForElementVisible(shopingCart, 5).click();
		return new ShoppingCartPage(driver);
	}
}
