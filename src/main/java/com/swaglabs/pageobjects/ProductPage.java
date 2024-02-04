package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	
	@FindBy(xpath="//div[@class=\"inventory_item_description\"]")
	List<WebElement> productCardElement;
	
	@FindBy(id="add-to-cart-sauce-labs-backpack")
	WebElement addToCartElement;
	
	@FindBy(xpath="//select[@class=\"product_sort_container\"]")
	WebElement dropdownElement;
	
	public String productText() {
		String productText = productHeading.getText();
		return productText;
	}
	
	private WebElement findProductByName(String productName) {
		return productCardElement.stream()
				.filter(product->product.findElement(By.xpath(".//div[@class=\"inventory_item_name \"]"))
						.getText().equals(productName))
				.findFirst().orElse(null);
		
	}
	
	public String getSingleProduct(String productName) {
		WebElement getSingleProduct = productListElement.stream()
		.filter(product->product.getText().equals(productName))
		.findFirst().orElse(null);
		return getSingleProduct.getText();
	}
	
	public String[] getProductDetails(String productName) {
		
		String[] productDetails = new String[2];
		WebElement selectedProduct = findProductByName(productName);
		productDetails[0] = selectedProduct.findElement(By.xpath(".//div[@class=\"inventory_item_desc\"]")).getText();
		productDetails[1] = selectedProduct.findElement(By.xpath(".//div[@class=\"inventory_item_price\"]")).getText();
		return productDetails;
		
	}
	
	//Get Add to cart button element
	public WebElement addToCartElement(String productName) {
		WebElement selectedProduct = findProductByName(productName);
		WebElement addToCartButton = selectedProduct.findElement(By.xpath(".//div[@class=\"pricebar\"]/button"));
		return addToCartButton;	
	}
	
	//Get Remove from cart button element
	public WebElement removeButtonElement(String productName) {
		WebElement selectedProduct = findProductByName(productName);
		WebElement removeButton = selectedProduct.findElement(By.xpath(".//div[@class=\"pricebar\"]/button"));
		return removeButton;
		
	}
	
	public void addToCart() {
		addToCartElement.click();
	}
	
	public void selectSortOrderZtoA() {
		Select select = new Select(dropdownElement);
		select.selectByVisibleText("Name (Z to A)");
	}
	
	public void selectSortOrderLowtoHigh() {
		Select select = new Select(dropdownElement);
		select.selectByVisibleText("Price (low to high)");
	}
	
	public void selectSortOrderHightoLow() {
		Select select = new Select(dropdownElement);
		select.selectByVisibleText("Price (high to low)");
	}
	
	
	
	
	
}
