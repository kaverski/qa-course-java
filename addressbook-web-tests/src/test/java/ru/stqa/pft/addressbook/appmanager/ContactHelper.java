package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        type(By.name("mobile"), contactData.getMobileNr());
        type(By.name("work"), contactData.getWorkNr());
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

    public int getContactCount() {
        return getWd().findElements(By.name("selected[]")).size();
    }

    public void selectContactById(int id) {
        getWd().findElement(By.xpath("//input[@id='" + id + "']")).click();
    }

    public void acceptAlert() {
        getWd().switchTo().alert().accept();
    }

    public void createContact(ContactData contactData, boolean creation) {
        fillContactForm(contactData, creation);
        submitContactForm();
    }

    public void editContact(ContactData contact, boolean creation) {
        fillContactForm(contact, creation);
        submitContactModification();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts getContacts() {
        Contacts contacts = new Contacts();
        List<WebElement> tableEntries = getWd().findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement tableEntry : tableEntries) {
            String lastName = tableEntry.findElement(By.xpath(".//td[2]")).getText();
            String firstName = tableEntry.findElement(By.xpath(".//td[3]")).getText();

            //get all phones from cell
            String allPhones = tableEntry.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(tableEntry.findElement(By.xpath(".//td[1]/input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .willAllPhones(allPhones);
            contacts.add(contact);
        }
        return contacts;
    }

    public void goToContactEditPageById(int id) {
        getWd().findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public ContactData getInfoFromEditForm(ContactData contact) {
        goToContactEditPageById(contact.getId());
        String firstName = getWd().findElement(By.name("firstname")).getAttribute("value");
        String lastName = getWd().findElement(By.name("lastname")).getAttribute("value");
        String homeNr = getWd().findElement(By.name("home")).getAttribute("value");
        String mobileNr = getWd().findElement(By.name("mobile")).getAttribute("value");
        String workNr = getWd().findElement(By.name("work")).getAttribute("value");
        return new ContactData().withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomeNr(homeNr)
                .withMobileNr(mobileNr)
                .withWorkNr(workNr);
    }
}