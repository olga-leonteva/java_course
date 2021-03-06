package ru.stqa.course.addressbook.appmanager;

import com.sun.xml.internal.bind.v2.TODO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by leonto on 3/4/2016.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address2"), contactData.getSecondaryAddress());
    type(By.name("phone2"), contactData.getHome());
    type(By.name("notes"), contactData.getNotes());
    atach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
     }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public void closePopupDeletion(){
    wd.switchTo().alert().accept();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void initContactModification(int id) {
 //   wd.findElement(By.xpath(".//a[@href='edit.php?id=" + id + "']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
   // click(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));
  }
  private void initContactDetails(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void submitContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    contactCache = null;
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    submitContactDeletion();
    contactCache = null;
    closePopupDeletion();
  }

  public void addContactToGroup(ContactData contact) {
    selectContactById(contact.getId());
    String[] groupName = wd.findElement(By.name("to_group")).getText().split("\n");
    wd.findElement(By.cssSelector("input[name='add']")).click();

//    List<WebElement> elements = wd.findElements(By.name("to_group"));
//    for (WebElement element : elements){
//      String groupName = element.getText();
//    }
    wd.findElement(By.cssSelector("a[href='./?group=" + groupName[0] + "']")).click();
  }

 // TODO
  public void filterByGroup() {
    wd.findElement(By.name("group")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if ( contactCache != null)
     {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.tagName("tr"));
    for (int i = 1; i < elements.size(); i++) {
      String firstName = elements.get(i).findElements(By.tagName("td")).get(2).getText();
      String lastName = elements.get(i).findElements(By.tagName("td")).get(1).getText();
      String allPhones = elements.get(i).findElements(By.tagName("td")).get(5).getText();
      String allEmails = elements.get(i).findElements(By.tagName("td")).get(4).getText();
      String address = elements.get(i).findElements(By.tagName("td")).get(3).getText();
      int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
//    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname);
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactDetails(contact.getId());
    String [] info = wd.findElement(By.id("content")).getText().split("\n");
    String fistname = info[0].split(" ")[0];
    String lastname = info[0].split(" ")[1];
    String address = info[1];
    String homePhone = info[3].replaceAll("H:","").replaceAll("\\s", "").replaceAll("[-()]","");
    String mobilePhone = info[4].replaceAll("M:","").replaceAll("\\s", "").replaceAll("[-()]","");
    String workPhone = info[5].replaceAll("W:","").replaceAll("\\s", "").replaceAll("[-()]","");
    String email = info[7].split(" ")[0];
    String email2 = info[8].split(" ")[0];
    String email3 = info[9].split(" ")[0];
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(fistname).withLastName(lastname)
            .withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }



// .withHomePhone(homePhone).withWorkPhone(workPhone)

}
