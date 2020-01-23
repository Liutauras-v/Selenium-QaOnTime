package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssue extends PageObject {

	@FindBy(linkText = "Add Issue")
	private WebElement addBtn;

	@FindBy(id = "field-issues-issueName")
	private WebElement issueName;

	@FindBy(id = "field-issues-descriptionText")
	private WebElement description;

	@FindBy(id = "field-issues-okSubmit")
	private WebElement submitBtn;

	@FindBy(className = "comment-text")
	private WebElement commentDescription;

	@FindBy(id = "field-issues-value4")
	private WebElement severity;

	@FindBy(className = "error")
	private WebElement commentValueIncorect;

	@FindBy(css = "#form-issues > div > div.form-fieldset > div:nth-child(5) > p")
	private WebElement severityIncorect;

	@FindBy(id = "field-issues-value1")
	private WebElement asignedTo;

	@FindBy(id = "field-issues-value2")
	private WebElement status;

	@FindBy(id = "field-issues-value3")
	private WebElement reason;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[2]/p")
	private WebElement asignedToError;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[3]/p")
	private WebElement statusError;

	@FindBy(xpath = "//*[@id=\"form-issues\"]/div/div[2]/div[4]/p")
	private WebElement reasonError;

	@FindBy(css = "td.top-pane > div > h2")
	private WebElement bug;

	public CreateIssue(WebDriver driver) {
		super(driver);
	}

	public void add(String name, String asignedTo, String status, String reason, String severity, String description) {
		driver.get("http://www.qaontime.com/register/client/index.php?folder=5)");
		this.addBtn.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.issueName.click();
		this.issueName.sendKeys(name);

		this.asignedTo.click();
		this.asignedTo.sendKeys(asignedTo);

		this.status.click();
		this.status.clear();
		this.status.sendKeys(status);

		this.reason.click();
		this.reason.sendKeys(reason);

		this.severity.clear();
		this.severity.sendKeys(severity);
		this.description.click();
		this.description.sendKeys(description);
		this.submitBtn.click();

	}

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getIssueName() {
		return issueName;
	}

	public WebElement getDescription() {
		return description;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getCommentDescription() {
		return commentDescription;
	}

	public WebElement getSeverity() {
		return severity;
	}

	public WebElement getCommentValueIncorect() {
		return commentValueIncorect;
	}

	public WebElement getSeverityIncorect() {
		return severityIncorect;
	}

	public WebElement getAsignedTo() {
		return asignedTo;
	}

	public WebElement getAsignedToError() {
		return asignedToError;
	}

	public WebElement getStatus() {
		return status;
	}

	public WebElement getStatusError() {
		return statusError;
	}

	public WebElement getReason() {
		return reason;
	}

	public WebElement getReasonError() {
		return reasonError;
	}

	public WebElement getBug() {
		return bug;
	}

}
