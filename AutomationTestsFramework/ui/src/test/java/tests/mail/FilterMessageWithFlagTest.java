package tests.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MailSteps;
import tests.SendTest;

public class FilterMessageWithFlagTest extends SendTest {
    @Test
    @DisplayName("Фильтрация писем с флагом")
    public void filterMessageWithFlagTest() {
        new MailSteps()
                .startSearch(this.messageTitle)
                .markMessageAsFavorite(this.messageTitle)
                .resetSearch()
                .startSearchFavorite()
                .checkMessageExists(this.messageTitle);
    }
}
