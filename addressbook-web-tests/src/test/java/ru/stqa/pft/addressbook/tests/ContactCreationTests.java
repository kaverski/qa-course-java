package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().createContact();
        app.getNavigationHelper().goToHomePage();
    }
}