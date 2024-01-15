package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends AbstractPage {
    @FindBy(how = How.CSS, using = ".resplash-btn_primary")
    public WebElement signInButton;

    @FindBy(how = How.CSS, using = ".resplash-btn_outlined-themed")
    public WebElement registerButton;
}
