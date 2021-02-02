package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getContacts().size() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(
                    new ContactData()
                            .withFirstName("InitialName")
                            .withLastName("InitialLast")
                            .withHomeNr("7  777")
                            .withMobileNr("+7656565")
                            .withWorkNr("10000")
                            //.withGroup("test55")
                            .withPhoto(new File("src/test/resources/download.jpg")), true);
            app.getNavigationHelper().goToHomePage();
        }
    }

    @Test
    public void testContactPhones() {
        Contacts contacts = app.getContactHelper().getContacts();
        ContactData contact = contacts.iterator().next(); //получить случайный контакт
        //получить данные этого случайного контакта с Edit contact page - открыть Edit page по ид этого контакта
        ContactData contactFromEditForm = app.getContactHelper().getInfoFromEditForm(contact);

        app.getNavigationHelper().clickHomePage();
        //сравнить контакт с home page с контактом с edit page
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactFromEditForm)));
    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomeNr(), contact.getMobileNr(), contact.getWorkNr()).stream()
                .filter(s -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    //преобразовать телефон, убрать ненужные символы
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}