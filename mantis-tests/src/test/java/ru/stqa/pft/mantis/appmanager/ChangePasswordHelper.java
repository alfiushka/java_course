package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper (ApplicationManager app) {
        super(app);
    }

    public void login(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), email);
        click(By.cssSelector("input[type='submit']"));

    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));

    }

    public void goToManageUsers(String login) throws InterruptedException {
        click(By.cssSelector("a[href*='manage_overview_page.php']"));
        click(By.cssSelector("a[href*='manage_user_page.php']"));
        click(By.xpath("//a[.='" + login + "']"));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
