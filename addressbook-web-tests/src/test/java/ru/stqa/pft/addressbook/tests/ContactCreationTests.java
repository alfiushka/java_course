package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[] {ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());  // List<ContactData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
//    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().goToContactPage();
//    File photo = new File("src/test/resources/photo.png");
    app.contact().create(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.db().contacts();
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
