package Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private static final String Screenshot_Capture = "C:\\Users\\Niketan Gawade\\eclipse-workspace\\Playwright-java-testng-cucumber-framework-boilerplate\\target\\Screeshot&Video\\Screenshots";
	private static final String Video_Capture = "C:\\Users\\Niketan Gawade\\eclipse-workspace\\Playwright-java-testng-cucumber-framework-boilerplate\\target\\Screeshot&Video\\Videos";

	// Browsers setup
	public static void OpenBrowserSetup(String browserName) {

		// Playwright
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
			throw new IllegalArgumentException("Invalid Browser is selected by User ::" + browserName);

		}

		// Browser
		browserThreadLocal.set(browser);
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

		// BrowserContext
		browserContextThreadLocal.set(browserContext);

		// Page
		Page page = browser.newPage();
		pageThreadLocal.set(page);
	}

//Page method 
	public static Page getPage() {
		return pageThreadLocal.get();

	}

//Closing setup
	public static void closeSetupBrowser() {
		if (pageThreadLocal.get() != null) {
			pageThreadLocal.get().close();
		}
		if (browserContextThreadLocal.get() != null) {
			browserContextThreadLocal.get().close();
		}
		if (browserThreadLocal.get() != null) {
			browserThreadLocal.get().close();
		}
		if (playwrightThreadLocal.get() != null) {
			playwrightThreadLocal.get().close();
		}

	}

//-----------------------------Screenshot Code---------------------------------------	
	// Takes a screenshot of the current page and saves it with timestamp.
	public static String takeScreenshot(Page page, String testName) {
		try {
			Files.createDirectories(Paths.get(Screenshot_Capture));

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotPath = Screenshot_Capture + testName + "_" + timestamp + ".png";

			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));

			System.out.println("✅ Screenshot captured: " + screenshotPath);
			return screenshotPath;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("❌ Failed to capture Screenshot: " + e.getMessage());
			return null;
		}
	}
//-------------------------------------------------------------------------------------	

//---------------------------------Video Capture--------------------------------------	
	public static BrowserContext captureVideo(Browser browser, String testname) {
		try {
			Files.createDirectories(Paths.get(Video_Capture));

			Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(null)
					.setRecordVideoDir(Paths.get(Video_Capture)).setRecordVideoSize(1366, 768);

			BrowserContext browsercontext = browser.newContext(contextOptions);
			System.out.println("🎥 ✅ Video recording started for test : " + testname);

			return browsercontext;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("🎥 ❌ Failed Video recording started for test : " + testname);
			return null;
		}
	}

	public static void saveVideo(BrowserContext browsercontext) {
		try {
			Path videoPath =browsercontext.pages().get(0).video().path();
			System.out.println("🎥 ✅ Video Saved at : " + videoPath.toAbsolutePath());
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("🎥 ❌ Failed to save video: " + e.getMessage());
		}
	}
	
	
	
}
