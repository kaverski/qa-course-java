package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    //groupHelper obj getter to access GroupHelper methods
    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    //navigationHelper obj to access NavigationHelper methods
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");

        //groupHelper получает ссылку на driver
        //создается groupHelper после создания driver
        groupHelper = new GroupHelper(wd);

        //navigationHelper получает ссылку на driver
        //создается navigationHelper
        navigationHelper = new NavigationHelper(wd);

        sessionHelper = new SessionHelper(wd); //передаем ссылку на driver
        sessionHelper.login("admin", "secret");

        contactHelper = new ContactHelper(wd);
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}