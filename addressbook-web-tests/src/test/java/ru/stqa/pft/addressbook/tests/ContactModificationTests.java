package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationViaEditPage() {
        app.getNavigationHelper().goToContactEditPage();
        app.getContactHelper().fillContactForm(new ContactData("NewFirst", "NewMiddle", "NewLast", "2222222", "newtest@newtest.newtest"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testContactModificationViaDetailsPage() {
        app.getNavigationHelper().goToContactDetailsPage();
        app.getNavigationHelper().goToModifyPage();
        app.getContactHelper().fillContactForm(new ContactData("ModifyOneFirst", "ModifySecondMiddle", "ModifyThirdLast", "3333333", "modify2@modify3.modify4"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
