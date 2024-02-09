package com.swaglabs.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	WebDriver driver;
	WebDriverWait wait;
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
	}
	
	
	@FindBy(xpath="//div[@class=\"cart_item_label\"]")
	List<WebElement> productsInCart;
	
	@FindBy(id="continue-shopping")
	WebElement continueShoppingElement;
	
	private WebElement findProductByName(String productName) {
		System.out.println(productsInCart);
		return productsInCart.stream()
				.filter(product->product.findElement(By.xpath(".//div[@class=\"inventory_item_name\"]"))
						.getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}
	
	public String[] getCartDetails(String productName) {
		
		String[] cartDetails = new String[3];
		WebElement selectedProduct = findProductByName(productName);
		//System.out.println(selectedProduct);
		cartDetails[0] = selectedProduct.findElement(By.xpath(".//div[@class=\"inventory_item_name\"]")).getText();
		cartDetails[1] = selectedProduct.findElement(By.xpath(".//div[@class=\"inventory_item_desc\"]")).getText();
		cartDetails[2] = selectedProduct.findElement(By.xpath(".//div[@class=\"inventory_item_price\"]")).getText();
		
		return cartDetails;
	}
	
	//Get Remove button element
	public WebElement removeFromCart(String productName) {
		
		WebElement selectedProduct = findProductByName(productName);
		WebElement removeFromCartButton = selectedProduct.findElement(By.xpath("//div[@class=\"item_pricebar\"]/button"));
		return removeFromCartButton;
		
	}
	
	private boolean isElementDisplayed(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean isCartItemDisplayed() {
		boolean isElementDisplayed = isElementDisplayed(By.className("cart_item"));
		return isElementDisplayed;
	}
	
	public void goBackToProductPage() {
		continueShoppingElement.click();
	}
	
	public void goBackToProductDetailPage(String productName) {
		WebElement selectedProduct = findProductByName(productName);
		selectedProduct.findElement(By.xpath("//div[@class=\"inventory_item_name\"]")).click();
	}
	
}
