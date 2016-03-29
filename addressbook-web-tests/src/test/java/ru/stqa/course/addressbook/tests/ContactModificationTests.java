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
public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
              .withPhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
              .withHome("test2").withNotes("test3").withGroup("test1"));
    }
  }

  @Test

  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withId(before.get(before.size() - 1).getId()).withFirstName("Name2").withLastName("LastName1").withAddress("testAddress")
            .withPhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
            .withHome("test2").withNotes("test3");
    app.contact().modify(before.size() + 1, contact);
    app.contact().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2)  -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
