package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pageobject.*;
import utils.Utils;
import utils.XLSXreader;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AddIssueTest {

	/*
	 * NOTE: All of test data has been removed due security reasons. Tests will fail
	 * without proper login data. Login data is stored in \test-data
	 */

	public WebDriver driver;
	public CreateIssue createIssue;
	Utils utils = new Utils();
	XLSXreader xlsx = new XLSXreader("test-data\\data.xlsx");

	@Test
	public void Should_Create_Issue_Good_One() {
		createIssue.add("qwerty", "qwerty", "Active", "Fixed", "1", "ba");
		Assert.assertTrue(createIssue.getBug().isDisplayed());
	}

	@Test
	public void Should_Not_Create_Issue_Without_Name() {
		createIssue.add("", " ", " ", " ", "1", "ba");
		Assert.assertEquals(createIssue.getCommentValueIncorect().getText(),
				"Some of the values you entered are incorrect.");
	}

	@Test
	public void Should_Create_Issue_Whith_Space_Before_Name() {
		createIssue.add("          laba", "", "Active", "", "1", "susiskure su tarpu name");
		Assert.assertTrue(createIssue.getBug().isDisplayed());
	}

	@Test
	public void Should_not_Create_Issue_with_severity_bigger_3() {
		createIssue.add("bfds5", " ", " ", " ", "5", "Incorrect value: Number is too big.");
		Assert.assertEquals(createIssue.getSeverityIncorect().getText(), "Incorrect value: Number is too big.");
	}

	@Test
	public void Should_random_write_asignedTo() {

		createIssue.add(utils.randomString(15), " ", "Active", " ", Integer.toString(utils.randomInt(1, 3)),
				utils.randomString(45));
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#body > table > tbody > tr:nth-child(1) > td.top-pane > div > h2"))
						.isDisplayed());
	}

	@Test
	public void Should_random_write() {
		createIssue.add(utils.randomString(15), " ", "Active", " ", Integer.toString(utils.randomInt(1, 3)),
				utils.randomString(45));
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#body > table > tbody > tr:nth-child(1) > td.top-pane > div > h2"))
						.isDisplayed());
	}

	@Test
	public void Should_NotBeAble_ToCreateIssue_When_AsignedTo_isInvalid() {
		createIssue.add("zi", "invalid", "Active", "Fixed", "1", "ba");
		Assert.assertEquals(createIssue.getAsignedToError().getText(),
				"Incorrect value: No matching item is selected.");
	}

	@Test
	public void Should_NotBeAble_ToCreateIssue_When_Status_isInvalid() {
		createIssue.add("zi", "qw", "invalid", "Fixed", "1", "ba");
		Assert.assertEquals(createIssue.getStatusError().getText(), "Incorrect value: No matching item is selected.");
	}

	@Test
	public void Should_NotBeAble_ToCreateIssue_When_Reason_isInvalid() {
		createIssue.add("zi", "qw", "Active", "blabla", "1", "ba");
		Assert.assertEquals(createIssue.getReasonError().getText(), "Incorrect value: No matching item is selected.");
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), xlsx.getItem("password", 0));

		createIssue = new CreateIssue(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
