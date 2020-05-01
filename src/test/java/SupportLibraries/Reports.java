package SupportLibraries;

import java.util.Properties;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestSetup.BaseClass;

public class Reports extends BaseClass {

	public static SoftAssert sAssert = new SoftAssert();
	public static String reportName;

	public static ExtentHtmlReporter startHtmlReport() {

		reportName = "Consolicated_" + Util.dateTimeString() + ".html";
		htmlReporter = new ExtentHtmlReporter(reportName);
		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		return htmlReporter;

	}

	public static ExtentHtmlReporter StartHtmlReport(ExtentHtmlReporter htmlReporter, String ResultFolderPath) {

		reportName = ResultFolderPath + "Consolidated_" + "_" + Util.dateTimeString() + ".html";
		htmlReporter = new ExtentHtmlReporter(reportName);

		htmlReporter.config().setDocumentTitle("Automation Execution Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		return htmlReporter;
	}

	public static void infoTest(String stepDetails) {
		try {

			test.info(MarkupHelper.createLabel(stepDetails, ExtentColor.BLUE));
			LogClass.info(stepDetails);
			System.out.println("Test Info : " + stepDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void passTest(String stepDetails) {
		try {

			test.pass(MarkupHelper.createLabel(stepDetails, ExtentColor.GREEN));
			LogClass.info(" PASSTest :"+stepDetails);
			System.out.println("Test Pass : " + stepDetails);
			Assert.assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			LogClass.info("Error while reporting" + e.getMessage());
		}
	}

	public static void failTest(String stepDetails) {
		try {
			test.fail(MarkupHelper.createLabel(stepDetails, ExtentColor.RED));
			LogClass.info("FailTest :"+stepDetails);
			System.out.println("Test Fail :" + stepDetails);
			Assert.assertTrue(false);

		} catch (Exception e) {
			e.printStackTrace();
			LogClass.info("Error while reporting" + e.getMessage());
		}
	}

	public static void startTest(String stepDetails) {
		test.info(MarkupHelper.createLabel(stepDetails, ExtentColor.BLACK));
		LogClass.info("Starting of Test Script >>> " + stepDetails);
		System.out.println("####### Starting Test script######" + stepDetails);
	}
	
	public static void endReport(ExtentReports extent) {
		extent.flush();
	}


	public static ExtentTest testCreate(ExtentReports extent, String Stepdetails) {
		test = extent.createTest(Stepdetails, Stepdetails);
		return test;
	}
	
	public static ExtentReports StartExtentReport(ExtentHtmlReporter htmlReporter, ExtentReports extent) {
		Properties prop = Util.readProperty();
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", System.getProperty("os.name")); // prop.getProperty("OS"));
		extent.setSystemInfo("Host Name", prop.getProperty("HostName"));
		extent.setSystemInfo("Env", prop.getProperty("Env"));
		return extent;
	}
}
