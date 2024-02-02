package com.swaglabs.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import com.swaglabs.basetest.BaseTest;
import com.swaglabs.pageobjects.ProductPage;

public class TestProductPage extends BaseTest{
	
	//Testing Product Display
	@Test(dataProvider = "getData") 
	public void testProductDisplay(HashMap<String,String> input) {
		ProductPage productPage = login.loginApp(input.get("validUsername"), input.get("validPassword"));
		String productName = productPage.getSingleProduct(input.get("productName"));
		//System.out.println(productName);
		AssertJUnit.assertTrue(productName.equalsIgnoreCase(input.get("productName")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap <String, String>> data = getJsonDataToHashmap(System.getProperty("user.dir")+"/src/main/java/com/swaglabs/resources/LoginDetail.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
}
