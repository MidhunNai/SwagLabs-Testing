package com.swaglabs.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/java/com/swaglabs/cucumber",
	    glue = "com.swaglabs.stepdefinitions",
	    monochrome = true,
	    plugin = {"html:target/cucumber.html"},
	    tags = "@Regression"
	)
	public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	}
