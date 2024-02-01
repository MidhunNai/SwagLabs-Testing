package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.ProductPage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginFunctionality {
	
	@Test //Positive Login with valid username and password
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
	
	@Test //Login with incorrect password
	public void testWithInvalidPassword() {
		String validUsername = "standard_user" ;
		String invalidPassword = "Password@123" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		String error = login.invalidLogin(validUsername, invalidPassword);
		Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"));;
		
	}
	
	@Test //Login with incorrect username
	public void testWithInvalidUsername() {
		String invalidUsername = "invaliduser" ;
		String validPassword = "secret_sauce" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		String error = login.invalidLogin(invalidUsername, validPassword);
		Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"));;
		
	}
	
	@Test //Login with blank fields
	public void testWithBlankFields() {
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		String error = login.loginWithBlankField();
		Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Username is required"));;
		
	}
	
	@Test //Login with locked out user
	public void testWithLockedUser() {
		
		String lockedUsername = "locked_out_user" ;
		String validPassword = "secret_sauce" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		String error = login.invalidLogin(lockedUsername, validPassword);
		Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out."));;
	}
	
	@Test //Positive Login with valid username and password
	public void testLogout() {
		
		String validUsername = "standard_user" ;
		String validPassword = "secret_sauce" ;
		
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		login.gotoApplication();
		login.loginApp(validUsername, validPassword);
		login.logotApp();
	}
	
	
	
}
