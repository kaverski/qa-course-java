package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream()
                .map(g -> new Object [] {g}) //каждый объект завернуть в массив, состоящий из одного объекта
                .collect(toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream()
                .map(g -> new Object [] {g}) //каждый объект завернуть в массив, состоящий из одного объекта
                .collect(toList()).iterator();
    }

    @Test(dataProvider = "validGroupsFromJSON")
    public void testGroupCreation(GroupData groupToAdd) throws Exception {
        app.getNavigationHelper().goToGroupPage();
        Groups before = app.getGroupHelper().all();
        //группа полученая из data provider
        app.getGroupHelper().createGroup(groupToAdd);

        //предварительная проверка размера списка после добавления группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() + 1));

        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(
                before.withAdded(groupToAdd.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }
}