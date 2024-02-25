package com.swaglabs.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.DataProvider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.ProductDetail;
import com.swaglabs.pageobjects.ProductPage;

public class TestProductPage extends BaseTest{
	
	ProductPage productPage = null;
	CartPage cartPage = null;
	ProductDetail productDetail = null;
	
	//Testing Product Display
	@Test(dataProvider = "getData",groups = {"Regression"}) 
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
	@Test(dataProvider = "getData",groups = {"Regression"})
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
		cartPage = goToCart();
		boolean isCartItemDisplayedBeforeRemoval = cartPage.isCartItemDisplayed();
		Assert.assertTrue(isCartItemDisplayedBeforeRemoval, "Cart item was not displayed before removal");
		cartPage.goBackToProductPage();
		WebElement removeFromCartButton = productPage.removeButtonElement(input.get("productName"));
		removeFromCartButton.click();
		cartPage = goToCart();
		boolean isCartItemDisplayedAfterRemoval = cartPage.isCartItemDisplayed();
		Assert.assertFalse(isCartItemDisplayedAfterRemoval, "Cart item is still displayed after removal");
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
	
	//Remove from cart multiple products
		@Test(dataProvider = "getData",groups = {"Regression"})
		public void testRemoveMultipleProduct(HashMap<String,String> input) {
				productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
				WebElement addToCartbutton = productPage.addToCartElement(input.get("product1"));
				addToCartbutton.click();
				addToCartbutton = productPage.addToCartElement(input.get("product2"));
				addToCartbutton.click();
				cartPage = goToCart();
				boolean isCartItemDisplayedBeforeRemoval = cartPage.isCartItemDisplayed();
				Assert.assertTrue(isCartItemDisplayedBeforeRemoval, "Cart item was not displayed before removal");
				cartPage.goBackToProductPage();
				WebElement removeFromCartButton = productPage.removeButtonElement(input.get("product1"));
				removeFromCartButton.click();
				removeFromCartButton = productPage.removeButtonElement(input.get("product2"));
				removeFromCartButton.click();
				cartPage = goToCart();
				boolean isCartItemDisplayedAfterRemoval = cartPage.isCartItemDisplayed();
				Assert.assertFalse(isCartItemDisplayedAfterRemoval, "Cart item is still displayed after removal");
			}	
	
	//Test product sorting
	@Test(dataProvider = "getData",groups = {"Regression"})
	public void testProductSorting(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productPage.selectSortOrderZtoA();
		List<String> sortedOnUi = productPage.getAllProductsName();
		List<String> sortedProductName = productPage.getAllProductsName();
		Collections.sort(sortedProductName, Collections.reverseOrder());
		Assert.assertEquals(sortedOnUi, sortedProductName, "Products are not sorted correctly Z-A");
		productPage.selectSortOrderLowtoHigh();
		List<Double> productPrices = productPage.getPriceOfAllProducts();
		List<Double> sortedProductPrices = new ArrayList<>(productPrices);
		Collections.sort(sortedProductPrices);
		Assert.assertEquals(productPrices, sortedProductPrices, "Products are not sorted correctly by price (low to high)");
		productPage.selectSortOrderHightoLow();
		productPrices = productPage.getPriceOfAllProducts();
		sortedProductPrices = new ArrayList<>(productPrices);
		Collections.sort(sortedProductPrices, Collections.reverseOrder());
		Assert.assertEquals(productPrices, sortedProductPrices, "Products are not sorted correctly by price (high to low)");
	}
	
	//Navigate to product detail page by clicking Product Name
	@Test(dataProvider="getData")
	public void testNavigationToProductDetail(HashMap<String,String> input) {
		productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		productDetail = productPage.goToProductDetail(input.get("productName"));
		String[] product = productDetail.getProductDetails();
		String selectedProduct = product[0];
		//System.out.println(product);
		Assert.assertTrue(selectedProduct.equalsIgnoreCase(input.get("productName")));	
	}
	//Navigate to product detail page by clicking Product Image
		@Test(dataProvider="getData")
		public void goToProductDetailByImage(HashMap<String,String> input) {
			productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
			productDetail = productPage.goToProductDetailByImage(input.get("productName"));
			String[] product = productDetail.getProductDetails();
			//System.out.println(product);
			String selectedProduct = product[0];
			Assert.assertTrue(selectedProduct.equalsIgnoreCase(input.get("productName")));	
		}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap <String, String>> data = getJsonDataToHashmap(System.getProperty("user.dir")+"/src/main/java/com/swaglabs/resources/LoginDetail.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		//{{data.get(0)},{data.get(1)}}
	}
	
}
