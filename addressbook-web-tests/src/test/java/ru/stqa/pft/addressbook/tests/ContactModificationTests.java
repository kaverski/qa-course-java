package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(
                    new ContactData()
                            .withFirstName("InitialName")
                            .withLastName("InitialLast")
                            //.withGroup("test55")
                            .withPhoto(new File("src/test/resources/download.jpg")), true);
            app.getNavigationHelper().goToHomePage();
        }
    }

    @Test
    public void testContactModificationViaEditPage() {
        //actual element list BEFORE edit contact
        Contacts before = app.getDbHelper().getContacts();

        //contact to modify
        ContactData contactToModify = before.iterator().next();

        app.getContactHelper().goToContactEditPageById(contactToModify.getId());

        //создать новый контакт с ИД модифиц контакта
        ContactData contact = new ContactData().withId(contactToModify.getId())
                .withFirstName("First")
                .withMiddleName("Middle")
                .withLastName("Last")
                .withHomeNr("8888")
                .withEmail("test111@test888.test555")
                .withPhoto(new File("src/test/resources/download.jpg"));

        app.getContactHelper().editContact(contact, false);
        app.getNavigationHelper().goToHomePage();

        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        //actual element list AFTER edit contact
        Contacts after = app.getDbHelper().getContacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }

    @Test
    public void testContactModificationViaDetailsPage() {
        Contacts before = app.getDbHelper().getContacts();
        ContactData contactToModify = before.iterator().next();
        app.getNavigationHelper().goToContactDetailsPageById(contactToModify.getId());
        app.getNavigationHelper().goToModifyPage();

        ContactData contact = new ContactData().withId(contactToModify.getId())
                .withFirstName("UPDFirst")
                .withMiddleName("UpdatedMiddle")
                .withLastName("BBBUpdatedLast")
                .withHomeNr("8888")
                .withEmail("test111@test888.test555")
                .withPhoto(new File("src/test/resources/download.jpg"));

        app.getContactHelper().editContact(contact, false);
        app.getNavigationHelper().goToHomePage();
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));

        Contacts after = app.getDbHelper().getContacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }
}