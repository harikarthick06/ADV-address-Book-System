package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final List<Contact> contactList = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
    }

    public boolean addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact added successfully.");
        return true;
    }

    public void displayContacts() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        contactList.forEach(System.out::println);
    }
}
