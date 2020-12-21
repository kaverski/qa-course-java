package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomeNr());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(getWd().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void acceptAlert() {
        getWd().switchTo().alert().accept();
    }
}