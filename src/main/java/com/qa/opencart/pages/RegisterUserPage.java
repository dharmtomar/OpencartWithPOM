package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegisterUserPage {

	WebDriver driver;
	ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPass = By.id("input-confirm");
	private By subscribe_Yes = By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']");
	private By subscribe_No = By.xpath("//input[@type='radio' and @name='newsletter' and @value='0']");
	private By privacy_Policy_checkbox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit']");
	private By registerConfirm = By.tagName("h1");
	private By logoutButton = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterUserPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("User register.....")
	public boolean doRegister(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPass, password);
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribe_Yes);
		} else {
			eleUtil.doClick(subscribe_No);
		}

		eleUtil.doClick(privacy_Policy_checkbox);
		eleUtil.doClick(continueButton);

		String regSuccessText = eleUtil.waitForElementVisible(registerConfirm, 10).getText();
		if (regSuccessText.equals(Constants.reg_Success_Msg_Text)) {
			eleUtil.doClick(logoutButton);
			eleUtil.doClick(registerLink);
			return true;
		}

		return false;
	}

}
