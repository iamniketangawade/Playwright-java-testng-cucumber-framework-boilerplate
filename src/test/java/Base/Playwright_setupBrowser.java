package Base;

import io.cucumber.java.Scenario;
import Utils.Playwright_webDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Playwright_setupBrowser {
	
	
	// --------------Open a browser---------------------
	@Before
	ublic void setup() {
		String browser = System.getProperty("browser", "chrome");
		Playwright_webDriver.OpenBrowserSetup(browser);

	}

	// --------------Close a browser---------------------
	@After
	public void tearDown(Scenario scenario) {

			// ✅ Close browser properly
			Playwright_webDriver.closeSetupBrowser();
}

}