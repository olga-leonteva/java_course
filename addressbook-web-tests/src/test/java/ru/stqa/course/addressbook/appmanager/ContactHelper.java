package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.course.addressbook.model.ContactData;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getSecondaryAddress());
    type(By.name("phone2"), contactData.getHome());
    type(By.name("notes"), contactData.getNotes());

  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void submitContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
}
