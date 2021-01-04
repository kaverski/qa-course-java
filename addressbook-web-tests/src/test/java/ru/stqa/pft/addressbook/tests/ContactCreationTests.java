package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        //actual element list BEFORE adding new contact
        Contacts before = app.getContactHelper().getContacts();
        System.out.println("before: " + before);

        app.getNavigationHelper().goToAddContactPage();
        ContactData contactToAdd = new ContactData().withFirstName("11First")
                .withMiddleName("Onemore2")
                .withLastName("rrZZZFirstLast")
                .withHomeNr("87878")
                .withEmail("newtest123@newtest.newtest")
                .withGroup("11test000");

        app.getContactHelper().createContact(contactToAdd, true);
        app.getNavigationHelper().goToHomePage();

        //actual element list AFTER adding new contact
        Contacts after = app.getContactHelper().getContacts();
        System.out.println(after);
       // assertThat(after.size(), equalTo(before.size() + 1));
        System.out.println("after: " + after.size());
        System.out.println("before: " + (before.size()+1));

        assertThat(after, equalTo(
                before.withAdded(contactToAdd.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}