package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    private WebDriver wd;

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public WebDriver getWd(){
        return wd;
    }

    public void type(By locator, String input) {
        click(locator);
        if (input != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!input.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(input);
            }
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            getWd().findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex) {
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
