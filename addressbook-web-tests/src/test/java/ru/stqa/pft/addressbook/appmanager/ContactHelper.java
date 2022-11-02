package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//a[@href= 'edit.php?id="+id+"']")).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
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
//        attach(By.name("photo"), contactData.getPhoto());

//        if (creaation) {
//            if (contactData.getGroups() != null) {
//                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups());
//            } else {
//                Assert.assertFalse(isElementPresent(By.name("new_group")));
//            }
//        }


    }

    public void selectContactDeletionById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitAlertDeletion() {
        wd.switchTo().alert().accept();
    }

    public void selectGroupForAdded(int id) {
        List<WebElement> elements = wd.findElements(By.name("to_group"));
        for (WebElement element : elements) {
            element.findElement(By.cssSelector("option[value='"+ id + "']")).click();
        }
    }

    public void selectGroupForDeletion(int id) {
//        List<WebElement> elements = wd.findElements(By.name("group"));
        List<WebElement> elements = wd.findElements(By.xpath("//select[@name='group']"));
        for (WebElement element : elements) {
            element.findElement(By.cssSelector("option[value='"+ id + "']")).click();
        }
    }

    public void submitContactAddToGroup() {
        click(By.xpath("//input[@value='Add to']"));
    }

    public void submitContactDeletionFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
        contactsCache =null;
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactsCache =null;
    }

    public void addedToGroup(ContactData addedContact, GroupData addedGroup) {
        selectContactDeletionById(addedContact.getId());
        selectGroupForAdded(addedGroup.getId());
        submitContactAddToGroup();
        contactsCache =null;
    }

    public void deleteFromGroup(ContactData deleteContact, GroupData deleteGroup) {
        selectGroupForDeletion(deleteGroup.getId());
        selectContactDeletionById(deleteContact.getId());
        submitContactDeletionFromGroup();
        contactsCache =null;
    }

    public void delete(ContactData contact) {
        selectContactDeletionById(contact.getId());
        submitContactDeletion();
        submitAlertDeletion();
        contactsCache =null;
    }
    public boolean isThereContact() {
        return isElementPresent(By.xpath("//td/input"));
    }

    private Contacts contactsCache = null;

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name= element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
//            String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
//            String[] emails = element.findElement(By.xpath(".//td[5]")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            contactsCache.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactsCache);
    }

    public ContactData infoFormEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}
