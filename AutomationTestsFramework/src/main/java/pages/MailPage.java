package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MailPage extends AbstractPage {
    @FindBy(how = How.CSS, using = ".compose-button__txt")
    private WebElement sendMessageButton;

    public void sendMessage() {
        sendMessageButton.click();
    }
}
