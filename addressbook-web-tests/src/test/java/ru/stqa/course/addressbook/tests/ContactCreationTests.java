package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
            //    .withId(before.get(before.size() + 1).getId())
                .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
                .withPhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
                .withHome("test2").withNotes("test3").withGroup("test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)  -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
