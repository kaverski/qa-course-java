package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().fillContactForm(new ContactData("ABC", "MyMiddle", "MyLast", "265699", "test@test.test"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goToHomePage();
    }
}