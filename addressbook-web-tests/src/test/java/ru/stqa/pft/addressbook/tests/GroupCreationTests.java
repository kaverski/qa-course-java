package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();

        //количество групп до добавления
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData groupToAdd = new GroupData("test555", null, null);
        app.getGroupHelper().createGroup(groupToAdd);

        //список групп после добавления
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //в проверке увелич на 1
        Assert.assertEquals(after.size(), before.size() + 1);

        //получить макс ID из нового списка группа
        int max = after.stream()
                .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
                .get().getId();

        //присвоить этой ID новой группе
        groupToAdd.setId(max);
        before.add(groupToAdd);

        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }
}