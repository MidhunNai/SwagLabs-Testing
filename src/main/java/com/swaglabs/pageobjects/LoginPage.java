package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglabs.base.BaseClass;

public class LoginPage extends BaseClass {
	
	WebDriver driver; 
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name")
	WebElement usernameElement;
	
	@FindBy(id="password")
	WebElement passwordElement;
	
	@FindBy(id="login-button")
	WebElement loginButtonElement;
	
	@FindBy(xpath="//div[@class=\"error-message-container error\"]/h3")
	WebElement errorElement;
	
	public ProductPage loginApp(String username, String password) {
		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		loginButtonElement.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public String invalidLogin(String invalidUsername, String invalidPassword) {
		usernameElement.sendKeys(invalidUsername);
		passwordElement.sendKeys(invalidPassword);
		loginButtonElement.click();
		String errorMessage = errorElement.getText();
		return errorMessage;
	}
	
	public void gotoApplication() {
		driver.get("https://www.saucedemo.com/");
	}
	
}
