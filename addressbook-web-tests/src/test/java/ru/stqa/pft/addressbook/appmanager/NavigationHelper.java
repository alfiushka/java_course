package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
//    private WebDriver wd;
    public NavigationHelper(WebDriver wd) {
       super(wd);
    }
    public void returnToHomePage() {
    wd.get("http://localhost/addressbook/");
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
//        wd.findElement(By.linkText("groups")).click();
    }

    public void goToContactPage() {
      wd.findElement(By.linkText("add new")).click();
    }
}
