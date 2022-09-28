package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().goToContactPage();
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("LastName")
            .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super ContactData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
