package com.przemek;


import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("500 384 723");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();
        while (!quit) {
            System.out.println("\nEnter action: (6 - to show available actions ");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("\nPhone shutting down...");
                    quit = true;
                    break;

                case 1:
                    mobilePhone.printContacts();
                    break;

                case 2:
                    addNewContact();
                    break;

                case 3:
                    updateContact();
                    break;

                case 4:
                    removeContact();
                    break;

                case 5:
                    queryContact();
                    break;

                case 6:
                    printActions();
                    break;
            }
        }
    }

    private static void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added, name: " + name + " ,phone number: " + phoneNumber);
        } else {
            System.out.println("Cannot add: " + name + " already on file.");
        }
    }

    private static void updateContact() {
        System.out.println("Enter contact name for update:");
        String oldName = scanner.nextLine();
        System.out.println("Enter new contact name:");
        String newName = scanner.nextLine();
        System.out.println("Enter contact phone number:");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        Contact oldContact = mobilePhone.queryContact(oldName);
        if (oldContact != null) {
            if (mobilePhone.updateContact(oldContact, newContact)) {
                System.out.println("Old contact " + oldName + " updated to " + newName);
            } else {
                System.out.println("Error while updating");
            }
        } else {
            System.out.println("Contact doesn't exist");
        }
    }

    private static void removeContact() {
        System.out.println("Enter contact name to remove:");
        String nameOfContactToRemove = scanner.nextLine();
        Contact contactToRemove = mobilePhone.queryContact(nameOfContactToRemove);
        if (contactToRemove != null) {
            if (mobilePhone.removeContact(contactToRemove)) {
                System.out.println("Contact " + nameOfContactToRemove + " successfully removed. ");
            } else {
                System.out.println("Error while removing.");
            }
        } else {
            System.out.println("Contact not found.");
        }

    }

    private static void queryContact() {
        System.out.println("Enter contact name to query:");
        String queryName = scanner.nextLine();
        Contact queryContact = mobilePhone.queryContact(queryName);
        if (queryContact != null) {
            System.out.println("Contact found, name: " + queryContact.getName() + " ,number: " + queryContact.getPhoneNumber());
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void printActions() {
        System.out.println("\nList of actions: \npress");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to add a new contact\n" +
                "3 - to update existing contact\n" +
                "4 - to remove existing contact\n" +
                "5 - to query if contact exists\n" +
                "6 - to print a list of actions\n");
        System.out.println("Choose your action...");

    }

    private static void startPhone() {
        System.out.println("Phone started...");
    }
}
