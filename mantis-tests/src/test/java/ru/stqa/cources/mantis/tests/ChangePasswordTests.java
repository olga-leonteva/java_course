package ru.stqa.cources.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.cources.mantis.model.MailMessage;
import ru.stqa.cources.mantis.model.UserData;
import ru.stqa.cources.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by leonto on 4/26/2016.
 */
public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public  void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String password = String.format("password%s", now);
        Users users = app.db().users();
        for (UserData user : users)
            if (user.getId() > 1) {

                app.changePassword().loginAsAdmin("administrator", "root");
                app.changePassword().goToManageUsers();
                app.changePassword().getUserInTable(user.getUsername());
                String email = app.changePassword().getUserEmail();
                app.changePassword().pressResetPasswordBottom();

                List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
                String resetPasswordLink = findResetPasswordLink(mailMessages, email);

                app.registration().finish(resetPasswordLink, password);
                Assert.assertTrue(app.newSession().login(user.getUsername(), password));
                break;
            }
    }

    private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }


}



