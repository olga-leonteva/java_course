package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by leonto on 3/7/2016.
 */
public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.closePopupDeletion();
    app.getNavigationHelper().gotoHomePage();
  }
}
