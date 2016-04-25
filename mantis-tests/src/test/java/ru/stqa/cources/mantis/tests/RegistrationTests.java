package ru.stqa.cources.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        String email = "user1@localhost.localdomain";
        app.registration().start("user1", email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        findConfirmationLink(mailMessages, email);

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        //находим среди писем то, кот отпр на нужный адрес. Используем фильтр
        // после фильтрации в потоке останутся те, что отправлены по нужному адресу. Берем первое
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
