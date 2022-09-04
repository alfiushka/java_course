package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;
    public NavigationHelper(WebDriver wd) {
        this.wd= wd;
    }
    public void returnToHomePage() {
    wd.get("http://localhost/addressbook/");
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToContactPage() {
      wd.findElement(By.linkText("add new")).click();
    }
}
