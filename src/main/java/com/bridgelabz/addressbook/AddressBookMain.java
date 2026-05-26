package com.bridgelabz.addressbook;

import java.util.Scanner;

public class AddressBookMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display Contacts");
            System.out.println("5. Sort Contacts by Name");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    Contact contact = createContactFromUser();
                    addressBook.addContact(contact);
                    break;
                case 2:
                    System.out.print("Enter first name: ");
                    String editFirstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String editLastName = scanner.nextLine();
                    addressBook.editContact(editFirstName, editLastName, scanner);
                    break;
                case 3:
                    System.out.print("Enter first name to delete: ");
                    String deleteFirstName = scanner.nextLine();
                    System.out.print("Enter last name to delete: ");
                    String deleteLastName = scanner.nextLine();
                    addressBook.deleteContact(deleteFirstName, deleteLastName);
                    break;
                case 4:
                    addressBook.displayContacts();
                    break;
                case 5:
                    java.util.List<Contact> sorted = addressBook.sortByName();
                    if (sorted.isEmpty()) {
                        System.out.println("Address Book is empty.");
                    } else {
                        sorted.forEach(System.out::println);
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Address Book.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Contact createContactFromUser() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter zip: ");
        String zip = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }
}
