package steps;

import grids.MailGrid;
import grids.items.Message;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class MailSteps {
    @Step("Начать поиск по тексту: {text}")
    public MailSteps startSearch(String text) {
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearch(text);
        return this;
    }

    @Step("Начать поиск избранных сообщений")
    public MailSteps startSearchFavorite() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.startSearchFavorite();
        return this;
    }

    @Step("Сбросить поиск")
    public MailSteps resetSearch() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.resetSearch();
        return this;
    }

    @Step("Отправить сообщение")
    public SendMessageDialogSteps sendMessage() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.sendMessage();
        return new SendMessageDialogSteps();
    }

    @Step("Перейти в раздел \"Черновики\"")
    public MailSteps goToDrafts() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.goToDrafts();
        return this;
    }

    @Step("Перейти в раздел \"Корзина\"")
    public MailSteps goToBasket() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.goToBasket();
        return this;
    }

    @Step("Выделить сообщение с заголовком: {topic}")
    public MailSteps selectMessage(String topic) {
        MailGrid mailGrid = new MailGrid();
        Message message = mailGrid.getMessage(topic);
        message.selectMessage();
        return this;
    }

    @Step("Пометить сообщение {topic} как избранное")
    public MailSteps markMessageAsFavorite(String topic) {
        MailGrid mailGrid = new MailGrid();
        Message message = mailGrid.getMessage(topic);
        message.markAsFavorite();
        return this;
    }

    @Step("Удалить выделенные сообщения")
    public MailSteps deleteMessages() {
        MailGrid mailGrid = new MailGrid();
        mailGrid.deleteMessgaes();
        return this;
    }

    @Step("Проверить, что сообщение содержится в гриде")
    public MailSteps checkMessageExists(String title) {
        MailGrid mailGrid = new MailGrid();
        Message message = mailGrid.getMessage(title);
        Assertions.assertNotNull(message, "Сообщение содержится в гриде");
        return this;
    }
}
