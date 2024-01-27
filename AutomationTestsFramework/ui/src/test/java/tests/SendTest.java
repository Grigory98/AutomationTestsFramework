package tests;

import core.DriversManager;
import dialogs.SendMessageDialogWindow;
import grids.MailGrid;
import org.junit.jupiter.api.BeforeEach;

public class SendTest extends MainTest {
    @BeforeEach
    public void beforeSendMessage() {
        // По хорошему нужно создавать сообщение через API.
        DriversManager.current().get("https://e.mail.ru/inbox/");
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();

        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic(this.messageTitle);
        dialogWindow.fillTextbox(this.messageDescription);
        dialogWindow.sendMessage();
        dialogWindow.closeWindow();
    }
}
