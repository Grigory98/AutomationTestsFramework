package steps;

import core.ApplicationConfig;
import io.qameta.allure.Step;
import pages.SignInPage;

public class SignInSteps {
    @Step("Вход в почтовый ящик")
    public void authorizeWithoutWindow() {
        SignInPage signInPage = new SignInPage();
        signInPage.fillUsername(ApplicationConfig.USERNAME);
        signInPage.clickNext();
        signInPage.fillPassword(ApplicationConfig.PASSWORD);
        signInPage.clickSubmit();
    }
}
