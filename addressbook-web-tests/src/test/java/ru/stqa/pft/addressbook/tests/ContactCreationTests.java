package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().createContact(new ContactData("NewFirst1", "NewMiddle12", "NewLast12", "121212", "newtest11@newtest.newtest", "test555"), true);
        app.getNavigationHelper().goToHomePage();

    }
}