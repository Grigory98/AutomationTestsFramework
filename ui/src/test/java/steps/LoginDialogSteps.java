package steps;

import core.ApplicationConfig;
import dialogs.LoginDialogWindow;
import io.qameta.allure.Step;

public class LoginDialogSteps {
    @Step("Заполнение полей входа в почту и переход в почтовый ящик")
    public MailSteps authorize() {
        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(ApplicationConfig.USERNAME);
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(ApplicationConfig.PASSWORD);
        loginDialogWindow.clickSignInButton();
        return new MailSteps();
    }
}
