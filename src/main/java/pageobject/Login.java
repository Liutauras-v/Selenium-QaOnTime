package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageObject {

	@FindBy(id = "field-login-login")
	private WebElement loginField;

	@FindBy(id = "field-login-password")
	private WebElement passField;

	@FindBy(id = "field-login-loginSubmit")
	private WebElement loginSubmitButton;

	@FindBy(className = "error")
	private WebElement loginError;

	@FindBy(css = "#infobar-right > a:nth-child(2)")
	private WebElement logOut;

	public Login(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		driver.get("http://www.qaontime.com/register");
		this.loginField.clear();
		this.passField.clear();
		this.loginField.sendKeys(username);
		this.passField.sendKeys(password);
		this.loginSubmitButton.click();
	}

	public WebElement getLoginField() {
		return loginField;
	}

	public WebElement getpassField() {
		return passField;
	}

	public WebElement getloginSubmitButton() {
		return loginSubmitButton;
	}

	public WebElement getLoginMessage() {
		return loginError;
	}

	public WebElement getLogOut() {
		return logOut;
	}
}
