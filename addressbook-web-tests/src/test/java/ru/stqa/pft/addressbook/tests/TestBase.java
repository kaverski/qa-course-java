package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class); // класс с которым ассоц logger

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); // если браузер не указан то дефолтный chrome

    @BeforeSuite
    public void setUp() throws Exception {
        //referencing to the appMan-class to invoke methods to perform actions on AUT
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] o) { //содержит инфо о тест методе который запускается
        logger.info("Start test " + m.getName() + Arrays.asList(o));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) { //если сист свойство установлено то делать поверку
            Groups groupsFromDB = app.getDbHelper().getGroups();
            Groups groupsFromUI = app.getGroupHelper().all();
            assertThat(groupsFromUI, equalTo(groupsFromDB.stream() //упрощение списка полученного из DB (преобразование в новую коллекйию)
                    .map(g -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }

    }
}