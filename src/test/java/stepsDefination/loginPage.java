package stepsDefination;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import Utils.Playwright_webDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginPage extends Playwright_webDriver{
	//Feature file Integration
	Page page = Playwright_webDriver.getPage();
	
	//Feature file Integration
	@Given("User navigate to website URL")
	public void user_navigate_to_website_url() {
		page.navigate("https://practice.expandtesting.com/login");
		Assert.fail();
	}

	@When("user validates the website title")
	public void user_validates_the_website_title() {
		String title = page.title();
		System.out.println("Page Title :" + title);
	}

	@Then("user enters username and password")
	public void user_enters_username_and_password(DataTable dataTable) {
		List<Map<String, String>> value = dataTable.asMaps();
		String username = value.get(0).get("username");
		String password = value.get(0).get("password");
		page.locator("//input[@id=\"username\"]").fill(username);
		page.locator("//input[@id=\"password\"]").fill(password);
	}

	@Then("user clicks on the submit button")
	public void user_clicks_on_the_submit_button() {
		page.click("//button[@id=\"submit-login\"]");
}
	
}