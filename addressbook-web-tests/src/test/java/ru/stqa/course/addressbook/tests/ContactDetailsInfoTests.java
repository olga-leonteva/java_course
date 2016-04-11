package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by leonto on 4/8/2016.
 */
public class ContactDetailsInfoTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Name1").withLastName("LastName1").withAddress("testAddress")
                    .withHomePhone("11-1").withMobilePhone("2 22").withWorkPhone("3(3)3")
                    .withEmail("olga.leonteva@test.ru").withEmail2("email2@test.ru").withEmail3("email3@test.ru"));
        }
    }
    @Test  (enabled = false)
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contactInfoFromEditForm, equalTo(contactInfoFromDetailsForm));


    }

}
