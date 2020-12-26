package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToAddContactPage();

        ContactData contactToAdd = new ContactData("Fourth", "Onemore2", "FourthLast", "87878", "newtest123@newtest.newtest", "test3");
        app.getContactHelper().createContact(contactToAdd, true);
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        System.out.println("after:" + after);

        before.add(contactToAdd);
        System.out.println("before: " + before);

        // Comparator<ContactData> byID = Comparator.comparing(ContactData::getLastName).thenComparing(ContactData::getFirstName);
        Comparator<ContactData> byID = Comparator.comparing(ContactData::getId);

        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);
    }
}