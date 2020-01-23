package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageobject.*;
import utils.*;

public class LoginTest {
	
	/*
	 * NOTE: All of test data has been removed due security reasons. Tests will fail
	 * without proper login data. Login data is stored in \test-data
	 */

	public WebDriver driver;
	public Login login;
	public AddIssueTest issue;
	public Utils utils = new Utils();
	XLSXreader xlsx = new XLSXreader("test-data\\data.xlsx");
	XMLreader xml = new XMLreader("test-data\\data.xml");
	
	@Test
	public void Should_Not_Work_With_Random_Username() {
	
		String userName = utils.randomString(10);
		Login login = new Login(driver);
		login.login(userName, xlsx.getItem("password", 0));

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
	}
	
	@Test
	public void Should_Not_Work_With_Random_Password() {
	
		String password = utils.randomString(10);
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), password);

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
	}
	
	@Test
	public void Should_Not_Work_With_Bad_User_Name() {
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), xlsx.getItem("password", 0));

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
		}

	@Test
	public void Should_Not_Work_With_Bad_Pass() {
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), xlsx.getItem("password", 0));

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_chine() {
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), xlsx.getItem("password", 0));

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_php() {
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), "<?php echo \"My first PHP script!\";?>");

		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
		}
	
	@Test
	public void Should_Not_Work_With_Bad_Pass_html() {
		Login login = new Login(driver);
		login.login(xlsx.getItem("username", 0), "<html><title>Page Title</title></html>");
		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
		}
	
	@Test
	public void Should_Work_With_good_Pass_XML() {
		Login login = new Login(driver);
		login.login(xml.getUserName(), xml.getPassword());
		Assert.assertTrue(driver.findElement(By.cssSelector("#infobar-left")).isDisplayed());
		}
	
	@Test
	public void Should_Work_With_bad_Pass_XML() {
		Login login = new Login(driver);
		login.login(xml.getUserName(), xml.getPassword());
		Assert.assertEquals(login.getLoginMessage().getText(),"Incorrect value: Invalid login or password.");
	}
		
	@BeforeClass (groups = {"smooke", "checkintest"} )
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(xml.getPassword());
	}


	@AfterClass (groups = {"smooke", "checkintest"} )
	public void afterClass() {
		 driver.quit();
	}

}