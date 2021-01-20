package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        //прямое обращение в DB в обход пользоват интерфейса
        if (app.getDbHelper().getGroups().size() == 0) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("test777"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.getDbHelper().getGroups(); //извлекаем изначальные группы из DB
        GroupData groupToModify = before.iterator().next(); // randomly selected group to modify

        app.getNavigationHelper().goToGroupPage();

        //создать новые групп данные
        GroupData group = new GroupData().withId(groupToModify.getId()) //получить ИД модифиц группы
                .withName("test55")
                .withHeader("test222")
                .withFooter("test333");

        app.getGroupHelper().modifyGroup(group); //main action in test
        //предварительная проверка размера списка после модиф группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));

        Groups after = app.getDbHelper().getGroups(); //извлекаем новые группы из DB
        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));

        verifyGroupListInUI();
    }
}