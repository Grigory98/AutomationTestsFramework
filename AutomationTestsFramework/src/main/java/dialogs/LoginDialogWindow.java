package dialogs;

import core.DriversManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.AbstractPage;

public class LoginDialogWindow extends AbstractPage {

    private final static String SUCCESS_LOGIN_URL = "https://e.mail.ru/inbox/";
    private final static By LOGIN_CONTENT = By.xpath("//*[@id=\"login-content\"]");

    @FindBy(how = How.NAME, using = "username")
    public WebElement username;

    @FindBy(how = How.NAME, using = "password")
    public WebElement password;

    @FindBy(how = How.CSS, using = "[data-test-id = \"next-button\"]")
    public WebElement nextButton;

    @FindBy(how = How.CSS, using = "[data-test-id = \"submit-button\"]")
    public WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"login-content\"")
    public WebElement loginContent;

    public void fillUsername(String text) {
        username.sendKeys(text);
    }

    public void fillPassword(String text) {
        password.sendKeys(text);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickSignInButton() {
        submitButton.click();
        DriversManager.waitFor().until(t -> DriversManager.current().getCurrentUrl().contains(SUCCESS_LOGIN_URL));
    }

    public LoginDialogWindow() {
        var div = DriversManager.current().findElement(By.cssSelector(".ag-popup__frame__layout.ag-popup__frame__layout-desktop"));

        var iframe = div.findElement(By.tagName("iframe"));

        DriversManager.current().switchTo().frame(iframe);

        PageFactory.initElements(DriversManager.current().findElement(LOGIN_CONTENT), this);
    }
}
