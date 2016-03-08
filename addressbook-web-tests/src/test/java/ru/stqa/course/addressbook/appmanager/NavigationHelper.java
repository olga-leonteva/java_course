package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.course.addressbook.tests.TestBase;

/**
 * Created by leonto on 3/4/2016.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoNewContactPage() {
    click(By.linkText("add new"));
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }
}
