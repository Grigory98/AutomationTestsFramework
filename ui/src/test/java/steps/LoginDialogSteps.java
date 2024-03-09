package steps;

import dialogs.LoginDialogWindow;
import io.qameta.allure.Step;

public class LoginDialogSteps {
    @Step("Заполнение полей входа в почту и переход в почтовый ящик")
    public MailSteps authorize() {
        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(System.getProperty("uiusername"));
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(System.getProperty("uipassword"));
        loginDialogWindow.clickSignInButton();
        return new MailSteps();
    }
}
