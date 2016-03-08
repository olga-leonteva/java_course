package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Olga", "Leonteva", "testAddress", "123456", "olga.leonteva@test.ru", "test1", "test2", "test3"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }
}