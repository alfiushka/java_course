package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("FirstNameEdit", "LastNameEdit", "SPb asdfghjk 12", "123456789", "qwerty@as.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();

    }
}
