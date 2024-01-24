package tests.mail;

import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.MainTest;

public class SaveDraftTest extends MainTest {
    @Test
    @DisplayName("Сохранить сообщение как черновик")
    public void saveAsDraft() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic(this.messageTitle);
        dialogWindow.fillTextbox(this.messageDescription);
        dialogWindow.saveMessageAsDraft();

        dialogWindow.closeWindow();
        mailGrid.goToDrafts();
        Assertions.assertNotNull(mailGrid.getMessage(this.messageTitle), "Сообщение сохранено в черновиках");
    }
}
