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

    public java.util.Optional<Contact> findContact(String firstName, String lastName) {
        return contactList.stream()
                .filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName)
                        && contact.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

    public boolean editContact(String firstName, String lastName, java.util.Scanner scanner) {
        java.util.Optional<Contact> contactOptional = findContact(firstName, lastName);
        if (contactOptional.isEmpty()) {
            System.out.println("Contact not found.");
            return false;
        }

        Contact contact = contactOptional.get();
        System.out.print("Enter new address: ");
        contact.setAddress(scanner.nextLine());
        System.out.print("Enter new city: ");
        contact.setCity(scanner.nextLine());
        System.out.print("Enter new state: ");
        contact.setState(scanner.nextLine());
        System.out.print("Enter new zip: ");
        contact.setZip(scanner.nextLine());
        System.out.print("Enter new phone number: ");
        contact.setPhoneNumber(scanner.nextLine());
        System.out.print("Enter new email: ");
        contact.setEmail(scanner.nextLine());

        System.out.println("Contact updated successfully.");
        return true;
    }
}
