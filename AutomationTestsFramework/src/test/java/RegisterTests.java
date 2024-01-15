import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.RegisterPage;

public class RegisterTests {

    @Test
    @DisplayName("Тест регистрации нового пользователя")
    public void registerNewUserTest() {
        MainPage mainPage = new MainPage();
        mainPage.registerButton.click();
        RegisterPage registerPage = new RegisterPage();
        registerPage.firstName.sendKeys("TestFirstName");
        registerPage.lastName.sendKeys("TestLastName");
        registerPage.sendBirthDate(1, 5, 2000);
        registerPage.selectCountry("ru");
        registerPage.phoneNumber.sendKeys("88005553535");
    }
}
