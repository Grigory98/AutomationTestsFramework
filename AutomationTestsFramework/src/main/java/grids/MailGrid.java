package grids;

import core.DriversManager;
import grids.items.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class MailGrid extends AbstractGrid {

    @FindBy(how = How.CSS, using = "[class = \"draggable\"]")
    private WebElement messageList;

    @FindBy(how = How.CSS, using = ".compose-button__txt")
    private WebElement sendMessageButton;

    @FindBy(how = How.CSS, using = ".search-panel-button__text")
    private WebElement searchPanelButton;

    @FindBy(how = How.CSS, using = ".search-panel__layer input")
    private WebElement searchInput;

    public void startSearch(String text) {
        searchPanelButton.click();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void sendMessage() {
        sendMessageButton.click();
    }



    public Message getMessage(String topic) {
        List<Message> messages = getMessages();
        return messages.stream()
                .filter(m -> m.getTitle().contains(topic))
                .collect(Collectors.toList())
                .get(0);
    }

    public void deleteMessgaes() {
        messageList.sendKeys(Keys.DELETE);
    }

    public boolean checkHintExists(String text) {
        WebElement hint = DriversManager.waitFor().until(t -> DriversManager.current().findElement(By.cssSelector(".notify__body")));
        return hint.getText().contains(text);
    }

    private List<Message> getMessages() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return messageList.findElements(By.cssSelector(".llc"))
                .stream()
                .map(m -> new Message(m))
                .collect(Collectors.toList());
    }

}
