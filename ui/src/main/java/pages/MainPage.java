package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends AbstractPage {
    @FindBy(how = How.CSS, using = ".resplash-btn_primary")
    public WebElement signInButton;

    @FindBy(how = How.CSS, using = "[href=\"https://trk.mail.ru/c/jlsxn4?mt_sub1=mail.ru\"]")
    public WebElement mailButton;
}
