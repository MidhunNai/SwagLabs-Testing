package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver;
	OverviewPage overview;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="first-name")
	WebElement firstnameInput;
	
	@FindBy(id="last-name")
	WebElement lastnameInput;
	
	@FindBy(id="postal-code")
	WebElement zipInput;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	public void fillShippingDetails(String firstname, String lastname, String zip) {
		firstnameInput.sendKeys(firstname);
		lastnameInput.sendKeys(lastname);
		zipInput.sendKeys(zip);
	}
	
	public OverviewPage goToOverview() {
		continueButton.click();
		overview = new OverviewPage(driver);
		return overview;
	}

}
