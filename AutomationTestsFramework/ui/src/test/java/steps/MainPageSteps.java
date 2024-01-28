package steps;

import io.qameta.allure.Step;
import pages.MainPage;

public class MainPageSteps {
    @Step("Войти в почту")
    public LoginDialogSteps signIn() {
        MainPage mainPage = new MainPage();
        mainPage.signInButton.click();
        return new LoginDialogSteps();
    }
}
