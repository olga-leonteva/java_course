package ru.stqa.course.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String phone;
  private final String email;
  private final String secondaryAddress;
  private final String home;
  private final String notes;

  public ContactData(String firstName, String lastName, String address, String phone, String email, String secondaryAddress, String home, String notes) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.secondaryAddress = secondaryAddress;
    this.home = home;
    this.notes = notes;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getSecondaryAddress() {
    return secondaryAddress;
  }

  public String getHome() {
    return home;
  }

  public String getNotes() {
    return notes;
  }
}
