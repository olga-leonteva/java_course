package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()){
      app.group().create(new GroupData("test1", null, null));
    }
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "LastName1", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3", "test1"));
    }
  }

  @Test(enabled = false)
  public void testContactDeletion() {

    app.goTo().groupPage();
    if (! app.group().isThereAGroup()){
      app.group().create(new GroupData("test1", null, null));
    }
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "LastName1", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().submitContactDeletion();
    app.closePopupDeletion();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Comparator<? super ContactData> byId = (c1, c2)  -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
