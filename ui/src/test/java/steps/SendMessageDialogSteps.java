package steps;

import dialogs.SendMessageDialogWindow;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class SendMessageDialogSteps {
    @Step("Заполнение полей сообщения")
    public SendMessageDialogSteps fillMessageFields(String title, String description) {
        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.fillForWhom();
        dialogWindow.fillTopic(title);
        dialogWindow.fillTextbox(description);
        return this;
    }

    @Step("Отправка сообщения")
    public SendMessageDialogSteps clickSendMessage() {
        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.sendMessage();
        return this;
    }

    @Step("Проверка заголовка об успешной отправки сообщения")
    public SendMessageDialogSteps checkAfterSendMessage() {
        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        Assertions.assertTrue(dialogWindow.checkAfterSendMessage(), "Появился заголовок об успешной отправке сообщения.");
        return this;
    }

    @Step("Сохранить сообщение как черновик")
    public SendMessageDialogSteps clickSaveAsDraft() {
        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.saveMessageAsDraft();
        return this;
    }

    @Step("Закрыть окно отправки сообщения")
    public MailSteps closeWindow() {
        SendMessageDialogWindow dialogWindow = new SendMessageDialogWindow();
        dialogWindow.closeWindow();
        return new MailSteps();
    }
}
