package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        Groups before = app.getGroupHelper().all();

        GroupData groupToAdd = new GroupData().withName("11test000");
        app.getGroupHelper().createGroup(groupToAdd);

        //предварительная проверка размера списка после добавления группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() + 1));

        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(
                before.withAdded(groupToAdd.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }
}