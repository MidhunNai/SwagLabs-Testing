package com.swaglabs.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;

import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.ProductPage;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class TestLoginFunctionality extends BaseTest{
	
	//Positive Login with valid username and password
	@Test(dataProvider = "getData",groups = {"Regression"}) 
	public void testWithValidCredentials(HashMap<String,String> input) throws IOException {
		
		//String validUsername = "standard_user" ;
		//String validPassword = "secret_sauce" ;
		
		//WebDriver driver = new ChromeDriver();
		//LoginPage login = launchApplication();
		ProductPage productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		AssertJUnit.assertTrue(productPage.productText().equalsIgnoreCase("Products"));
		login.logotApp();
	}
	
	//Login with incorrect password
	@Test (dataProvider = "getData",groups = {"Regression"}, retryAnalyzer=com.swaglabs.listeners.Retry.class)
	public void testWithInvalidPassword(HashMap<String,String> input) throws IOException {
		
		login.gotoApplication();
		String error = login.invalidLogin(input.get("validUsername"), input.get("invalidPassword"));
		AssertJUnit.assertTrue(error.equalsIgnoreCase("Username and password do not match any user in this service"));;
		
	}
	
	//Login with incorrect username
	@Test (dataProvider = "getData")
	public void testWithInvalidUsername(HashMap<String,String> input) throws IOException {
		login.gotoApplication();
		String error = login.invalidLogin(input.get("invalidUsername"), input.get("validPassword"));
		AssertJUnit.assertTrue(error.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"));;
		
	}
	
	//Login with blank fields
	@Test
	public void testWithBlankFields() throws IOException {
		
		login.gotoApplication();
		String error = login.loginWithBlankField();
		AssertJUnit.assertTrue(error.equalsIgnoreCase("Epic sadface: Username is required"));;
		
	}
	
	//Login with locked out user
	@Test(dataProvider = "getData") 
	public void testWithLockedUser(HashMap<String,String> input) throws IOException {
		
		login.gotoApplication();
		String error = login.invalidLogin(input.get("lockedUsername"), input.get("validPassword"));
		AssertJUnit.assertTrue(error.equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out."));;
	}
	
	//Testing Logout
	@Test(dataProvider = "getData") 
	public void testLogout(HashMap<String,String> input) throws IOException {
		
		login.gotoApplication();
		login.loginApp(input.get("validUsername"), input.get("validPassword"));
		login.logotApp();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("validUsername", "standard_user");
//		map.put("validPassword", "secret_sauce");
		List<HashMap <String, String>> data = getJsonDataToHashmap(System.getProperty("user.dir")+"/src/main/java/com/swaglabs/resources/LoginDetail.json");
		return new Object [][] {{data.get(0)}};
	}	
	
}
