package ru.stqa.course.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withFirstName("")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test2"));
        }
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/resources/stru.jpg");
//        ContactData contact = new ContactData()
//            //    .withId(before.get(before.size() + 1).getId())
//                .withFirstName("Name1").withLastName("LastName1")
//                .withHomePhone("111").withEmail("olga.leonteva@test.ru")
//                .withPhoto(photo);
        app.contact().create(contact);

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
