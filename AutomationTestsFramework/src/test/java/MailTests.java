import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import dialogs.SendMessageDialogWindow;
import org.junit.jupiter.api.*;
import pages.MailPage;
import pages.MainPage;

public class MailTests {

    @BeforeAll
    public static void logIn() {
        MainPage mainPage = new MainPage();
        mainPage.signInButton.click();

        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(ApplicationConfig.username);
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(ApplicationConfig.password);
        loginDialogWindow.clickSignInButton();
    }

    @Test
    @DisplayName("Отправка сообщения")
    public void sendLetter() {
        MailPage mailPage = new MailPage();
        mailPage.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic("TestTopic");
        dialogWindow.fillTextbox("TestText");
        Assertions.assertTrue(dialogWindow.sendMessage(), "Появился заголовок об успешной отправке сообщения.");
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
