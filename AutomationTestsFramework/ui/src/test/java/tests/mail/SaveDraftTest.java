package tests.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.MainTest;

public class SaveDraftTest extends MainTest {
    @Test
    @DisplayName("Сохранить сообщение как черновик")
    public void saveAsDraft() {
        new MailSteps()
                .sendMessage()
                .fillMessageFields(this.messageTitle, this.messageDescription)
                .clickSaveAsDraft()
                .closeWindow()
                .goToDrafts()
                .checkMessageExists(this.messageTitle);
    }
}
