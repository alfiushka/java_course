package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{
  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstName("firstname 1").withLastName("lastname 1")
            .withAddress("address 1").withHomePhone("homePhone 1").withMobilePhone("mobilePhone 1").withEmail1("email1 1")});
    list.add(new Object[] {new ContactData().withFirstName("firstname 2").withLastName("lastname 2")
            .withAddress("address 2").withHomePhone("homePhone 2").withMobilePhone("mobilePhone 2").withEmail1("email1 2")});
    list.add(new Object[] {new ContactData().withFirstName("firstname 3").withLastName("lastname 3")
            .withAddress("address 3").withHomePhone("homePhone 3").withMobilePhone("mobilePhone 3").withEmail1("email1 3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().goToContactPage();
//    File photo = new File("src/test/resources/photo.png");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/photo.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }
}
