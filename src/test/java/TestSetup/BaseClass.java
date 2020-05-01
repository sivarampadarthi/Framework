package TestSetup;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import SupportLibraries.Reports;
import SupportLibraries.Util;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static String testCaseName = "";
	public static String ResultFolderPath;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	public static String systemDir = System.getProperty("user.dir");
	public static Properties prop;
	public static ExtentReports extent;
	public static String driverPath;

	/**
	 * This methoid is going to execute before batch execution
	 */

	@BeforeSuite
	public void initializeWebdriver() {
		System.out.println("Under @BeforeSuite");
		String currentDate = Util.dateString_MM_DD_YYYY();
		ResultFolderPath = systemDir + "/test-output" + "/" + currentDate + "/";
		Util.createFolder(ResultFolderPath);
		prop = Util.readProperty();
		htmlReporter = Reports.StartHtmlReport(htmlReporter, ResultFolderPath);
		// htmlReporter = Report.StartHtmlReport(htmlReporter,
		// prop.getProperty("browserName"), ResultFolderPath);
		extent = Reports.StartExtentReport(htmlReporter, extent);

	}

	/**
	 * This method is going to execute before each method
	 * 
	 * @param testMethod
	 */
	@BeforeMethod
	public void beforeTestMethod(Method testMethod) {

		System.out.println("Under @BeforeMerhod");
		testCaseName = testMethod.getName();
	}

	/**
	 * This method is going to execute before Class once
	 * 
	 * @param context
	 */
	@BeforeClass
	public void beforeClass(ITestContext context) {
		try {
			String className = this.getClass().getName();
			System.out.println("############ Test Execution Started for " + className + "#############");
			test = Reports.testCreate(extent, "Test==> :" + className);
			Reports.startTest(className);
			System.out.println("XXXXXX======== Test Executed Started for " + className + "==========XXXXXXXXX");

			String reportNamePath = Reports.reportName;
			System.out.println(" reportName>>" + reportNamePath);

			/*
			 * //Html Parser File input = new File (reportNamePath); Document html
			 * =Jsoup.parse(input,"UTF-8");
			 * 
			 * String invidualTestStatus =
			 * html.getElementsByAttributeValueContaining("class","test-status right").text(
			 * ); System.out.println(invidualTestStatus); Reports.endReport(extent);
			 */

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * This method is going to execute after Class Once
	 * 
	 * @param context
	 */
	@AfterClass
	public void quitReport(ITestContext context) {

		System.out.println("########## Test Execution completed for " + context.getName() + "##################");

	}

	/**
	 * This method is going to execute after each Test case / Method
	 * 
	 * @param result
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) {

		System.out.println("Method name : " + result.getMethod().getMethodName());
		Reports.endReport(extent);
	}

	/**
	 * This method is going to execute after completed batch execution once
	 */
	@AfterSuite
	public void afterSuite() {

		System.out.println("############## End of Suite #################");
		Reports.endReport(extent);
	}

	public static WebDriver startBrowser(String browser) {
		
		
		try {
			Reports.infoTest(browser + " Browser is launching" );
			
			if(browser.toLowerCase().equals("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				driverPath = systemDir + "\\drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);// New change

				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				options.addArguments("--disable-extensions"); // to disable browser extension popup

				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				try {
					driver = new ChromeDriver();
					Reports.infoTest("Browser Launched");
					driver.manage().window().maximize();
					Util.hardWait(1000);
				}catch(Exception e) {
					System.out.println("Failed to invoke browser " + e.getMessage());
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return driver;
	}

}
