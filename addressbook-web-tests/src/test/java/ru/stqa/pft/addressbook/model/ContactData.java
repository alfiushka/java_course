package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {
    @XStreamOmitField()
    @Id
    @Column(name= "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name= "firstname")
    private String firstName;
    @Expose
    @Column(name= "lastname")
    private String lastName;
    @Expose
    @Column(name= "address")
    @Type(type = "text")
    private String address;
    @Transient
    private String phone;
    @Transient
    private String allEmails;
    @Transient
    private String allPhones;
    @Expose
    @Column(name= "home")
    @Type(type = "text")
    private String home;
    @Expose
    @Column(name= "mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column(name= "work")
    @Type(type = "text")
    private String work;
    @Transient
    private String email;
    @Expose
    @Column(name= "email")
    @Type(type = "text")
    private String email1;
    @Transient
    private String email2;
    @Transient
    private String email3;
//    @Transient
//    private String group;
    @Transient
    @Column(name= "photo")
    private String photo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
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

    public Groups getGroups() {
        return new Groups(groups);
    }
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
