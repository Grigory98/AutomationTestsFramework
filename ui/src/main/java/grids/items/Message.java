package grids.items;

import core.DriversManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.AbstractPage;

public class Message extends AbstractPage {
    private final WebElement message;

    private final String IS_ACTIVE_SELECTOR = "ll-fs_is-active";

    @FindBy(how = How.CSS, using = ".llc__item_correspondent")
    private WebElement correspondent;

    @FindBy(how = How.CSS, using = ".llc__item_title")
    private WebElement title;

    @FindBy(how = How.CSS, using = ".llc__snippet")
    private WebElement description;

    @FindBy(how = How.CSS, using = ".checkbox__box")
    private WebElement checkbox;

    @FindBy(how = How.CSS, using = ".ll-fs")
    private WebElement flag;

    public String getCorrespondent() {
        return correspondent.getText();
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public void selectMessage() {
        Actions action = new Actions(DriversManager.current());
        action.moveToElement(this.message).perform();
        DriversManager.waitFor().until(t -> checkbox.isDisplayed());
        checkbox.click();
    }

    public void markAsFavorite() {
        if(!flag.getAttribute("class").contains(IS_ACTIVE_SELECTOR))
            flag.click();
    }

    public Message(WebElement message) {
        this.message = message;
    }
}
