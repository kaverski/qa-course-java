package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(
                    new ContactData()
                            .withFirstName("InitialName")
                            .withLastName("InitialLast")
                            .withAddress("Brivibas iela 100, Riga, LV-1111")
                            .withHomeNr("7  777")
                            .withMobileNr("+7656565")
                            .withWorkNr("10000")
                            //.withGroup("test55")
                            .withPhoto(new File("src/test/resources/download.jpg"))
                    , true);
            app.getNavigationHelper().goToHomePage();
        }
    }

    @Test
    public void testContactAddress() {
        Contacts contacts = app.getContactHelper().getContacts(); //initial contacts from DB
        ContactData contact = contacts.iterator().next();
        ContactData contactFromEditForm = app.getContactHelper().getInfoFromEditForm(contact);
        app.getNavigationHelper().clickHomePage();
        assertThat(contact.getAddress(), equalTo(contactFromEditForm.getAddress()));
    }
}