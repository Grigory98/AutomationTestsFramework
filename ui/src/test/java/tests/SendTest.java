package tests;

import core.DriversManager;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import steps.MailSteps;

@Epic("UI")
public class SendTest extends MainTest {
    @BeforeEach
    public void beforeSendMessage() {
        // По хорошему нужно создавать сообщение через API.
        DriversManager.current().get("https://e.mail.ru/inbox/");
        new MailSteps()
                .sendMessage()
                .fillMessageFields(this.messageTitle, this.messageDescription)
                .clickSendMessage()
                .closeWindow();
    }
}
