package com.swaglabs.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	WebDriver driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menuElement;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logoutElement;
	

	public void waitForElementToAppear (By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	
	}
	
	
	By logoutButton = By.id("logout_sidebar_link");
	
	public void logotApp() {
		menuElement.click();
		waitForElementToAppear(logoutButton);
		logoutElement.click();
	}
	
}
