package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().goToContactPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName").withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deleteContact);
        Assert.assertEquals(before, after);
    }
}
