package grids;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class MailGrid extends AbstractGrid {

    @FindBy(how = How.CSS, using = "[class = \"draggable\"]")
    private WebElement messageList;

    @FindBy(how = How.CSS, using = ".compose-button__txt")
    private WebElement sendMessageButton;

    @FindBy(how = How.CSS, using = ".search-panel__layer input")
    private WebElement search;

    public void searchMessage(String text) {
        search.sendKeys(text);
    }

    public void sendMessage() {
        sendMessageButton.click();
    }

    private List<WebElement> getMessages() {
        return messageList.findElements(By.cssSelector("a[class ^= \"llc\"]"));
    }

    public WebElement getMessage(String topic) {
        return this.getMessages()
                .stream()
                .filter(m -> m.findElement(By.cssSelector(".llc__item_title")).getText() == topic)
                .collect(Collectors.toList())
                .get(0);
    }

}
