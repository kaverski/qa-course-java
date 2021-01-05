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

        app.getNavigationHelper().goToAddContactPage();
        ContactData contactToAdd = new ContactData().withFirstName("11First")
                .withMiddleName("Onemore2")
                .withLastName("rrZZZFirstLast")
                .withHomeNr("87878")
                .withEmail("newtest123@newtest.newtest")
                .withGroup("test55");

        app.getContactHelper().createContact(contactToAdd, true);
        app.getNavigationHelper().goToHomePage();

        //предварительная проверка размера списка
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        //actual element list AFTER adding new contact
        Contacts after = app.getContactHelper().getContacts();
        assertThat(after, equalTo(
                before.withAdded(contactToAdd.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}