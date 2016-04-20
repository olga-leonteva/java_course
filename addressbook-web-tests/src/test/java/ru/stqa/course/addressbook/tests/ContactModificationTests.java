package ru.stqa.course.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      //app.goTo().homePage();
      app.goTo().contactPage();
      File photo = new File("src/test/resources/stru.jpg");
      app.contact().create(new ContactData()
              .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("olga.leonteva@test.ru")
              .withEmail2("olga.leonteva@test.ru").withEmail3("olga.leonteva@test.ru")
              .withPhoto(photo));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    File photo = new File("src/test/resources/stru.jpg");
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("NameEdit").withLastName("LastNameEdit").withAddress("EditAddress")
            .withHomePhone("111-1").withMobilePhone("222-2").withWorkPhone("333-3")
            .withEmail("olga.leonteva1@test.ru") .withEmail2("olga.leonteva2@test.ru").withEmail3("olga.leonteva3@test.ru")
            .withPhoto(photo);
   //app.goTo().homePage();
    app.contact().modify(contact);
    app.contact().returnToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }




}
