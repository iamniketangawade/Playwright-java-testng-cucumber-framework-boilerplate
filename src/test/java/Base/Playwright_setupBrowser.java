package Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import Utils.Playwright_webDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Playwright_setupBrowser {

	//--------------Open a browser---------------------
	@Parameters("browser")
	@Before
	public void setup(String browser) {
		Playwright_webDriver.OpenBrowserSetup(browser);
	}

	//--------------Close a browser---------------------
	@After
	public void tearDown() {
		Playwright_webDriver.closeSetupBrowser();
	}
	
	
	

}
