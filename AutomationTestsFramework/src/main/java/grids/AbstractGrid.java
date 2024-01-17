package grids;

import core.DriversManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractGrid {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractGrid() {
        this.driver = DriversManager.current();
        this.wait = DriversManager.waitFor();
        PageFactory.initElements(driver, this);
    }
}
