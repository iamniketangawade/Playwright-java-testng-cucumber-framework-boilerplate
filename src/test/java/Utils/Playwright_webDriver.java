package Utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class Playwright_webDriver {

	private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<Playwright>();
	private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<Browser>();
	private static final ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<BrowserContext>();
	private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<Page>();

	//Browsers setup
	public static void OpenBrowserSetup(String browserName) {
		
		//Playwright 
		Playwright playwright = Playwright.create();
		playwrightThreadLocal.set(playwright);

		Browser browser = null;
		switch (browserName.toLowerCase()) {
		case "chrome":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
					.setChannel("chrome").setArgs(java.util.Arrays.asList("--start-maximized")));
			break;

		case "edge":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
					.setChannel("msedge").setArgs(java.util.Arrays.asList("--start-maximized")));
			break;

		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)
					.setArgs(java.util.Arrays.asList("--start-maximized")));
			break;

		default:
			throw new IllegalArgumentException("Invalid Browser is selected by User ::" +browserName);
			
		}
		
		//Browser 
		browserThreadLocal.set(browser);
		BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		
		//BrowserContext
		browserContextThreadLocal.set(browserContext);
		
		//Page
		Page page =browser.newPage();
		pageThreadLocal.set(page);
	}
	
//Page method 
	public static Page getPage() {
		return pageThreadLocal.get();
		
	}

//Closing setup
	public static void closeSetupBrowser() {
		if (pageThreadLocal.get() !=null) {
			pageThreadLocal.get().close();
		}
		if (browserContextThreadLocal.get() !=null) {
			browserContextThreadLocal.get().close();
		}
		if (browserThreadLocal.get() !=null) {
			browserThreadLocal.get().close();
		}
		if (playwrightThreadLocal.get() !=null) {
			playwrightThreadLocal.get().close();
		}
		
	}
	
	

}
