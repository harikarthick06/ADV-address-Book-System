package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final List<Contact> contactList = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
    }

    public boolean isDuplicate(Contact newContact) {
        return contactList.stream().anyMatch(contact -> contact.equals(newContact));
    }

    public boolean addContact(Contact contact) {
        if (isDuplicate(contact)) {
            System.out.println("Duplicate contact found. Contact not added.");
            return false;
        }
        contactList.add(contact);
        System.out.println("Contact added successfully.");
        return true;
    }

    public boolean addMultipleContacts(List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            return false;
        }
        for (Contact contact : contacts) {
            addContact(contact);
        }
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

    public boolean deleteContact(String firstName, String lastName) {
        java.util.Optional<Contact> contactOptional = findContact(firstName, lastName);
        if (contactOptional.isEmpty()) {
            System.out.println("Contact not found.");
            return false;
        }

        contactList.remove(contactOptional.get());
        System.out.println("Contact deleted successfully.");
        return true;
    }

    public List<Contact> sortByName() {
        return contactList.stream()
                .sorted(java.util.Comparator.comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public List<Contact> sortByCity() {
        return contactList.stream()
                .sorted(java.util.Comparator.comparing(Contact::getCity, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public List<Contact> sortByState() {
        return contactList.stream()
                .sorted(java.util.Comparator.comparing(Contact::getState, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public List<Contact> sortByZip() {
        return contactList.stream()
                .sorted(java.util.Comparator.comparing(Contact::getZip, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public java.util.Map<String, List<Contact>> getPersonsByCity() {
        return contactList.stream()
                .collect(java.util.stream.Collectors.groupingBy(Contact::getCity));
    }

    public java.util.Map<String, List<Contact>> getPersonsByState() {
        return contactList.stream()
                .collect(java.util.stream.Collectors.groupingBy(Contact::getState));
    }

    public List<Contact> searchByCity(String city) {
        return contactList.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .toList();
    }

    public List<Contact> searchByState(String state) {
        return contactList.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .toList();
    }
}
