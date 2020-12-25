package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup())  {
            app.getGroupHelper().createGroup(new GroupData("test555", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();

        //выбор группы для удаления по индексу
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()-1);

        //оригинальный список с удаленным элементом
        before.remove(before.size() - 1);

        //проверить каждый элемент оригинального списка с каждым элементом нового списка
        Assert.assertEquals(after, before);
    }
}