package ru.stqa.cources.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.cources.mantis.model.UserData;

/**
 * Created by leonto on 4/26/2016.
 */
public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUsers(){
        wd.findElement(By.xpath(".//a[text()='Manage Users']")).click();
    }

    public void getUserInTable(String user){
        wd.findElement(By.xpath(".//a[text()='" + user + "']")).click();
    }

    public String getUserEmail(){
        String email = wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
        return email;
    }

    public void pressResetPasswordBottom(){
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void loginAsAdmin(String username, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}
