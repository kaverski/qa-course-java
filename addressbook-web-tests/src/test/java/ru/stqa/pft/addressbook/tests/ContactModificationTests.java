package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getContactHelper().getContacts().size() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData().withFirstName("InitialName")
                    .withMiddleName("InitialMiddle")
                    .withLastName("InitialLast")
                    .withHomeNr("7777")
                    .withEmail("test222@test333.test444"), true);
            app.getNavigationHelper().goToHomePage();
        }
    }

    @Test
    public void testContactModificationViaEditPage() {
        //actual element list BEFORE edit contact
        Contacts before = app.getContactHelper().getContacts();

        //contact to modify
        ContactData contactToModify = before.iterator().next();

        app.getContactHelper().goToContactEditPageById(contactToModify.getId());

        //создать новый контакт с ИД модифиц контакта
        ContactData contact = new ContactData().withId(contactToModify.getId())
                .withFirstName("First")
                .withMiddleName("Middle")
                .withLastName("Last")
                .withHomeNr("8888")
                .withEmail("test111@test888.test555");

        app.getContactHelper().editContact(contact, false);
        app.getNavigationHelper().goToHomePage();

        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        //actual element list AFTER edit contact
        Contacts after = app.getContactHelper().getContacts();

        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }

    @Test
    public void testContactModificationViaDetailsPage() {
        Contacts before = app.getContactHelper().getContacts();
        ContactData contactToModify = before.iterator().next();
        app.getNavigationHelper().goToContactDetailsPageById(contactToModify.getId());
        app.getNavigationHelper().goToModifyPage();

        ContactData contact = new ContactData().withId(contactToModify.getId())
                .withFirstName("UPDFirst")
                .withMiddleName("UpdatedMiddle")
                .withLastName("BBBUpdatedLast")
                .withHomeNr("8888")
                .withEmail("test111@test888.test555");

        app.getContactHelper().editContact(contact, false);
        app.getNavigationHelper().goToHomePage();
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        Contacts after = app.getContactHelper().getContacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }
}