package stepsDefination;

import com.microsoft.playwright.Page;

import Utils.Playwright_webDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class radioButton {
	//Feature file : Radio button selection
	Page page = Playwright_webDriver.getPage();

	@Given("the user is on the radio button page")
	public void user_navigate_to_website_url() {
		page.navigate("https://practice.expandtesting.com/radio-buttons");
	}

	@When("the user selects the {string} option")
	public void select_radioOption(String radio_option) {
		String color=radio_option;
		page.locator("input[type='radio'][value='" + color + "']").check();
	}
	
	@Then("the {string} radio button should be selected")
	public void select_radioOptionValidation(String radio_option) {
		String color=radio_option;
		boolean isChecked =page.locator("input[type='radio'][value='" + color + "']").isChecked();
		System.out.println("Radio button :"+ color +" is selected:"+isChecked );
	}
}