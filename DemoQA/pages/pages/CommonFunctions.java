package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class CommonFunctions {
	public static WebDriver driver;
	public static ExtentTest test;

	public WebElement getWebElement(By locator) {
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + locator);
			return null;
		}
	}

	public void click(By elementToClick) {
		try {
			WebElement element = getWebElement(elementToClick);
			if (element != null) {
				element.click();
			} else {
				System.out.println("Click failed: Element is null.");
			}
		} catch (Exception e) {
			System.out.println("Exception in click(): " + e.getMessage());
		}
	}

	public void setValue(By locator, String value) {
		try {
			WebElement element = getWebElement(locator);
			if (element != null) {
				element.clear();
				element.sendKeys(value);
			} else {
				System.out.println("SetValue failed: Element is null.");
			}
		} catch (Exception e) {
			System.out.println("Exception in setValue(): " + e.getMessage());
		}
	}

	public void pause(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			System.out.println("Pause interrupted: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
	}

	public void scrollToElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		} catch (Exception e) {
			System.out.println("Error while scrolling: " + e.getMessage());
		}
	}

	public void jsClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			System.out.println("Error while performing JS Click: " + e.getMessage());
		}
	}
}
