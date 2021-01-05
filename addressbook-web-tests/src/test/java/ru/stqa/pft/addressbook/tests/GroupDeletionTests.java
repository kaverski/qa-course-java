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
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().all().size() == 0) {
            app.getGroupHelper().createGroup(new GroupData().withName("test777"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.getGroupHelper().all();
        GroupData deletedGroup = before.iterator().next();

        app.getGroupHelper().deleteGroup(deletedGroup);
        //предварительная проверка размера списка после удаления группы
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() - 1));

        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}