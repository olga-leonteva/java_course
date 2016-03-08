package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getNavigationHelper().gotoNewContactPage();
        app.getContactHelper().createContact(new ContactData("Olga", "Leonteva", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3", "test1"),true);
    }
}
