package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getContactHelper().getContactCount() == 0) {
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(
                    new ContactData()
                            .withFirstName("InitialName")
                            .withLastName("InitialLast")
                            .withHomeNr("7  777")
                            .withMobileNr("+7656565")
                            .withWorkNr("10000")
                            .withGroup("test55"), true);
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
        assertThat(contact.getHomeNr(), equalTo(cleaned(contactFromEditForm.getHomeNr())));
        assertThat(contact.getMobileNr(), equalTo(cleaned(contactFromEditForm.getMobileNr())));
        assertThat(contact.getWorkNr(), equalTo(cleaned(contactFromEditForm.getWorkNr())));

    }

    //преобоазовать телефон, убрать ненужные символы
    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}