package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
WebDriver driver; 
	
	public ProductPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class=\"header_secondary_container\"]/span")
	WebElement productHeading;
	
	@FindBy(xpath="//div[@class=\"header_label\"]/div")
	WebElement websiteName;
	
	@FindBy(xpath="//div[@class=\"inventory_item_name \"]")
	List<WebElement> productListElement;
	
	public String productText() {
		String productText = productHeading.getText();
		return productText;
	}
	
	public String getSingleProduct(String productName) {
		WebElement getSingleProduct = productListElement.stream()
		.filter(product->product.getText().equals(productName))
		.findFirst().orElse(null);
		return getSingleProduct.getText();
	}
	
	
	
}
