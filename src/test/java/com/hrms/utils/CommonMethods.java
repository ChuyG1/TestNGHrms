package com.hrms.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrms.testbase.PageInitializer;

public class CommonMethods extends PageInitializer {
	/**
	 * Method that clears and sends keys
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	/**
	 * Method checks if radio/checkbox is enabled and clicks it
	 * @param radioOrcheckbox
	 * @param value
	 */
	public static void clickRadioOrCheckbox(List<WebElement> radioOrcheckbox, String value) {
		
		String actualValue;
		
		for(WebElement el:radioOrcheckbox) {
			actualValue=el.getAttribute("value").trim();
			if(el.isEnabled()&&actualValue.equals(value)) {
			el.click();
			break;
			}
		}
	}
	public static void selectDdValue(WebElement element, String textToSelect) {
		try {
		Select select=new Select(element);
		List<WebElement> options=select.getOptions();
		
		for(WebElement el:options) {
			if(el.getText().equals(textToSelect)) {
				select.selectByVisibleText(textToSelect);
				break;
			}
		}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}
	//public static void SelectDdValue(WebElement element, String textToSelect) {
	//	try {
	//		Select select=new Select(element);
	//		List<WebElement> options=select.getOptions();
//			
	//		for(WebElement el: options) {
	//			if(el.getText().equals(textToSelect)) {
	//				select.selectByVisibleText(textToSelect);
	//				break;
	//			}
	//		} 
	//	}
	//}
	
	public static void acceptAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	public static void dismissAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	public static String getAlertText() {
		
		String alertText= null;
		
		try {
			Alert alert=driver.switchTo().alert();
			alertText=alert.getText();
		}catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
		return alertText;
	}
	public static void switchToFrame(String nameOrid) {
		
		try {
			driver.switchTo().frame(nameOrid);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	public static void switchToFrame(WebElement element) {
		
		try {
			driver.switchTo().frame(element);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	public static void switchToFreame(int index) {
		
		try {
			driver.switchTo().frame(index);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	public static void switchToChildWindow() {
		String mainWindow=driver.getWindowHandle();
		Set<String> windows=driver.getWindowHandles();
		for(String window : windows) {
			if(!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}
	
	public static WebDriverWait getwaitObject() {
		WebDriverWait wait =new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
		return wait;
	}
	
	public static  WebElement waitForClickability(WebElement element) {
		getwaitObject().until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public static WebElement waitForVisibility(WebElement element) {
		return getwaitObject().until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
	}
	
	public static JavascriptExecutor getJSObject() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		return js;
	}
	
	public static void jsClick(WebElement element) {
		getJSObject().executeScript("arguments[0].click();", element);
	}
	
	public static void scrollToElement(WebElement element) {
		getJSObject().executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void scrollDown(int pixel) {
		getJSObject().executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	public static void scrollUp(int pixel) {
		getJSObject().executeScript("window.scrollBy(0,-"+pixel+")");
	}
	
	/**
	 * this Method will take a screenshot
	 * 
	 * @param filename
	 */
	public static String takeScreenshot(String filename) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=Constants.SCREENSHOT_FILEPATH+filename+getTimeStamp()+".png";
		
		try {
			FileUtils.copyFile(file, new File(destinationFile));
		} catch(Exception ex) {
			System.out.println("Cannot take screenshot!");
		}
		
		return destinationFile;
	}
	
	public static String getTimeStamp() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}
	
	public static void wait(int second) {
		try {
			Thread.sleep(second = 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
	

