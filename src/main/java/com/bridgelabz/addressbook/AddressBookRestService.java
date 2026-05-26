package com.bridgelabz.addressbook;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class AddressBookRestService {
    private static final String BASE_URL = "http://localhost:3000";
    private static final String CONTACT_ENDPOINT = "/contacts";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static List<Contact> getContacts() {
        try {
            Response response = RestAssured
                    .given()
                    .when()
                    .get(CONTACT_ENDPOINT);
            if (response.statusCode() == 200) {
                Contact[] contacts = response.as(Contact[].class);
                System.out.println("Contacts fetched from JSON Server.");
                return Arrays.asList(contacts);
            }
        } catch (Exception e) {
            System.out.println("Could not connect to JSON Server: " + e.getMessage());
        }
        return java.util.Collections.emptyList();
    }

    public static void addContact(Contact contact) {
        try {
            Response response = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .body(contact)
                    .when()
                    .post(CONTACT_ENDPOINT);
            if (response.statusCode() == 201) {
                System.out.println("Contact added to JSON Server successfully.");
            } else {
                System.out.println("Failed to add contact. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Could not connect to JSON Server: " + e.getMessage());
        }
    }
}
