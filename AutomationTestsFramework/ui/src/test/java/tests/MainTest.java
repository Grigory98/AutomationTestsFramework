package tests;

import core.DriversManager;
import core.AllureExtensions;
import generates.TestDataGenerate;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.MainPageSteps;

@Epic("UI")
@ExtendWith(AllureExtensions.class)
public class MainTest {
    public final String messageTitle = TestDataGenerate.generateString(15);
    public final String messageDescription = TestDataGenerate.generateString(15);

    @BeforeAll
    @Step("Авторизация пользователя")
    public static void logIn() {
        new MainPageSteps()
                .signIn()
                .authorize();
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
