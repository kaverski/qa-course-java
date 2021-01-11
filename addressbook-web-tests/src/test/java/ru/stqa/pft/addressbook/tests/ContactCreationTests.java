package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        //actual element list BEFORE adding new contact
        Contacts before = app.getContactHelper().getContacts();

        app.getNavigationHelper().goToAddContactPage();
        File photo = new File("src/test/resources/download.jpg");//относит путь
        ContactData contactToAdd = new ContactData().withFirstName("11First")
                .withMiddleName("Onemore2")
                .withLastName("rrZZZFirstLast")
                .withEmail("newtest123@newtest.newtest")
                .withGroup("test55")
                .withPhoto(photo);

        app.getContactHelper().createContact(contactToAdd, true); //файл по абсолютному пути
        app.getNavigationHelper().goToHomePage();

        //предварительная проверка размера списка
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        //actual element list AFTER adding new contact
        Contacts after = app.getContactHelper().getContacts();
        assertThat(after, equalTo(
                before.withAdded(contactToAdd.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}