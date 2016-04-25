package ru.stqa.cources.mantis.appmanager;


import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.cources.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by leonto on 4/25/2016.
 */
public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser(); // почтовый сервер
    }

    //добавляем метод для ожидания письма на почту
    public List<MailMessage> waitForMail(int count, long timeout) throws  MessagingException, IOException {
        long start = System.currentTimeMillis(); // запоминаем текущее время
        // в цикле проверяем, что новое текущее время не прывышает время ожидания
        while (System.currentTimeMillis() < start + timeout){
            // если почты пришло достаточно много, прекращаем ожидание
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList()); // преобр реальные объекты в модельные
                // берем список, превращаем в поток, ко всем эл-м применяем одну и туже ф-цию
                // получившиеся модельные объекты собираем в список
            }
            try {   // если почты пришло мало, ждем 1000мс и снова проверям if
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("No mail :( ");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent()); // берем реальный объект, список получателей и оставляем первый из них
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}
