package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test777"), true);
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToContactEditPage(before.size() - 1);
        app.getContactHelper().deleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);

        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactDeletionFromHomePage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test777"), true);
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);

        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }
}