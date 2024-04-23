package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
WebDriver driver;
	public OrderPage(WebDriver driver) {
		this.driver=driver;
	}
	By username=By.id("username");
	By pwd=By.id("password");

  public void Order(){
  System.out.println("this is order testing");
  }
}
