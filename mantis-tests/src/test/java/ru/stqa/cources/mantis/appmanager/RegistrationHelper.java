package ru.stqa.cources.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by leonto on 4/22/2016.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;
    

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver(); //этот метод будет инициализировать драйвер в момент первого обращения
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
