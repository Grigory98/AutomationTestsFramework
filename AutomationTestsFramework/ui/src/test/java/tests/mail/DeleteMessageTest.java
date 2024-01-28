package tests.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.SendTest;

public class DeleteMessageTest extends SendTest {
    @Test
    @DisplayName("Удаление сообщения")
    public void deleteMessages() {
        new MailSteps()
                .startSearch(this.messageTitle)
                .selectMessage(this.messageTitle)
                .deleteMessages()
                .resetSearch()
                .goToBasket()
                .checkMessageExists(this.messageTitle);
    }
}
