package tests.mail;

import grids.MailGrid;
import grids.items.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.SendTest;

public class DeleteMessageTest extends SendTest {
    @Test
    @DisplayName("Удаление сообщения")
    public void deleteMessages() {
        String hintMessage = "Перемещено в папку «Корзина»";
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(this.messageTitle);
        Message message = mailGrid.getMessage(this.messageTitle);
        message.selectMessage();
        mailGrid.deleteMessgaes();
        //Assertions.assertTrue(mailGrid.checkHintExists(hintMessage), "Появился хинт с сообщением: " + hintMessage);
        mailGrid.resetSearch();
        mailGrid.goToBasket();
        Message messageInBasket = mailGrid.getMessage(this.messageTitle);
        Assertions.assertNotNull(messageInBasket, "Удалённое сообщение находится в корзине");
    }
}
