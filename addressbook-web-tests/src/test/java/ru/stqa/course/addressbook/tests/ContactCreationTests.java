package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
            //    .withId(before.get(before.size() + 1).getId())
                .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
                .withPhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
                .withHome("test2").withNotes("test3").withGroup("test2");
        app.contact().create(contact);

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
