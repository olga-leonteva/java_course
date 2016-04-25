package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

/**
 * Created by leonto on 4/21/2016.
 */
public class AddContactToGroupTests extends TestBase{

    @Test
    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact);

        app.contact().filterByGroup();

    }
}
