package tests.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.MainTest;

public class SendMessageTest extends MainTest {
    @Test
    @DisplayName("Отправка сообщения")
    public void sendLetter() {
        new MailSteps()
                .sendMessage()
                .fillMessageFields(this.messageTitle, this.messageDescription)
                .clickSendMessage()
                .checkAfterSendMessage();
    }
}
