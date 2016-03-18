package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Leonteva", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.closePopupDeletion();
    app.getNavigationHelper().gotoHomePage();
  }
}
