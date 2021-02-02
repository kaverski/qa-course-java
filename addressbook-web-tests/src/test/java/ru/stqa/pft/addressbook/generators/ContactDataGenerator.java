package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "contact count")
    public int count;

    @Parameter(names = "-f", description = "target file")
    public String file;

    @Parameter(names = "-d", description = "format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveAsJSON(contacts, new File(file));
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName("Myfirst" + (i+100))
            .withLastName("OneLast" + (i+100))
            .withMiddleName("OneMiddle" + (i+100))
            .withAddress("Newaddress str.100, 1234" + (i+100))
            .withEmail("bbb@bbb.bbb" + (i+100))
            //.withGroup("test55")
            .withMobileNr("987456321" + (i+100))
            .withPhoto(new File("src/test/resources/download.jpg")));
        }
        return contacts;
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contacts); //serialize object to json
        try (Writer writer = new FileWriter(file)) {
            writer.write(json); //write to file
        }
    }
}