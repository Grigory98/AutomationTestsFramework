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
@Feature("Тесты на поиск сообщений")
public class SearchMessageTest extends SendTest {
    @Owner("Grigory98")
    @DisplayName("Поиск сообщения")
    @Description("Тест на поиск сообщения в почтовом ящике")
    @Test
    public void searchLetter() {
        new MailSteps()
                .startSearch(this.messageTitle)
                .checkMessageExists(this.messageTitle);
    }
}
