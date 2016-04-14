package ru.stqa.course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonto on 4/14/2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String formate;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {           // для вывода информации о доступных опциях, если возникло исключение
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        saveAsCsv(contacts, new File(file));
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstName(String.format("Firstname %s", i))
                    .withLastName(String.format("Lastname %s", i))
                    .withHomePhone(String.format("111 %s", i))
                    .withMobilePhone(String.format("222 %s", i))
                    .withWorkPhone(String.format("333 %s", i))
                    .withEmail(String.format("email %s", i))
                    .withEmail2(String.format("email2 %s", i))
                    .withEmail3(String.format("email3 %s", i)));
                    }
        return contacts;
    }

}
