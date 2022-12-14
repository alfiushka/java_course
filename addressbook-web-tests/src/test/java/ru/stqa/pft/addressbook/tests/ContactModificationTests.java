package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToContactPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName")
                    .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru"));
            app.goTo().homePage();
        }

//        app.goTo().homePage();
//        if (app.contact().all().size() == 0) {
//            app.goTo().goToContactPage();
//            app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName")
//                    .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru"));
//            app.goTo().homePage();
//        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("FirstNameEdit")
                .withLastName("LastNameEdit").withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
