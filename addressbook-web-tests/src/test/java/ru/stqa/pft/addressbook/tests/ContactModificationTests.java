package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationViaEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test555"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getNavigationHelper().goToContactEditPage();
        app.getContactHelper().editContact(new ContactData("UpdatedName",
                "UpdatedMiddle", "UpdatedLast", "8888", "test111@test888.test555", null), false);
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testContactModificationViaDetailsPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test555"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getNavigationHelper().goToContactDetailsPage();
        app.getNavigationHelper().goToModifyPage();
        app.getContactHelper().editContact(new ContactData("UpdatedName",
                "UpdatedMiddle", "UpdatedLast", "8888", "test111@test888.test555", null), false);
        app.getNavigationHelper().goToHomePage();
    }
}