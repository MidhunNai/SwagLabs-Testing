package com.swaglabs.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.ProductDetail;
import com.swaglabs.pageobjects.ProductPage;

public class TestDetailPage extends BaseTest {
	
	ProductPage productPage;
	ProductDetail productDetail;
	CartPage cartPage;
	
	//Test -1 Verifying Product Information Display
	@Test(dataProvider="getData")
	public void testProductDetails(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		String[] selectedProduct = productDetail.getProductDetails();
		String selectedProductName = selectedProduct[0];
		//System.out.println(selectedProductName);
		Assert.assertEquals(selectedProductName, input.get("productName"));
		String selectedProductDesc = selectedProduct[1];
		System.out.println(selectedProductDesc);
		Assert.assertEquals(selectedProductDesc, input.get("productDescription"));
		String selectedProductPrice = selectedProduct[2];
		System.out.println(selectedProductPrice);
		Assert.assertEquals(selectedProductPrice, input.get("productPrice"));
	}
	
	//Verify Product Image is displayed
	@Test(dataProvider = "getData")
	public void testProductImage(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		String[] selectedProduct = productDetail.getProductDetails();
		String selectedProductName = selectedProduct[0];
		//System.out.println(selectedProductName);
		Assert.assertEquals(selectedProductName, input.get("productName"));
		WebElement productImage = productDetail.getProductImage();
		Assert.assertTrue(productImage.isDisplayed(),"Product Image is not displayed");
	}
	
	//Test Add product to cart
	@Test(dataProvider = "getData",groups = {"Regression"})
	public void testaddTocart(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		productDetail.addAndRemoveToCart();
		cartPage = goToCart();
		String[] cartDetails = cartPage.getCartDetails(input.get("productName"));
		String productIncart = cartDetails[0];
		Assert.assertTrue(productIncart.equalsIgnoreCase(input.get("productName")));
	
	}
	
	//Test Remove product from cart
	@Test(dataProvider = "getData",groups = {"Regression"})
	public void testRemoveFromcart(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		productDetail.addAndRemoveToCart();
		cartPage = goToCart();
		String[] cartDetails = cartPage.getCartDetails(input.get("productName"));
		String productIncart = cartDetails[0];
		Assert.assertTrue(productIncart.equalsIgnoreCase(input.get("productName")));
		cartPage.goBackToProductDetailPage(input.get("productName"));
		productDetail.addAndRemoveToCart();
		cartPage = goToCart();
		boolean isCartItemDisplayedAfterRemoval = cartPage.isCartItemDisplayed();
		Assert.assertFalse(isCartItemDisplayedAfterRemoval, "Cart item is still displayed after removal");
	}
	
	//Testing back button on the product detail page
	@Test(dataProvider = "getData")
	public void testBackToProduct(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		productDetail.goBackToProductPage();
	}
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap <String,String>> data = getJsonDataToHashmap(System.getProperty("user.dir")+"/src/main/java/com/swaglabs/resources/LoginDetail.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
