package com.bridgelabz.addressbook;

import java.util.List;

public class AddressBookThreadService {

    public static void writeJSONUsingThread(List<Contact> contacts) {
        Runnable task = () -> {
            System.out.println("\nJSON write operation started in thread: " + Thread.currentThread().getName());
            AddressBookJSONService.writeContacts(contacts);
            System.out.println("JSON write operation completed in thread: " + Thread.currentThread().getName());
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Main thread is free. IO operation is running separately.");
    }

    public static void writeCSVUsingThread(List<Contact> contacts) {
        Runnable task = () -> {
            System.out.println("\nCSV write operation started in thread: " + Thread.currentThread().getName());
            AddressBookCSVService.writeContacts(contacts);
            System.out.println("CSV write operation completed in thread: " + Thread.currentThread().getName());
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Main thread is free. CSV IO is running separately.");
    }
}
