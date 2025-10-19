package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps"},
		tags = "",
		plugin = {
				"pretty",
		        "html:target/cucumber-reports/cucu-html-report.html",
		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"	
		}
		)
public class testRunner extends AbstractTestNGCucumberTests {
	
}
