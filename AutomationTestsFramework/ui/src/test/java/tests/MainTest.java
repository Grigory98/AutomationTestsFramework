package tests;

import core.DriversManager;
import generates.TestDataGenerate;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import steps.MainPageSteps;

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
