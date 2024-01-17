import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import org.junit.jupiter.api.*;
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
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic("TestTopic");
        dialogWindow.fillTextbox("TestText");
        Assertions.assertTrue(dialogWindow.sendMessage(), "Появился заголовок об успешной отправке сообщения.");
    }

    @Test
    @DisplayName("Поиск сообщения")
    public void searchLetter() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.searchMessage("TestTopic");
        Assertions.assertNotNull(mailGrid.getMessage("TestTopic"), "Появилось сообщение в гриде");
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
