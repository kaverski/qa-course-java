package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getDbHelper().getGroups().size() == 0) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("test777"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.getDbHelper().getGroups(); //initial group list from DB
        GroupData deletedGroup = before.iterator().next();

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().deleteGroup(deletedGroup);

        //предварительная проверка размера списка после удаления группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() - 1));

        Groups after = app.getDbHelper().getGroups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}