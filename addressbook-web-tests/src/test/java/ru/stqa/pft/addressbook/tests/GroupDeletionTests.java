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
        Groups after = app.getGroupHelper().all();
        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}