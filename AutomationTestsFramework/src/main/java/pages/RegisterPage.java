package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class RegisterPage extends AbstractPage {
    @FindBy(how = How.ID, using = "fname")
    public WebElement firstName;

    @FindBy(how = How.ID, using = "lname")
    public WebElement lastName;

    @FindBy(how = How.NAME, using = "partial_login")
    public WebElement login;

    @FindBy(how = How.ID, using = "password")
    public WebElement password;

    @FindBy(how = How.ID, using = "phone-number__phone-input")
    public WebElement phoneNumber;

    @FindBy(how = How.CSS, using = "[value = \"male\"]")
    public WebElement maleRadioButton;

    @FindBy(how = How.CSS, using = "[value = \"female\"]")
    public WebElement femaleRadioButton;

    @FindBy(how = How.CSS, using = "[class *= \"daySelect\"]")
    public WebElement daySelect;

    @FindAll({@FindBy(how = How.CSS, using = ".Select__control")})
    public List<WebElement> selectors;

    @FindBy(how = How.CSS, using = "[data-test-id = \"select-menu-wrapper\"]")
    public WebElement wrapper;

    @FindBy(how = How.XPATH, using = "//span[text() = \"Создать\"]")
    public WebElement createButton;

    public void sendBirthDate(int day, int month, int year) {
        selectors.get(0).click();
        wrapper.findElement(By.xpath("//*[text() = '"+day+"']")).click();
        selectors.get(1).click();
        wrapper.findElement(By.cssSelector("[data-test-id = \"select-value:"+month+"\"]")).click();
        selectors.get(2).click();
        wrapper.findElement(By.cssSelector("[data-test-id = \"select-value:"+year+"\"]")).click();
    }

    public void selectCountry(String country) {
        selectors.get(3).click();
        wrapper.findElement(By.cssSelector("[data-test-id = \"select-value:"+country+"\"]")).click();
    }
}
