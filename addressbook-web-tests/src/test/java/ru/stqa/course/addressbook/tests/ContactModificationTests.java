package ru.stqa.course.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("olga.leonteva@test.ru")
              .withSecondaryAddress("test1").withNotes("test3"));
    }
  }

  @Test

  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Name2").withLastName("LastName1").withAddress("testAddress")
            .withMobilePhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test2")
            .withNotes("test3");
    app.contact().modify(contact);
    app.contact().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }


}
