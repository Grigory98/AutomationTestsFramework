package tests.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.SendTest;

public class SearchMessageTest extends SendTest {
    @Test
    @DisplayName("Поиск сообщения")
    public void searchLetter() {
        new MailSteps()
                .startSearch(this.messageTitle)
                .checkMessageExists(this.messageTitle);
    }
}
