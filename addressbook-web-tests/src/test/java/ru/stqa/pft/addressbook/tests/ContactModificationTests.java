package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().goToContactPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName")
                    .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("FirstNameEdit")
                .withLastName("LastNameEdit").withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru");
        app.contact().modify(contact);
        app.goTo().returnToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
//        Comparator<? super ContactData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
