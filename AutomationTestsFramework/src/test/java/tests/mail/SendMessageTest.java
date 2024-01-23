package tests.mail;

import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.MainTest;

public class SendMessageTest extends MainTest {
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
}
