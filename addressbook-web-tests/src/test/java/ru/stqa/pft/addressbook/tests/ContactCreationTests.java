package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().goToContactPage();
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("LastName")
            .withAddress("SPb asdfghjk 12").withPhone("123456789").withEmail("qwerty@as.ru");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
