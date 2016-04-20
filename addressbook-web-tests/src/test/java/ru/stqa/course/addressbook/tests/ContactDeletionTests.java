package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      File photo = new File("src/test/resources/stru.jpg");
      app.contact().create(new ContactData()
              .withFirstName("Name1").withFirstName("LastName1").withAddress("testAddress")
              .withMobilePhone("123456").withEmail("olga.leonteva@test.ru").withSecondaryAddress("test1")
              .withNotes("test3").withPhoto(photo));
         //     .withNotes("test3").withGroup("test 1").withPhoto(photo));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(),equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();

  }


}
