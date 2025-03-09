package basesuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelUtils;

public class DemoQABaseSuite extends ExcelUtils {

	public ExtentReports extent;
	public ExtentSparkReporter htmlReporter;

	@BeforeSuite
	public void setUp() {

		extent = new ExtentReports();
		htmlReporter = new ExtentSparkReporter("./reports/testReport.html");
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com");
	}

	@BeforeMethod
	public void before(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = extent.createTest(testName, "Description of " + testName);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		extent.flush();
	}
}
