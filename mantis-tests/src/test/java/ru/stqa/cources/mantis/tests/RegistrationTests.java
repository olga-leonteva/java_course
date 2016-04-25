package ru.stqa.cources.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.cources.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by leonto on 4/22/2016.
 */
public class RegistrationTests extends TestBase{

    @BeforeMethod
    public  void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.locoldomain", now);

        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 20000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        //находим среди писем то, кот отпр на нужный адрес. Используем фильтр
        // после фильтрации в потоке останутся те, что отправлены по нужному адресу. Берем первое
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
