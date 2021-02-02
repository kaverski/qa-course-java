package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContractEmailsTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(
                    new ContactData()
                            .withFirstName("InitialName")
                            .withLastName("InitialLast")
                            .withAddress("Brivibas iela 100, Riga, LV-1111")
                            .withEmail("aaa@bbb.cc")
                            .withEmail2("fff111@fff222.ff")
                            .withEmail3("ddd22@aa.cc")
                            .withHomeNr("7  777")
                            .withMobileNr("+7656565")
                            .withWorkNr("10000")
                            //.withGroup("test55")
                            .withPhoto(new File("src/test/resources/download.jpg")), true);
            app.getNavigationHelper().goToHomePage();
        }
    }

    @Test
    public void testContactEmails() {
        Contacts contacts = app.getContactHelper().getContacts();
        ContactData contact = contacts.iterator().next();
        ContactData contactFromEditForm = app.getContactHelper().getInfoFromEditForm(contact);
        app.getNavigationHelper().clickHomePage();
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}