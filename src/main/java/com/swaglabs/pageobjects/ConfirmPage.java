package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPage {
	
	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".complete-header")
	WebElement confirmmessage;
	
	public String getConfirmMessage() {
		String confirmMessage = confirmmessage.getText();
		return confirmMessage;
	}

}
