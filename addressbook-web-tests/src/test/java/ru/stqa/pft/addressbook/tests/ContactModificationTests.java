package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationViaEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test777"), true);
            app.getNavigationHelper().goToHomePage();
        }
        //initial contact list
        List<ContactData> before = app.getContactHelper().getContactList();
        //select contact for edit by index
        app.getNavigationHelper().goToContactEditPage(before.size()-1);
        //edited contact
        ContactData contactToEdit = new ContactData(before.get(before.size() - 1).getId(),  "First",
                "Middle", "Last", "8888", "test111@test888.test555", null);
        app.getContactHelper().editContact(contactToEdit, false);
        app.getNavigationHelper().goToHomePage();
        //after edit list
        List<ContactData> after = app.getContactHelper().getContactList();
        //removing old last-element
        before.remove(before.size()-1);
        //adding edited element
        before.add(contactToEdit);
        //sorting
        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }

    @Test
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
                "UpdatedMiddle", "000UpdatedLast", "8888", "test111@test888.test555", null);
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