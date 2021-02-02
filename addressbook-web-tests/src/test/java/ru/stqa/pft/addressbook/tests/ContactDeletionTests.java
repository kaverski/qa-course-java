package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletionFromEditPage() {
        Contacts before = app.getDbHelper().getContacts();
        ContactData contactToDelete = before.iterator().next();
        app.getContactHelper().goToContactEditPageById(contactToDelete.getId());
        app.getContactHelper().deleteContact();

        //предварительная проверка размера списка
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() - 1));

        Contacts after = app.getDbHelper().getContacts();

        assertThat(after, equalTo(before.without(contactToDelete)));
    }

    @Test
    public void testContactDeletionFromHomePage() {
        Contacts before = app.getDbHelper().getContacts();
        ContactData contactToDelete = before.iterator().next();

        app.getContactHelper().selectContactById(contactToDelete.getId());
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();

        //предварительная проверка размера списка
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() - 1));

        Contacts after = app.getDbHelper().getContacts();
        assertThat(after, equalTo(before.without(contactToDelete)));
    }
}