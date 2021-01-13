package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            //deserialize to collection
            List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream()
                    .map(c -> new Object[]{c}) //каждый объект завернуть в массив, состоящий из одного объекта
                    .collect(toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJSON")
    public void testContactCreation(ContactData contact) throws Exception {
        //actual element list BEFORE adding new contact
        Contacts before = app.getContactHelper().getContacts();

        app.getNavigationHelper().goToAddContactPage();
        //File photo = new File("src/test/resources/download.jpg");//относит путь

        app.getContactHelper().createContact(contact, true); //файл по абсолютному пути
        app.getNavigationHelper().goToHomePage();

        //предварительная проверка размера списка
        assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));

        //actual element list AFTER adding new contact
        Contacts after = app.getContactHelper().getContacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}