package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToAddContactPage() {
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }

    public void goToContactDetailsPage() {
        click(By.xpath("(//img[@title='Details'])[2]"));
    }

    public void goToModifyPage() {
        click(By.name("modifiy"));
    }

    public void goToContactEditPage() {
        click(By.xpath("(//img[@title='Edit'])[3]"));
    }
}