package appsuite;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import basesuite.DemoQABaseSuite;
import pages.DemoQA;
import pages.DemoQA_OR;


public class DemoQASuite extends DemoQABaseSuite {

	DemoQA demoQa = new DemoQA();

	@Test(priority = 0)
	public void navigateToForm() {
		demoQa.clickAndOpen(DemoQA_OR.formText, DemoQA_OR.practiceFormLink);
		demoQa.clickAndOpen(DemoQA_OR.practiceFormLink, DemoQA_OR.practiceFormLink);

	}

	@Test(priority = 1, dependsOnMethods = "navigateToForm", dataProvider = "userData")
	public void enterFormDataAndSave(String firstNameStr, String lastNameStr, String emailid, String gendar,
			String subjectToEnter, String addressStr) {
		demoQa.setFormData(firstNameStr, lastNameStr, emailid, gendar, subjectToEnter, addressStr);

		demoQa.clickSaveAndVerifySavePopup();
		demoQa.closePopup();
	}

	@Test(priority = 2)
	public void handleAlert() {
		demoQa.clickAndOpen(DemoQA_OR.alertFrameAndWindowPanel, DemoQA_OR.alerts);

		demoQa.clickAndOpen(DemoQA_OR.alerts, DemoQA_OR.alertButton);

		demoQa.scrollToElement(DemoQA_OR.alertButton);
		demoQa.click(DemoQA_OR.alertButton);
		pause(1);
		// Switch to alert and accept it
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Text: " + alert.getText());
		alert.accept();

		// Click the "Click Me" button for confirm alert
		demoQa.scrollToElement(DemoQA_OR.confirmButton);

		demoQa.click(DemoQA_OR.confirmButton);
		pause(1);
		Alert confirmAlert = driver.switchTo().alert();
		System.out.println("Confirm Alert Text: " + confirmAlert.getText());
		confirmAlert.dismiss(); // You can also use accept()

		// Click the "Click Me" button for prompt alert
		demoQa.scrollToElement(DemoQA_OR.promptButton);

		demoQa.click(DemoQA_OR.promptButton);
		pause(1);
		Alert promptAlert = driver.switchTo().alert();
		System.out.println("Prompt Alert Text: " + promptAlert.getText());
		promptAlert.sendKeys("Test User");
		promptAlert.accept();

		// Verify the response after prompt alert

		WebElement promptResult = demoQa.getWebElement(DemoQA_OR.promptResult);
		if (promptResult.getText().contains("Test User")) {
			test.log(Status.PASS, "Successfully displayed Test User");
		} else {
			test.log(Status.FAIL, "Failed to displayed Test User");
		}

	}
	@Test(priority = 3)
	public void handleFrames() {
		demoQa.click(DemoQA_OR.frames);
		demoQa.pause(4);

		// Switch to first frame by ID
		driver.switchTo().frame("frame1");
		WebElement heading1 = demoQa.getWebElement(DemoQA_OR.sampleHeading);
		System.out.println("Frame 1 Text: " + heading1.getText());

		if (heading1.getText().equals("This is a sample page")) {
			test.log(Status.PASS, "Successfully displayed 'This is a sample page'");
		} else {
			test.log(Status.FAIL, "Failed to displayed 'This is a sample page'");
		}
		// Switch back to default content
		driver.switchTo().defaultContent();

		// Switch to second frame by ID
		driver.switchTo().frame("frame2");
		WebElement heading2 = demoQa.getWebElement(DemoQA_OR.sampleHeading);
		System.out.println("Frame 2 Text: " + heading2.getText());

		if (heading2.getText().equals("This is a sample page")) {
			test.log(Status.PASS, "Successfully displayed 'This is a sample page'");
		} else {
			test.log(Status.FAIL, "Failed to displayed 'This is a sample page'");
		}
		// Switch back to default content
		driver.switchTo().defaultContent();
	}
	@Test(priority = 4)
	public void handleWindows() {
		demoQa.click(DemoQA_OR.browserWindow);

		demoQa.pause(2);
		// Click "New Tab" button
		demoQa.click(DemoQA_OR.tabButton);

		// Handle multiple windows
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();

		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				WebElement textElement = demoQa.getWebElement(DemoQA_OR.sampleHeading);
				System.out.println("New Tab Text: " + textElement.getText());

				if (textElement.getText().equals("This is a sample page")) {
					test.log(Status.PASS, "Successfully displayed 'This is a sample page'");
				} else {
					test.log(Status.FAIL, "Failed to displayed 'This is a sample page'");
				}
				driver.close(); // Close child window
			}
		}
		driver.switchTo().window(mainWindow); // Switch back to main window

		// Click "New Window" button
		demoQa.click(DemoQA_OR.windowButton);

		allWindows = driver.getWindowHandles();
		iterator = allWindows.iterator();

		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println("New Window Title: " + driver.getTitle());
				driver.close(); // Close child window
			}
		}
		driver.switchTo().window(mainWindow); // Switch back to main window
	}

	
}
