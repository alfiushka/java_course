package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().goToContactPage();
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("LastName")
            .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
//    Comparator<? super ContactData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
