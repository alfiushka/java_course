package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;
    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }
//    protected WebDriver wd;

    public void submitContactCreation() {
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
      wd.findElement(By.name("lastname")).click();
  //    driver.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).sendKeys(contactData.getPhone());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }
}
