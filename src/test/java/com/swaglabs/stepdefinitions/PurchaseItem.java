package com.swaglabs.stepdefinitions;
import io.cucumber.java.en.*;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.CheckoutPage;
import com.swaglabs.pageobjects.ConfirmPage;
import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.OverviewPage;
import com.swaglabs.pageobjects.ProductDetail;
import com.swaglabs.pageobjects.ProductPage;

public class PurchaseItem extends BaseTest{
	
	public LoginPage login;
	public ProductPage productPage;
	public ProductDetail productDetail;
	public CartPage cartPage;
	public CheckoutPage checkout;
	public OverviewPage overview;
	public ConfirmPage confirmPage;

		@Given("^I landed on Swaglab Website$")
	    public void iLandedOnSwaglabWebsite() throws IOException {
			login = launchApplication();
	    }

	    @Given("^Logged in with username (.+) and password (.+)$")
	    public void loggedInWithUsernameAndPassword(String username, String password) {
	    	productPage = login.loginApp(username, password);
	    }

	    @When("^I add the selected product (.+) to the cart$")
	    public void iAddTheSelectedProductToTheCart(String product) {
	    	WebElement addToCartbutton = productPage.addToCartElement(product);
			addToCartbutton.click();
		}

	    @And("^I view the product in the cart$")
	    public void iViewTheProductInTheCart() {
	    	cartPage = goToCart();   
	    }

	    @And("^I proceed to checkout$")
	    public void iProceedToCheckout() {
	    	checkout = cartPage.goToCheckout();
	    }

	    @And("^I provided firstname (.+) lastname (.+) and zip (.+) as shipping details$")
	    public void iProvidedFirstnameLastnameAndZipAsShippingDetails(String firstname, String lastname, String zip) {
	    	checkout.fillShippingDetails(firstname, lastname, zip);
	    	overview = checkout.goToOverview();
	    }

	    @And("^I confirm the order$")
	    public void iConfirmTheOrder() {
	    	confirmPage = overview.finishOrder();
	    }

	    @Then("^success message is displayed on confirmation page$")
	    public void successMessageIsDisplayedOnConfirmationPage() {
	    	String confirmmessage = confirmPage.getConfirmMessage();
	    	Assert.assertEquals(confirmmessage, "Thank you for your order!");
	    	driver.close();
	    }
}
