package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "SPb asdfghjk 12", "123456789", "qwerty@as.ru"));
            app.getNavigationHelper().goToHomePage();
        }
    }
    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() -1;
        ContactData contact = new ContactData(before.get(index).getId(),"FirstNameEdit", "LastNameEdit", "SPb asdfghjk 12", "123456789", "qwerty@as.ru");
        app.getContactHelper().modifyContact(index, contact);
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
