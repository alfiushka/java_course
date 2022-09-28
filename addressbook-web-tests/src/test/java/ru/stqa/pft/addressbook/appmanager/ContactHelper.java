package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModification(int index) {
//        click(By.xpath("//img[@alt='Edit']"));
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactForm(contact);
        submitContactModification();
    }

    public void delete(int index) {
        selectContactDeletion(index);
        submitContactDeletion();
        submitAlertDeletion();
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//td/input"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name= element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
//            ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName);
            contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
        }
        return contacts;
    }
}
