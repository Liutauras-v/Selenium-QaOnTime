package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;
import utils.Utils;
import utils.XLSXreader;
import utils.XMLreader;

public class NewTest {

	/*
	 * NOTE: All of test data has been removed due security reasons. Tests will fail
	 * without proper login data. Login data is stored in \test-data
	 */

	public WebDriver driver;
	Utils utils = new Utils();
	XLSXreader xlsx = new XLSXreader("test-data\\data.xlsx");
	XMLreader xml = new XMLreader("test-data\\data.xml");

	@Test
	public void loginToQaontime() throws InterruptedException {
		driver.get("http://qaontime.com/register/client/index.php?folder=5");
		WebElement addIssue = driver.findElement(By.linkText("Add Issue"));
		addIssue.click();

		Thread.sleep(1500);

		WebElement issueNameField = driver.findElement(By.id("field-issues-issueName"));
		issueNameField.sendKeys("name");

		WebElement descriptionTextField = driver.findElement(By.id("field-issues-descriptionText"));
		descriptionTextField.sendKeys("qwerty");

		WebElement okSubmitButton = driver.findElement(By.id("field-issues-okSubmit"));
		okSubmitButton.click();
		Thread.sleep(1500);
	}

	@Test

	public void Should_NotAbleCreateIssue_when_issueNotProvided() throws InterruptedException {

		driver.get("http://qaontime.com/register/client/index.php?folder=5");
		WebElement addIssue = driver.findElement(By.linkText("Add Issue"));
		addIssue.click();

		Thread.sleep(1500);

		WebElement okSubmitButton = driver.findElement(By.id("field-issues-okSubmit"));
		okSubmitButton.click();

		WebElement requireValueIsMissingError = driver.findElement(By.xpath("//*[@id=\"body\"]/div/p[1]"));
		Assert.assertEquals(requireValueIsMissingError.getText(), "Some of the values you entered are incorrect.");

	}

	@Test(timeOut = 1000)
	public void Should_severity_valid() {

		driver.get("http://qaontime.com/register/client/issues/addissue.php?type=2");
		driver.findElement(By.id("field-issues-value4")).sendKeys("8");
		WebElement okButton = driver.findElement(By.id("field-issues-okSubmit"));
		okButton.click();
		Assert.assertTrue(driver.findElement(By.id("infobar-left")).isDisplayed(), "nepavyko surasti");
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(xml.getUrl());
		Login login = new Login(driver);
		login.getLoginField().sendKeys(xlsx.getItem("username", 0));
		login.getpassField().sendKeys(xlsx.getItem("password", 0));
		login.getloginSubmitButton().click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}