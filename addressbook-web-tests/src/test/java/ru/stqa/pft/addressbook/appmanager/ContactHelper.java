package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"),contactData.getEmail());
    }
    public void selectContactDeletion(int index) {
        wd.findElements(By.xpath("//td/input")).get(index).click();
//        click(By.xpath("//td/input"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitAlertDeletion() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//td/input"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("selected[]"));
        for (WebElement element : elements) {
            String fullName = element.getAttribute("title");
            StringTokenizer st = new StringTokenizer(fullName, " \t\n\r,.()");
            String select = st.nextToken();
            String name= st.nextToken();
            String lastName = st.nextToken();
            int id = Integer.parseInt(element.getAttribute("id"));
            ContactData contact = new ContactData(id, name, lastName, null, null, null) ;
            contacts.add(contact);
        }
        return contacts;
    }
}
