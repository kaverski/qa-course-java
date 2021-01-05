package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && getWd().findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void goToAddContactPage() {
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page")); //
    }

    public void clickHomePage() {
        getWd().findElement(By.xpath("//a[@href='./']")).click();
    }

    public void goToContactDetailsPage(int index) {
        getWd().findElements(By.xpath("//img[@title='Details']")).get(index).click();
    }

    public void goToContactDetailsPageById(int id) {
        getWd().findElement(By.xpath("//a[@href='view.php?id=" + id + "']")).click();
    }

    public void goToModifyPage() {
        click(By.name("modifiy"));
    }

    public void goToContactEditPage(int index) {
        getWd().findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    }

    public void goToContactEditPageById(int id) {
        getWd().findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }
}