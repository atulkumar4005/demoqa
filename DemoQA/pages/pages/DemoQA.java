package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class DemoQA extends CommonFunctions implements DemoQA_OR {

	public void clickAndOpen(By toBeClicked, By toBeVerified) {

		scrollToElement(toBeClicked);

		jsClick(toBeClicked);
		pause(4);
		if (getWebElement(toBeVerified).isDisplayed()) {
			test.log(Status.PASS, toBeVerified.toString() + " is displayed");
		} else {
			test.log(Status.FAIL, toBeVerified.toString() + " is not displayed");
		}
	}

	public void setFormData(String firstNameStr, String lastNameStr, String emailid, String gendar,
			String subjectToEnter, String addressStr) {

		scrollToElement(firstName);

		setValue(firstName, firstNameStr);
		setValue(lastName, lastNameStr);

		scrollToElement(email);
		setValue(email, emailid);

		String genderLocatorStr = "//input[@value='%s']".replace("%s", gendar);

		jsClick(By.xpath(genderLocatorStr));
		scrollToElement(subject);
		setValue(mobileNumber, "1234567898");
		setValue(subject, subjectToEnter);
		setValue(address, addressStr);
	}

	public void clickSaveAndVerifySavePopup() {
		scrollToElement(submit);
		jsClick(submit);
		pause(3);
		if (getWebElement(savedDataPopup).isDisplayed()) {
			test.log(Status.PASS, savedDataPopup.toString() + " is displayed");
		} else {
			test.log(Status.FAIL, savedDataPopup.toString() + " is not displayed");
		}
	}

	public void closePopup() {

		scrollToElement(close);
		jsClick(close);
		pause(3);

	}

}
