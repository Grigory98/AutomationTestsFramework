package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage extends AbstractPage {
    @FindBy(how = How.CSS, using = "[name = \"username\"]")
    private WebElement username;

    @FindBy(how = How.CSS, using = "[name = \"password\"]")
    private WebElement password;

    @FindBy(how = How.CSS, using = "[data-test-id = \"next-button\"]")
    private WebElement nextButton;

    @FindBy(how = How.CSS, using = "[data-test-id= \"submit-button\"]")
    private WebElement submitButton;

    public void fillUsername(String login) {
        username.sendKeys(login);
    }

    public void fillPassword(String pass) {
        password.sendKeys(pass);
    }

    public void clickNext() {
        nextButton.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
