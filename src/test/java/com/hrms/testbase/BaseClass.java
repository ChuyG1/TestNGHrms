package com.hrms.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;

public class BaseClass {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports report;
	public static ExtentTest test;
	
	
	@BeforeTest(alwaysRun = true)
	public void generateReport() {
		
		System.out.println("------------ Starting Generating Reports ----------");
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		htmlReport=new ExtentHtmlReporter(Constants.REPORT_FILEPATH);
		htmlReport.config().setDocumentTitle("Hrms Report");
		htmlReport.config().setReportName("Hrms Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		
	}
	
	@AfterTest(alwaysRun = true)
	public void writeReport() {
		System.out.println("After Test Executed");
		report.flush();
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public static WebDriver setUp() {
		
		System.getProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "true");
		
		switch(ConfigsReader.getProperty("browser").toLowerCase()) {
		
		case"chrome":
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			driver=new ChromeDriver();
			break;
		case"firefox":
			System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
			driver=new FirefoxDriver();
			break;
			default:
				throw new RuntimeException("Browser is not supported");
		}
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.get(ConfigsReader.getProperty("url"));
		//initialize all page objects as part of setUp
		PageInitializer.initialize();
		return driver;
	}
	@AfterMethod(alwaysRun = true)
	public static void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
