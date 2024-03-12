package tests;

import core.AllureExtensions;
import core.DriversManager;
import generates.TestDataGenerate;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.SignInSteps;

@Epic("UI")
@ExtendWith(AllureExtensions.class)
public class MainTest {
    public final String messageTitle = TestDataGenerate.generateString(15);
    public final String messageDescription = TestDataGenerate.generateString(15);

    @BeforeAll
    @Step("Авторизация пользователя")
    public static void logIn() {
        new SignInSteps().authorizeWithoutWindow();
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
