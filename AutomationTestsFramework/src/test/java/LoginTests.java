import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class LoginTests {
    @Test
    @DisplayName("Вход в почтовый ящик")
    public void logIn() {
        MainPage mainPage = new MainPage();
        mainPage.signInButton.click();

        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(ApplicationConfig.username);
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(ApplicationConfig.password);
        loginDialogWindow.clickSignInButton();
        Assertions.assertTrue(
                DriversManager.current().getCurrentUrl().contains("https://e.mail.ru/inbox/"),
                "Вход в почтовый ящик выполнен успешно"
        );
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
