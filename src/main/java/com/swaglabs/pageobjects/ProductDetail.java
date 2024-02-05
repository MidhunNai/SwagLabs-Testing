package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetail {
	
	WebDriver driver;
	
	public ProductDetail(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class=\"inventory_details_name large_size\"]")
	WebElement productNameelement;
	
	public String getProductName() {
		return productNameelement.getText();
	}
}
