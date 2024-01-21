import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import grids.items.Message;
import org.junit.jupiter.api.*;
import pages.MainPage;
import utils.TestDataGenerate;

public class MailTests {
    public final String messageTitle = TestDataGenerate.generateString(15);
    public final String messageDescription = TestDataGenerate.generateString(15);

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

    //TODO: Before и After перенести в хуки
    private SendMessageDialogWindow beforeSendMessage() {
        // По хорошему нужно создавать сообщение через API.
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic(this.messageTitle);
        dialogWindow.fillTextbox(this.messageDescription);
        dialogWindow.sendMessage();
        return dialogWindow;
    }

    private void afterSendMessage(SendMessageDialogWindow dialogWindow ) {
        dialogWindow.closeWindow();
    }

    @Test
    @DisplayName("Отправка сообщения")
    public void sendLetter() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic(this.messageTitle);
        dialogWindow.fillTextbox(this.messageDescription);
        Assertions.assertTrue(dialogWindow.sendMessage(), "Появился заголовок об успешной отправке сообщения.");
    }

    @Test
    @DisplayName("Поиск сообщения")
    public void searchLetter() {
        SendMessageDialogWindow dialog = beforeSendMessage();
        afterSendMessage(dialog);

        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(this.messageTitle);
        Message message = mailGrid.getMessage(this.messageTitle);
        Assertions.assertNotNull(message, "Появилось сообщение в гриде");
    }

    @Test
    @DisplayName("Удаление сообщения")
    public void deleteMessages() {
        String hintMessage = "Перемещено в папку «Корзина»";
        SendMessageDialogWindow dialog = beforeSendMessage();
        afterSendMessage(dialog);

        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(this.messageTitle);
        Message message = mailGrid.getMessage(this.messageTitle);
        message.selectMessage();
        mailGrid.deleteMessgaes();
        //Assertions.assertTrue(mailGrid.checkHintExists(hintMessage), "Появился хинт с сообщением: " + hintMessage);
        mailGrid.resetSearch();
        mailGrid.moveToBasket();
        Message messageInBasket = mailGrid.getMessage(this.messageTitle);
        Assertions.assertNotNull(messageInBasket, "Удалённое сообщение находится в корзине");
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
