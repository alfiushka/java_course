package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class NavigationHelper extends HelperBase {
    private final Properties properties;
//    private WebDriver wd;
    public NavigationHelper(WebDriver wd) {
       super(wd);
        properties = new Properties();
    }
    public void homePage() {
//    wd.get("http://localhost/addressbook/");
    wd.get(properties.getProperty("web.homePage"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void returnToHomePage1() {
        click(By.linkText("home"));
    }

    public void groupPage() {
        click(By.linkText("groups"));
//        wd.findElement(By.linkText("groups")).click();
    }

    public void goToContactPage() {
      wd.findElement(By.linkText("add new")).click();
    }
}
