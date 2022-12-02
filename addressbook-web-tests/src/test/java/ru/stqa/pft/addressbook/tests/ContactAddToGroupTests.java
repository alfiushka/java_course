package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToContactPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName")
                    .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru"));
            app.goTo().returnToHomePage1();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().returnToHomePage1();
        }
    }
    @Test
    public void testContactAddToGroup() {
        Contacts beforeContact = app.db().contacts();
        ContactData addedContact = beforeContact.iterator().next();
        Groups beforeGroup = app.db().groups();
        GroupData addedGroup =beforeGroup.iterator().next();
        if (!addedContact.getGroups().isEmpty() && addedContact.getGroups().contains(addedGroup)) {
            app.contact().deleteFromGroup(addedContact, addedGroup);
            app.goTo().returnToHomePage1();
        }

        app.contact().addedToGroup(addedContact, addedGroup);
        Contacts afterContact = app.db().contacts();
        Groups afterGroup = app.db().groups();
        Assert.assertEquals(afterGroup.size(), beforeGroup.size());
        Assert.assertEquals(afterContact.size(), beforeContact.size());
        assertThat(addedContact.getGroups().withAdded(addedGroup), equalTo(afterContact
                .stream().filter((c) -> c.getId() == addedContact.getId()).collect(Collectors.toList()).get(0).getGroups()));

//        verifyContactListInUI();
    }


    @Test
    public void testContactDeleteFromGroup() {
        Contacts beforeContact = app.db().contacts();
        ContactData deleteContact = beforeContact.iterator().next();
        Groups beforeGroup = app.db().groups();
        GroupData deleteGroup =beforeGroup.iterator().next();

        if (deleteContact.getGroups().isEmpty() || !deleteContact.getGroups().contains(deleteGroup)) {
            app.contact().addedToGroup(deleteContact, deleteGroup);
            app.goTo().returnToHomePage1();
        }

        app.contact().deleteFromGroup(deleteContact, deleteGroup);
        Contacts afterContact = app.db().contacts();
        Groups afterGroup = app.db().groups();
        Assert.assertEquals(afterGroup.size(), beforeGroup.size());
        Assert.assertEquals(afterContact.size(), beforeContact.size());
        assertThat(deleteContact.getGroups().without(deleteGroup), equalTo(afterContact
                .stream().filter((c) -> c.getId() == deleteContact.getId()).collect(Collectors.toList()).get(0).getGroups()));

//        verifyContactListInUI();
    }
}
