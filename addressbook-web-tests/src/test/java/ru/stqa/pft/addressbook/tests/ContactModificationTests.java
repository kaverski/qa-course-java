package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationViaEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact();
            app.getNavigationHelper().goToHomePage();
        }
        app.getNavigationHelper().goToContactEditPage();
        app.getContactHelper().editContact();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testContactModificationViaDetailsPage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact();
            app.getNavigationHelper().goToHomePage();
        }
        app.getNavigationHelper().goToContactDetailsPage();
        app.getNavigationHelper().goToModifyPage();
        app.getContactHelper().editContact();
        app.getNavigationHelper().goToHomePage();
    }
}