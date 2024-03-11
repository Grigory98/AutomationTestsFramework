package core;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriversManager {
    private static WebDriver _current;

    private static WebDriverWait _wait;

    public static boolean isDriverExist() {
        return _current != null;
    }

    public static WebDriver current() {
        if (!DriversManager.isDriverExist()) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            if(ApplicationConfig.HEADLESS_MODE)
                chromeOptions.addArguments("--headless");
            _current = new ChromeDriver(chromeOptions);
            _current.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICITLY_WAIT));
            _current.manage().window().maximize();
            _current.get(ApplicationConfig.URL);
        }
        return _current;
    }

    @Attachment(value = "Screenshoot", type = "image/png")
    public static byte[] screenshoot() {
        return ((TakesScreenshot) DriversManager.current()).getScreenshotAs(OutputType.BYTES);
    }

    public static void closeBrowser() {
        _wait = null;
        _current.quit();
        _current = null;
    }

    public static WebDriverWait waitFor() {
        if(_wait == null) {
            _wait = new WebDriverWait(DriversManager.current(), Duration.ofSeconds(20));
        }
        return _wait;
    }

    private static class Constants {
        public static final long IMPLICITLY_WAIT = 5000;
    }
}