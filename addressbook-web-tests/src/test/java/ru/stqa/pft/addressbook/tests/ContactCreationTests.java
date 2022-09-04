package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().fillContactForm(new ContactData("FirstName", "LastName", "SPb asdfghjk 12", "123456789", "qwerty@as.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
