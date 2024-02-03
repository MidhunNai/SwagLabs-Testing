package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class=\"cart_item_label\"]")
	List<WebElement> productsInCart;
	
	private WebElement findProductByName(String productName) {
		System.out.println(productsInCart);
		return productsInCart.stream()
				.filter(product->product.findElement(By.xpath("//div[@class=\"inventory_item_name\"]"))
						.getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}
	
	public String[] getCartDetails(String productName) {
		
		String[] cartDetails = new String[3];
		WebElement selectedProduct = findProductByName(productName);
		System.out.println(selectedProduct);
		cartDetails[0] = selectedProduct.findElement(By.xpath("//div[@class=\"inventory_item_name\"]")).getText();
		cartDetails[1] = selectedProduct.findElement(By.xpath("//div[@class=\"inventory_item_desc\"]")).getText();
		cartDetails[2] = selectedProduct.findElement(By.xpath("//div[@class=\"inventory_item_price\"]")).getText();
		
		return cartDetails;
	}
	
	//Get Remove button element
	public WebElement removeFromCart(String productName) {
		
		WebElement selectedProduct = findProductByName(productName);
		WebElement removeFromCartButton = selectedProduct.findElement(By.xpath("//div[@class=\"item_pricebar\"]/button"));
		return removeFromCartButton;
		
	}
	
}
