package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By macbookimage=By.xpath("//img[@alt='MacBook']");
	private By myAccountLink=By.linkText("My Account");
	private By headers=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchButton=By.cssSelector("div#search button");
	private By productSearch=By.cssSelector("div.product-thumb");
	//By searchProduct=By.linkText("MacBook");

	@Step("product img exist check----")
	public boolean macbookImageExist() {
		return eleUtil.waitForElementVisible(macbookimage, 10).isDisplayed();
	}
	@Step("Myaccount link exist check----")
	public boolean MyAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, 5).isDisplayed();
	}
	@Step("search result count check----")
	public int getSearchResultCount() {
		int productcount = eleUtil.getElementsCount(productSearch);
		System.out.println("total product count is- "+productcount);
		return productcount;
	}
	@Step("Select a product----")
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("selecting the product- "+productName);
		eleUtil.waitForElementVisible(By.linkText(productName),10).click();
		return new ProductInfoPage(driver);
	}
}
