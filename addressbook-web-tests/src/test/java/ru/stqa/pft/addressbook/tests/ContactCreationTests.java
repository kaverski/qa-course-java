package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        //actual element list BEFORE adding new contact
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToAddContactPage();
        ContactData contactToAdd = new ContactData("11First", "Onemore2", "ZZZFirstLast", "87878", "newtest123@newtest.newtest", "test3");
        app.getContactHelper().createContact(contactToAdd, true);
        app.getNavigationHelper().goToHomePage();

        //actual element list AFTER adding new contact
        List<ContactData> after = app.getContactHelper().getContactList();

        before.add(contactToAdd);

        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);
        before.sort(byID);
        after.sort(byID);
        System.out.println("before: " + before);
        System.out.println("after:" + after);
        Assert.assertEquals(before, after);
    }
}