package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String homeNr;
    private final String email;

    public ContactData(String firstName, String middleName, String lastName, String homeNr, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.homeNr = homeNr;
        this.email = email;
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

    public String getEmail() {
        return email;
    }
}
