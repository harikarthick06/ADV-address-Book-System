package com.bridgelabz.addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVService {
    private static final Path CSV_FILE_PATH = Path.of("src/main/resources/addressbook.csv");

    public static void writeContacts(List<Contact> contacts) {
        try {
            Files.createDirectories(CSV_FILE_PATH.getParent());
            try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH.toFile()))) {
                String[] header = { "First Name", "Last Name", "Address", "City", "State", "Zip", "Phone Number", "Email" };
                writer.writeNext(header);
                for (Contact contact : contacts) {
                    String[] data = {
                            contact.getFirstName(),
                            contact.getLastName(),
                            contact.getAddress(),
                            contact.getCity(),
                            contact.getState(),
                            contact.getZip(),
                            contact.getPhoneNumber(),
                            contact.getEmail()
                    };
                    writer.writeNext(data);
                }
                System.out.println("Contacts written to CSV file successfully.");
            }
        } catch (Exception exception) {
            System.out.println("Error while writing CSV file: " + exception.getMessage());
        }
    }

    public static List<Contact> readContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            if (!Files.exists(CSV_FILE_PATH)) {
                System.out.println("CSV file not found.");
                return contacts;
            }
            try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH.toFile()))) {
                List<String[]> records = reader.readAll();
                for (int i = 1; i < records.size(); i++) {
                    String[] data = records.get(i);
                    Contact contact = new Contact(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            data[6],
                            data[7]
                    );
                    contacts.add(contact);
                }
                System.out.println("Contacts read from CSV file successfully.");
            }
        } catch (Exception exception) {
            System.out.println("Error while reading CSV file: " + exception.getMessage());
        }
        return contacts;
    }
}
