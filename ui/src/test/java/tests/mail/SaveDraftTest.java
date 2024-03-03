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
@Feature("Тесты на сохранение сообщений")
public class SaveDraftTest extends MainTest {
    @Owner("Grigory98")
    @DisplayName("Сохранить сообщение как черновик")
    @Description("Тест на сохранение сообщения в черновиках")
    @Test
    public void saveAsDraft() {
        new MailSteps()
                .sendMessage()
                .fillMessageFields(this.messageTitle, this.messageDescription)
                .clickSaveAsDraft()
                .closeWindow()
                .goToDrafts()
                .checkMessageExists(this.messageTitle);
    }
}
