package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test555"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getNavigationHelper().goToContactEditPage();
        app.getContactHelper().deleteContact();
    }

    @Test
    public void testContactDeletionFromHomePage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("InitialName",
                    "InitialMiddle", "InitialLast", "7777", "test222@test333.test444", "test555"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
    }
}
