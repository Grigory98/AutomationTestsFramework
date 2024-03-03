package tests.mail;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.SendTest;

@Epic("UI")
@Feature("Тесты на удаление сообщений")
public class DeleteMessageTest extends SendTest {
    @Owner("Grigory98")
    @DisplayName("Удаление сообщения")
    @Description("Тест на удаление сообщения в почтовом ящике")
    @Test
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
