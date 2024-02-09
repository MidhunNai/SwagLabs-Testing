package com.swaglabs.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage login;
	public CartPage cart;

	//Initializing the driver based on the driver name provided in config file.
	public WebDriver initializeDriver() throws IOException {
		
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/Configuration/config.properties");
		property.load(fis);
		String browserName = property.getProperty("browser");
		
		//Set Chrome options to run in headless mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
	
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser Name in Config File");
		}
		driver.manage().window().maximize();	
		return driver;
	}
	
	public CartPage goToCart() {
		driver.findElement(By.id("shopping_cart_container")).click();
		cart = new CartPage(driver);
		return cart;
	}
	
	//Converting JSON data to HashMap for data provider
	public List<HashMap<String, String>> getJsonDataToHashmap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap <String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.gotoApplication();
		return login;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	
	//Take screenshot
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"/ScreenShots/" + testCaseName +".png");
		System.out.println(file);
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "/ScreenShots/" + testCaseName +".png" ; 
	}
	
}
