package core;

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
            if(ApplicationConfig.headlessMode)
                chromeOptions.addArguments("--headless");
            _current = new ChromeDriver(chromeOptions);
            _current.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            _current.manage().window().maximize();
            _current.get(ApplicationConfig.url);
        }
        return _current;
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

}