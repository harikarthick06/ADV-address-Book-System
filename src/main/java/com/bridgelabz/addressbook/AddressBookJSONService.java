package com.bridgelabz.addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AddressBookJSONService {
    private static final Path JSON_FILE_PATH = Path.of("src/main/resources/addressbook.json");

    public static void writeContacts(List<Contact> contacts) {
        try {
            Files.createDirectories(JSON_FILE_PATH.getParent());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (Writer writer = Files.newBufferedWriter(JSON_FILE_PATH)) {
                gson.toJson(contacts, writer);
            }
            System.out.println("Contacts written to JSON file successfully.");
        } catch (Exception exception) {
            System.out.println("Error while writing JSON file: " + exception.getMessage());
        }
    }

    public static List<Contact> readContacts() {
        try {
            if (!Files.exists(JSON_FILE_PATH)) {
                System.out.println("JSON file not found.");
                return new ArrayList<>();
            }
            Gson gson = new Gson();
            try (Reader reader = Files.newBufferedReader(JSON_FILE_PATH)) {
                Type contactListType = new TypeToken<List<Contact>>() {}.getType();
                List<Contact> contacts = gson.fromJson(reader, contactListType);
                if (contacts == null) {
                    return new ArrayList<>();
                }
                System.out.println("Contacts read from JSON file successfully.");
                return contacts;
            }
        } catch (Exception exception) {
            System.out.println("Error while reading JSON file: " + exception.getMessage());
            return new ArrayList<>();
        }
    }
}
