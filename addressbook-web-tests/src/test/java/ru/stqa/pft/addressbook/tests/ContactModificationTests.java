package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModificationViaEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test777"), true);
            app.getNavigationHelper().goToHomePage();
        }
        //actual element list BEFORE edit contact
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToContactEditPage(before.size() - 1);
        ContactData contactToEdit = new ContactData(before.get(before.size() - 1).getId(), "First",
                "Middle", "Last", "8888", "test111@test888.test555", null);
        app.getContactHelper().editContact(contactToEdit, false);
        app.getNavigationHelper().goToHomePage();

        //actual element list AFTER edit contact
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        before.add(contactToEdit);

        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = false)
    public void testContactModificationViaDetailsPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test777"), true);
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToContactDetailsPage(before.size() - 1);
        app.getNavigationHelper().goToModifyPage();
        ContactData contactToEdit = new ContactData(before.get(before.size() - 1).getId(), "UPDFirst",
                "UpdatedMiddle", "BBBUpdatedLast", "8888", "test111@test888.test555", null);
        app.getContactHelper().editContact(contactToEdit, false);
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        before.add(contactToEdit);

        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }
}