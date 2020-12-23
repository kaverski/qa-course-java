package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        //количество групп до добавления
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test777", null, null));
        //количество групп после добавления
        int after = app.getGroupHelper().getGroupCount();
        //увелич на 1
        Assert.assertEquals(after, before+1);
    }
}