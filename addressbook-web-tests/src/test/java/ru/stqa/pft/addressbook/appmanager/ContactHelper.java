package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
        getWd().findElements(By.name("selected[]")).get(index).click();
    }

    public void acceptAlert() {
        getWd().switchTo().alert().accept();
    }

    public void createContact(ContactData contactData, boolean creation) {
        fillContactForm(contactData, creation);
        submitContactForm();
    }

    public void editContact(ContactData contactData, boolean creation) {
        fillContactForm(contactData, creation);
        submitContactModification();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> tableEntries = getWd().findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement tableEntry : tableEntries) {
            String lastName = tableEntry.findElement(By.xpath("//td[2]")).getText();
            String firstName = tableEntry.findElement(By.xpath("//td[3]")).getText();
            int id = Integer.parseInt(tableEntry.findElement(By.xpath("//td[1]/input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}