package tests.mail;

import grids.MailGrid;
import grids.items.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.SendTest;

public class SearchMessageTest extends SendTest {
    @Test
    @DisplayName("Поиск сообщения")
    public void searchLetter() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(this.messageTitle);
        Message message = mailGrid.getMessage(this.messageTitle);
        Assertions.assertNotNull(message, "Появилось сообщение в гриде");
    }
}
