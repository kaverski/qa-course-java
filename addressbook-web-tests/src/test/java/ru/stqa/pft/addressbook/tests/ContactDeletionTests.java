package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletionFromEditPage(){
        app.getNavigationHelper().goToContactEditPage();
        app.getContactHelper().deleteContact();
    }

    @Test
    public void testContactDeletionFromHomePage(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
    }
}
