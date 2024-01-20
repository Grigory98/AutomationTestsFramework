import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import grids.items.Message;
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
        mailGrid.startSearch("TestTopic");
        Message message = mailGrid.getMessage("TestTopic");
        Assertions.assertNotNull(message, "Появилось сообщение в гриде");
    }

    @Test
    @DisplayName("Удаление сообщения")
    public void deleteMessages() {
        String hintMessage = "Перемещено в папку «Корзина»";
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch("TestTopic");
        Message message = mailGrid.getMessage("TestTopic");
        message.selectMessage();
        mailGrid.deleteMessgaes();
        Assertions.assertTrue(mailGrid.checkHintExists(hintMessage), "Появился хинт с сообщением: " + hintMessage);
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
