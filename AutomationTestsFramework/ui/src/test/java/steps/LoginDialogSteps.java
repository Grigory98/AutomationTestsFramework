package steps;

import core.ApplicationConfig;
import dialogs.LoginDialogWindow;
import io.qameta.allure.Step;

public class LoginDialogSteps {
    @Step("Заполнение полей входа в почту и переход в почтовый ящик")
    public MailSteps authorize() {
        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(ApplicationConfig.username);
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(ApplicationConfig.password);
        loginDialogWindow.clickSignInButton();
        return new MailSteps();
    }
}
