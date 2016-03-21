package ru.stqa.course.addressbook.model;

public class ContactData {
  private int id;
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String phone;
  private final String email;
  private final String secondaryAddress;
  private final String home;
  private final String notes;
  private String group;


  public ContactData(int id, String firstName, String lastName, String address, String phone, String email, String secondaryAddress, String home, String notes, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.secondaryAddress = secondaryAddress;
    this.home = home;
    this.notes = notes;
    this.group = group;
  }
  public ContactData(String firstName, String lastName, String address, String phone, String email, String secondaryAddress, String home, String notes, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.secondaryAddress = secondaryAddress;
    this.home = home;
    this.notes = notes;
    this.group = group;
  }

  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result =  result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
