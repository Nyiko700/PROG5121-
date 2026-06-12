/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part3;

/**
 *
 * @author nyiko
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Login {

    String username;
    String password;
    String cellPhoneNumber;
    String firstName;
    String lastName;

    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Username validation
    public boolean checkUsername() {

        return username.contains("_") && username.length() <= 5;
    }

    public String registerUsername() {

        if (checkUsername()) {

            return "Username successfully captured.";

        } else {

            return "Username is not correctly formatted.";
        }
    }

    // Password validation
    public boolean checkPasswordComplexity() {

        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");

        return length && capital && number && special;
    }

    public String registerPassword() {

        if (checkPasswordComplexity()) {

            return "Password successfully captured.";

        } else {

            return "Password is not correctly formatted.";
        }
    }

    // Phone number validation
    public boolean checkCellPhoneNumber() {

        return cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() <= 12;
    }

    public String registerCellPhoneNumber() {

        if (checkCellPhoneNumber()) {

            return "Cell phone number successfully added.";

        } else {

            return "Cell phone number incorrectly formatted.";
        }
    }

    // Login validation
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    public String returnLoginStatus(boolean loginStatus) {

        if (loginStatus) {

            return "Welcome " + firstName + " " + lastName + ", it is nice to see you again.";

        } else {

            return "Username or password incorrect.";
        }
    }
}

class QuickChat {

    String messageID;
    String recipient;
    String text;
    String messageHash;
    String flag;

    static int totalMessages = 0;

    // Arrays
    static ArrayList<QuickChat> sentMessages = new ArrayList<>();
    static ArrayList<QuickChat> storedMessages = new ArrayList<>();
    static ArrayList<QuickChat> discardedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    // Constructor
    public QuickChat(String recipient, String text, String flag) {

        this.recipient = recipient;
        this.text = text;
        this.flag = flag;

        generateMessageID();
        createMessageHash();

        messageIDs.add(messageID);
        messageHashes.add(messageHash);
    }

    // Generate random ID
    public void generateMessageID() {

        Random random = new Random();

        messageID = String.valueOf(1000000000L + (long)(random.nextDouble() * 9000000000L));
    }

    // Check message length
    public boolean checkMessage() {

        return text.length() <= 250;
    }

    // Check recipient
    public boolean checkRecipientCell() {

        return recipient.startsWith("+27") && recipient.length() <= 12;
    }

    // Create hash
    public void createMessageHash() {

        String[] words = text.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = messageID.substring(0, 2) + ":"  + (totalMessages + 1) + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    // Process message
    public void processMessage() {

        if (flag.equalsIgnoreCase("Sent")) {

            sentMessages.add(this); totalMessages++;

        } else if (flag.equalsIgnoreCase("Stored")) {

            storedMessages.add(this);

        } else if (flag.equalsIgnoreCase("Disregard")) {

            discardedMessages.add(this);
        }
    }

    // Print details
    public String printMessage() {

        return "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + text;
    }

    // Display sent messages
    public static void displaySentMessages() {

        System.out.println("\n===== SENT MESSAGES =====");

        for (QuickChat msg : sentMessages) {

            System.out.println(msg.text);
        }
    }

    // Longest stored message
    public static void displayLongestMessage() {

        String longest = "";

        for (QuickChat msg : storedMessages) {

            if (msg.text.length() > longest.length()) {

                longest = msg.text;
            }
        }

        System.out.println("\nLongest Message: " + longest);
    }

    // Search by ID
    public static void searchMessageID(String id) {

        for (QuickChat msg : sentMessages) {

            if (msg.messageID.equals(id)) {

                System.out.println("\nRecipient: " + msg.recipient);

                System.out.println("Message: " + msg.text);
            }
        }
    }

    // Search by recipient
    public static void searchRecipient(String number) {

        for (QuickChat msg : storedMessages) {

            if (msg.recipient.equals(number)) {

                System.out.println(msg.text);
            }
        }
    }

    // Delete by hash
    public static void deleteByHash(String hash) {

        for (int i = 0; i < storedMessages.size(); i++) {

            if (storedMessages.get(i).messageHash.equals(hash)) {

                System.out.println("Message \"" + storedMessages.get(i).text + "\" successfully deleted.");

                storedMessages.remove(i);

                return;
            }
        }

        System.out.println("Hash not found.");
    }

    // Display report
    public static void displayReport() {

        System.out.println("\n===== SENT MESSAGE REPORT =====");

        for (QuickChat msg : sentMessages) {

            System.out.println("\nMessage Hash: " + msg.messageHash);

            System.out.println("Recipient: " + msg.recipient);

            System.out.println("Message: " + msg.text);
        }
    }
}

class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ========================= PART 1 - REGISTRATION =========================
        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Cell Phone Number: ");
        String phone = input.nextLine();

        Login user = new Login(username, password, phone, firstName, lastName);

        System.out.println(user.registerUsername());

        System.out.println(user.registerPassword());

        System.out.println(user.registerCellPhoneNumber());

        // ========================= LOGIN =========================
        if (user.checkUsername()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Enter Username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            boolean loginStatus = user.loginUser(loginUsername, loginPassword);

            System.out.println(user.returnLoginStatus(loginStatus));

            // =========================PART 2 + PART 3 =========================
            if (loginStatus) {

                // Test data
                QuickChat m1 = new QuickChat("+27834557896", "Did you get the cake?", "Sent");
                QuickChat m2 = new QuickChat("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
                QuickChat m3 = new QuickChat("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
                QuickChat m4 = new QuickChat("0838884567", "It is dinner time !", "Sent");
                QuickChat m5 = new QuickChat("+27838884567", "Ok, I am leaving without you.", "Stored");

                m1.processMessage();
                m2.processMessage();
                m3.processMessage();
                m4.processMessage();
                m5.processMessage();

                int option;

                do {

                    System.out.println("\n===== QUICKCHAT MENU =====");

                    System.out.println("1. Display Sent Messages");

                    System.out.println("2. Display Longest Message");

                    System.out.println("3. Search Message ID");

                    System.out.println("4. Search Messages by Recipient");

                    System.out.println("5. Delete Message using Hash");

                    System.out.println("6. Display Report");

                    System.out.println("7. Quit");

                    System.out.print("Choose option: ");

                    option = input.nextInt();input.nextLine();

                    switch (option) {

                        case 1:

                            QuickChat.displaySentMessages();

                            break;

                        case 2:

                            QuickChat.displayLongestMessage();

                            break;

                        case 3:

                            System.out.print("Enter Message ID: ");

                            String id = input.nextLine();

                            QuickChat.searchMessageID(id);

                            break;

                        case 4:

                            System.out.print("Enter recipient number: ");

                            String recipient = input.nextLine();

                            QuickChat.searchRecipient(recipient);

                            break;

                        case 5:

                            System.out.print("Enter Message Hash: ");

                            String hash = input.nextLine();

                            QuickChat.deleteByHash(hash);

                            break;

                        case 6:

                            QuickChat.displayReport();

                            break;

                        case 7:

                            System.out.println("Exiting QuickChat...");

                            break;

                        default:

                            System.out.println("Invalid option.");
                    }

                } while (option != 7);
            }

        } else {

            System.out.println("\nRegistration failed.");
        }

        input.close();
    }
}