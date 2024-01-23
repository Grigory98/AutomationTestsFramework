package tests.mail;

import grids.MailGrid;
import grids.items.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.SendTest;

public class FilterMessageWithFlagTest extends SendTest {

    @Test
    @DisplayName("Фильтрация писем с флагом")
    public void filterMessageWithFlagTest() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(this.messageTitle);
        Message message = mailGrid.getMessage(this.messageTitle);
        message.markAsFavorite();
        mailGrid.resetSearch();
        mailGrid.startSearchFavorite();
        Assertions.assertNotNull(mailGrid.getMessage(this.messageTitle), "Избранное сообщение отображается в гриде");
    }
}
