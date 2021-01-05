package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().all().size() == 0) {
            app.getGroupHelper().createGroup(new GroupData().withName("test777"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getGroupHelper().all();
        GroupData groupToModify = before.iterator().next(); // randomly selected group to modify
        //создать новые групп данные
        GroupData group = new GroupData().withId(groupToModify.getId()) //получить ИД модифиц группы
                .withName("test55")
                .withHeader("test222")
                .withFooter("test333");

        app.getGroupHelper().modifyGroup(group);
        //предварительная проверка размера списка после модиф группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));

        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));
    }
}