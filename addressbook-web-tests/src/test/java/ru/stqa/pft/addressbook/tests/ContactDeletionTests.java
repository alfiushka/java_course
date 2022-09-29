package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.without(deleteContact)));
    }
}
