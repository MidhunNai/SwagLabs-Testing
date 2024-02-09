package com.swaglabs.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
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
	WebElement productNameElement;
	
	@FindBy(xpath="//div[@class=\"inventory_details_desc_container\"]")
	WebElement detailContainerElement;
	
	@FindBy(css=".inventory_details_img")
	WebElement imageElement;
	
	@FindBy(css=".btn_inventory")
	WebElement addAndRemoveToCartElement;
	
	@FindBy(id="back-to-products")
	WebElement backToProductElement;
	
	
	public String[] getProductDetails() {
		ArrayList<String> productDetailsList = new ArrayList<>();
		productDetailsList.add(detailContainerElement.findElement(By
				.cssSelector(".inventory_details_name")).getText());
		productDetailsList.add(detailContainerElement.findElement(By
				.cssSelector(".inventory_details_desc")).getText());
		productDetailsList.add(detailContainerElement.findElement(By
				.cssSelector(".inventory_details_price")).getText());
		String[] productDetails = productDetailsList.toArray(new String[0]);
		return productDetails;
	}
	
	public WebElement getProductImage() {
		WebElement productImage = imageElement;
		return productImage;
	}
	
	public void addAndRemoveToCart() {
		addAndRemoveToCartElement.click();
	}
	
	public void goBackToProductPage() {
		backToProductElement.click();
	}

}
