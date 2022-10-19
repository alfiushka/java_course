package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField()
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String address;
    private String phone;

   private String allEmails;
    private String allPhones;
    @Expose
    private String home;
    @Expose
    private String mobile;
    private String work;
    private String email;
    @Expose
    private String email1;
    private String email2;
    private String email3;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getEmail1() {return email1;}
    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }
    public String getEmail2() {return email2;}
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public String getEmail3() {return email3;}
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public String getHome() {return home;}

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }
    public String getMobile() {return mobile;}
    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public String getWork () {return  work;}
    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

//
//    public ContactData(String firstName, String lastName, String address, String phone, String email) {
//        this.id = Integer.MAX_VALUE;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//    }
//    public ContactData(int id, String firstName, String lastName, String address, String phone, String email) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
