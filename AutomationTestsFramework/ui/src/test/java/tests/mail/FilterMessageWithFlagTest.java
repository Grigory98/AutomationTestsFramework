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
@Feature("Тесты фильтрации")
public class FilterMessageWithFlagTest extends SendTest {
    @Owner("Grigory98")
    @DisplayName("Фильтрация писем с флагом")
    @Description("Тест на фильтрацию письма с флагом \"Важные\"")
    @Test
    public void filterMessageWithFlagTest() {
        new MailSteps()
                .startSearch(this.messageTitle)
                .markMessageAsFavorite(this.messageTitle)
                .resetSearch()
                .startSearchFavorite()
                .checkMessageExists(this.messageTitle);
    }
}
