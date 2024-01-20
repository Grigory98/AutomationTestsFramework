package grids.items;

import core.DriversManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Message {
    private final WebElement message;

    public String getSender() {
        return message.findElement(By.cssSelector(".llc__item_correspondent")).getText();
    }

    public String getTitle() {
        return message.findElement(By.cssSelector(".llc__item_title")).getText();
    }

    public String getDescription() {
        return message.findElement(By.cssSelector(".llc__snippet")).getText();
    }

    public void selectMessage() {
        WebElement checkbox  = this.message.findElement(By.cssSelector(".checkbox__box"));
        Actions action = new Actions(DriversManager.current());
        action.moveToElement(this.message).perform();
        DriversManager.waitFor().until(t -> checkbox.isDisplayed());
        checkbox.click();
    }

    public void openMessage() {
        message.click();
    }

    public Message(WebElement message) {
        this.message = message;
    }
}
