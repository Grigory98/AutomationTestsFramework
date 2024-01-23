package tests;

import core.ApplicationConfig;
import core.DriversManager;
import dialogs.LoginDialogWindow;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.MainPage;
import utils.TestDataGenerate;

public class MainTest {
    public final String messageTitle = TestDataGenerate.generateString(15);
    public final String messageDescription = TestDataGenerate.generateString(15);

    @BeforeAll
    public static void logIn() {
        MainPage mainPage = new MainPage();
        mainPage.signInButton.click();

        LoginDialogWindow loginDialogWindow = new LoginDialogWindow();
        loginDialogWindow.fillUsername(ApplicationConfig.username);
        loginDialogWindow.clickNextButton();
        loginDialogWindow.fillPassword(ApplicationConfig.password);
        loginDialogWindow.clickSignInButton();
    }

    @AfterAll
    public static void tearDown() {
        DriversManager.closeBrowser();
    }
}
