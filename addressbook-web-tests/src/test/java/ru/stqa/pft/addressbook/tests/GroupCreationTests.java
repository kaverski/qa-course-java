package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData groupToAdd = new GroupData("11test000", null, null);
        app.getGroupHelper().createGroup(groupToAdd);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.add(groupToAdd);
        Comparator<GroupData> byId = Comparator.comparing(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        System.out.println("before: " + before);
        System.out.println("after:" + after);

        Assert.assertEquals(before, after);
    }
}