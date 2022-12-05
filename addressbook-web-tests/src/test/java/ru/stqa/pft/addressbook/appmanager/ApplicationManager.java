package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    protected WebDriver wd;
    private SessionHelper sessionHelper;
    private  ContactHelper contactHelper ;
    private NavigationHelper navigationHelper ;
    protected GroupHelper groupHelper;
//    protected StringBuffer verificationErrors = new StringBuffer();
    private String baseUrl;
    private JavascriptExecutor js;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) throws IOException {

        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
//        String browser = BrowserType.CHROME;
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else wd = new FirefoxDriver();
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);

            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }


        baseUrl = "https://www.google.com/";
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        js = (JavascriptExecutor) wd;
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }


//    public void logout() {
//        wd.findElement(By.linkText("Logout")).click();
//    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public DbHelper db() { return dbHelper; }
}
