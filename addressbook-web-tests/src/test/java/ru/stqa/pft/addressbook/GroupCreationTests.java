package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test333", "test444", "test555"));
        submitGroupCreation();
        returnToGroupPage();
    }
}