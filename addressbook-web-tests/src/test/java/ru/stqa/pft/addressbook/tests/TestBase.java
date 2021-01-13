package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app =
           new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); // если браузер не указан то дефолтный chrome

    @BeforeSuite
    public void setUp() throws Exception {
        //referencing to the appMan-class to invoke methods to perform actions on AUT
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }
}