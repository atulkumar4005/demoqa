package pages;

import org.openqa.selenium.By;

public interface DemoQA_OR {

	By formText = By.xpath("//div[@class='card-body']/*[text()='Forms']");
	By practiceFormLink = By.xpath("//ul[@class='menu-list']//span[text()='Practice Form']");

	By firstName = By.xpath("//input[@id='firstName']");
	By lastName = By.xpath("//input[@id='lastName']");
	By email = By.xpath("//input[@id='userEmail']");
	By mobileNumber = By.xpath("//input[@id='userNumber']");
	By address = By.xpath("//textarea[@id='currentAddress']");
	By subject = By.xpath("//input[@id='subjectsInput']");

	By submit = By.xpath("//button[@id='submit']");

	By savedDataPopup = By.xpath("//div[contains(@class,'modal-title') and text()='Thanks for submitting the form']");
	By close = By.xpath("//button[@id='closeLargeModal']");

	By alertFrameAndWindowPanel = By
			.xpath("//div[@class='header-text' and contains(text(),'Alerts, Frame & Windows')]");
	By browserWindow = By.xpath("//span[@class='text' and text()='Browser Windows']");
	By alerts = By.xpath("//span[@class='text' and text()='Alerts']");
	By frames = By.xpath("//span[@class='text' and text()='Frames']");
	
	//for alert
	By alertButton=By.id("alertButton");
	By confirmButton=By.id("confirmButton");
	By promptButton=By.id("promtButton");
	By promptResult=By.id("promptResult");
	
	//for window
	By tabButton=By.id("tabButton");
	By sampleHeading=By.id("sampleHeading");
	By windowButton=By.id("windowButton");
}
