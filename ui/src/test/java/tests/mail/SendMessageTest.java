package tests.mail;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.MainTest;

@Epic("UI")
@Feature("Тесты на отправку сообщений")
public class SendMessageTest extends MainTest {
    @Owner("Grigory98")
    @DisplayName("Отправка сообщения")
    @Description("Тест на отправку сообщения")
    @Test
    public void sendLetter() {
        new MailSteps()
                .sendMessage()
                .fillMessageFields(this.messageTitle, this.messageDescription)
                .clickSendMessage()
                .checkAfterSendMessage();
    }
}
