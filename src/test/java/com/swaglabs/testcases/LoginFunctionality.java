package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.ProductPage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginFunctionality {
	
	@Test
	public void testWithValidCredentials() {
		
		String validUsername = "standard_user" ;
		String validPassword = "secret_sauce" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		ProductPage productPage = login.loginApp(validUsername, validPassword);
		Assert.assertTrue(productPage.productText().equalsIgnoreCase("Products"));
		login.logotApp();
	}
	
	@Test
	public void testWithInvalidPassword() {
		String validUsername = "standard_user" ;
		String invalidPassword = "Password@123" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		String error = login.invalidLogin(validUsername, invalidPassword);
		Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"));;
		
	}
	
}
