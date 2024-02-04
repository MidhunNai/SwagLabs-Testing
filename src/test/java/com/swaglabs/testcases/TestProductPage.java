package com.swaglabs.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.DataProvider;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.ProductPage;

public class TestProductPage extends BaseTest{
	
	ProductPage productPage = null;
	CartPage cartPage = null;
	
	//Testing Product Display
	@Test(dataProvider = "getData") 
	public void testProductDisplay(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		String productName = productPage.getSingleProduct(input.get("productName"));
		//System.out.println(productName);
		Assert.assertTrue(productName.equalsIgnoreCase(input.get("productName")));
		
	}
	
	@Test(dataProvider = "getData", groups = "testrun") 
	public void testProductDescription(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		String[] productDetails = productPage.getProductDetails(input.get("productName"));
		String productDescription = productDetails[0];
		//String productDescription = productPage.getProductDescription(input.get("productName"));
		Assert.assertTrue(productDescription.equalsIgnoreCase(input.get("productDescription")));
		String productPrice = productDetails[1];
		Assert.assertTrue(productPrice.equalsIgnoreCase(input.get("productPrice")));
		
	}
	
	//Add to cart single product
	@Test(dataProvider = "getData")
	public void testAddToCart(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		WebElement addToCartbutton = productPage.addToCartElement(input.get("productName"));
		addToCartbutton.click();
		cartPage = goToCart();
		String[] cartDetails = cartPage.getCartDetails(input.get("productName"));
		String productIncart = cartDetails[0];
		Assert.assertTrue(productIncart.equalsIgnoreCase(input.get("productName")));
	}
	
	//Remove single product from cart, from the product page
	@Test(dataProvider = "getData")
	public void removeProductFromCartInProductPage(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		WebElement addToCartbutton = productPage.addToCartElement(input.get("productName"));
		addToCartbutton.click();
		WebElement removeFromCartButton = productPage.removeButtonElement(input.get("productName"));
		removeFromCartButton.click();
		cartPage = goToCart();
		
	}
	
	//Add to cart multiple products
	@Test(dataProvider = "getData")
	public void testAddMultipleProduct(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		WebElement addToCartbutton = productPage.addToCartElement(input.get("product1"));
		addToCartbutton.click();
		addToCartbutton = productPage.addToCartElement(input.get("product2"));
		addToCartbutton.click();
		cartPage = goToCart();
		String[] cartDetails = cartPage.getCartDetails(input.get("product1"));
		String productIncart = cartDetails[0];
		Assert.assertTrue(productIncart.equalsIgnoreCase(input.get("product1")));
		cartDetails = cartPage.getCartDetails(input.get("product2"));
		productIncart = cartDetails[0];
		Assert.assertTrue(productIncart.equalsIgnoreCase(input.get("product2")));
	}
	
	//Test product sorting
	@Test(dataProvider = "getData")
	public void testProductSorting(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productPage.selectSortOrderZtoA();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap <String, String>> data = getJsonDataToHashmap(System.getProperty("user.dir")+"/src/main/java/com/swaglabs/resources/LoginDetail.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		//{{data.get(0)},{data.get(1)}}
	}
	
}
