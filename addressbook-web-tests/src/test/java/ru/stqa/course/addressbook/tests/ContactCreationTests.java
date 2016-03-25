package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


    @Test(enabled = false)
    public void testContactCreation() {

        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData("test1", null, null));
        }
        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().gotoNewContactPage();
        ContactData contact = new ContactData("Name1", "LastName1", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)  -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
