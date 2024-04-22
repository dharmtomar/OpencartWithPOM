package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class E2ETest {
WebDriver driver;
	public E2ETest(WebDriver driver) {
		this.driver=driver;
	}
	By username=By.id("username");
	By pwd=By.id("password");
}
