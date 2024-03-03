package dialogs;

import core.ApplicationConfig;
import core.DriversManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.AbstractPage;

public class SendMessageDialogWindow extends AbstractPage {
    @FindBy(how = How.XPATH, using = "(//div[@class = \"compose-app__compose\"]//input[@type = \"text\"])[1]")
    private WebElement forWhom;

    @FindBy(how = How.XPATH, using = "//input[@name = \"Subject\"]")
    private WebElement topic;

    @FindBy(how = How.XPATH, using = "//div[@role = \"textbox\"]")
    private WebElement textbox;

    @FindBy(how = How.CSS, using = "[data-test-id = \"send\"]")
    private WebElement sendButton;

    @FindBy(how = How.CSS, using = "[data-test-id=\"save\"]")
    private WebElement saveButton;

    @FindBy(how = How.CSS, using = ".layer__link")
    private WebElement successfulTitleAfterSend;

    @FindBy(how = How.CSS, using = "[title = \"Закрыть\"]")
    private WebElement closeWindowButton;

    public void fillForWhom() {
        forWhom.sendKeys(ApplicationConfig.USERNAME);
    }

    public void fillTopic(String text) {
        topic.sendKeys(text);
    }

    public void fillTextbox(String text) {
        textbox.sendKeys(text);
    }

    public void closeWindow() {
        try {
            Thread.sleep(1000);
            closeWindowButton.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMessageAsDraft() {
        saveButton.click();
    }

    public void sendMessage() {
        sendButton.click();
    }

    public boolean checkAfterSendMessage() {
        return DriversManager.waitFor().until(t -> successfulTitleAfterSend.getText().contains("Письмо отправлено"));
    }
}
