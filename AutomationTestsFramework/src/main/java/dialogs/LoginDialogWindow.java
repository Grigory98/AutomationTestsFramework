package dialogs;

import core.DriversManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginDialogWindow {
    private final WebElement dialog;

    public void fillUsername(String text) {
        dialog
                .findElement(By.name("username"))
                .sendKeys(text);
    }

    public void fillPassword(String text) {
        dialog
                .findElement(By.name("password"))
                .sendKeys(text);
    }

    public void clickNextButton() {
        dialog
                .findElement(By.cssSelector("[data-test-id = \"next-button\"]"))
                .click();
    }

    public void clickSignInButton() {
        dialog
                .findElement(By.cssSelector("[data-test-id = \"submit-button\"]"))
                .click();
    }

    public LoginDialogWindow() {
        var div = DriversManager.current().findElement(By.cssSelector(".ag-popup__frame__layout.ag-popup__frame__layout-desktop"));

        var iframe = div.findElement(By.tagName("iframe"));

        DriversManager.current().switchTo().frame(iframe);

        this.dialog = DriversManager.current().findElement(By.xpath("//*[@id=\"login-content\"]"));
    }
}
