package grids;

import core.DriversManager;
import grids.items.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class MailGrid extends AbstractGrid {

    @FindBy(how = How.CSS, using = "[class = \"draggable\"]")
    private WebElement messageList;

    @FindBy(how = How.CSS, using = ".compose-button__txt")
    private WebElement sendMessageButton;

    @FindBy(how = How.XPATH, using = "//div[text() = \"Сбросить поиск\"]")
    private WebElement resetSearchButton;

    @FindBy(how = How.CSS, using = ".search-panel-button__text")
    private WebElement searchPanelButton;

    @FindBy(how = How.CSS, using = ".search-panel__layer input")
    private WebElement searchInput;

    @FindBy(how = How.CSS, using = "[href=\"/drafts/?\"]")
    private WebElement sectionDraft;

    @FindBy(how = How.CSS, using = "[href = \"/trash/?\"]")
    private WebElement sectionBasket;

    public void startSearch(String text) {
        searchPanelButton.click();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void startSearchFavorite() {
        searchPanelButton.click();
        DriversManager.current().findElement(By.cssSelector("[data-navigation-index = \"5\"]")).click();
    }

    public void resetSearch() {
        resetSearchButton.click();
    }

    public void sendMessage() {
        sendMessageButton.click();
    }

    public void goToDrafts() {
        sectionDraft.click();
        DriversManager.waitFor().until(t -> sectionDraft.getAttribute("class").contains("nav__item_active"));
    }

    public void goToBasket() {
        sectionBasket.click();
        DriversManager.waitFor().until(t -> sectionBasket.getAttribute("class").contains("nav__item_active"));
    }

    public Message getMessage(String topic) {
        List<Message> messages = getMessages();
        if(messages.isEmpty())
            return null;
        return messages.stream()
                .filter(m -> m.getTitle().contains(topic))
                .collect(Collectors.toList())
                .get(0);
    }

    public Message getMessage(int num) {
        List<Message> messages = getMessages();
        if(messages.isEmpty())
            return null;
        return messages.get(num);
    }

    public void deleteMessgaes() {
        Actions action = new Actions(DriversManager.current());
        action.sendKeys(Keys.DELETE).perform();
    }

    public boolean checkHintExists(String text) {
        WebElement notifyStack = DriversManager.current().findElement(By.cssSelector(".notify-stack_fixed"));
        WebElement hint = DriversManager.waitFor().until(t -> notifyStack.findElement(By.cssSelector(".notify__message")));
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
                .map(Message::new)
                .collect(Collectors.toList());
    }
}
