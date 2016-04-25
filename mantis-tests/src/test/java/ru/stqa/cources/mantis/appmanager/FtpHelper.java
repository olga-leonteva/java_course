package ru.stqa.cources.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by leonto on 4/22/2016.
 */
public class FtpHelper {
    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    // заменяем файл
    public void upload(File file, String target, String backup) throws IOException { // загружает новый файл и временно переименовывает старый
        // устанавливаем соединение с сервером
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        // удаляем предыдущую резервную копию
        ftp.deleteFile(backup);
        // переименовываем удаленный файл, делаем резервную копию
        ftp.rename(target, backup);
        // включается пассывный режим передачи данных
        ftp.enterLocalPassiveMode();
        // передается локальный файл
        ftp.storeFile(target, new FileInputStream(file)); // файл для чтения бинарных данных. Читаются из лок файла, передаются на удаленную машину и там сохраняются
        // соединение разрывается
        ftp.disconnect();
    }
    // востанавлимаем конфигурацию
    public void restore(String backup, String target) throws IOException { //метод восстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
