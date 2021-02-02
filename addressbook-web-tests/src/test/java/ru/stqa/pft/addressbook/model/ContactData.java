package ru.stqa.pft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "home")
    @Type(type = "text")
    private String homeNr;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobileNr;

    @Column(name = "work")
    @Type(type = "text")
    private String workNr;

    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Transient
    private String allPhones;
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeNr() {
        return homeNr;
    }

    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withHomeNr(String homeNr) {
        this.homeNr = homeNr;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobileNr() {
        return mobileNr;
    }

    public ContactData withMobileNr(String mobileNr) {
        this.mobileNr = mobileNr;
        return this;
    }

    public String getWorkNr() {
        return workNr;
    }

    public ContactData withWorkNr(String workNr) {
        this.workNr = workNr;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData setGroups(Set<GroupData> groups) {
        this.groups = groups;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homeNr='" + homeNr + '\'' +
                ", mobileNr='" + mobileNr + '\'' +
                ", workNr='" + workNr + '\'' +
                '}';
    }

    public ContactData willAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
