package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "group count")
    public int count;

    @Parameter(names = "-f", description = "target file")
    public String file; //файл, в который сохранять

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count); //generate data
        if (format.equals("csv")) {
            saveAsCSV(groups, new File(file)); //save these data into file as CSV
        } else if (format.equals("xml")) {
            saveAsXML(groups, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(groups, new File(file));
        }
        else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(groups); //object to serialize
        Writer writer = new FileWriter(file); //открыть файл на запись; файл, в который сохранять
        writer.write(json);
        writer.close();
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.alias("Group", GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file); //открыть файл на запись; файл, в который сохранять
        writer.write(xml);
        writer.close();
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file); //открыть файл на запись; файл, в который сохранять
        for (GroupData group : groups) {
            writer.write(String.format("%s; %s; %s\n",
                    group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(String.format("test %s", (i+10)))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}