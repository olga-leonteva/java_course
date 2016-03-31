package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.Set;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
            //    .withId(before.get(before.size() + 1).getId())
                .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
                .withPhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
                .withHome("test2").withNotes("test3").withGroup("test1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
