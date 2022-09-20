package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;


import java.time.Duration;

public class ApplicationManager {
    protected WebDriver wd;
    private SessionHelper sessionHelper;
    private  ContactHelper contactHelper ;
    private NavigationHelper navigationHelper ;
    protected GroupHelper groupHelper;
//    protected StringBuffer verificationErrors = new StringBuffer();
    private String baseUrl;
    private JavascriptExecutor js;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
//        String browser = BrowserType.CHROME;

        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else wd = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        js = (JavascriptExecutor) wd;
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }


//    public void logout() {
//        wd.findElement(By.linkText("Logout")).click();
//    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
