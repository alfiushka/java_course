package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "SPb asdfghjk 12", "123456789", "qwerty@as.ru"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContactDeletion();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().submitAlertDeletion();

    }
}
