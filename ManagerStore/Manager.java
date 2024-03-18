package ManagerStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import User.User;

public class Manager {
    Scanner kb = new Scanner(System.in);

    private ArrayList<User> users;

    public Manager() {
        users = new ArrayList<>();
    }

    // โหลดข้อมูล User ทั้งหมดจากไฟล์
    public void loadUsersFromFile() {
        users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Data\\UserData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String id = parts[0];
                    String username = parts[1];
                    String password = parts[2];
                    String rank = parts[3];
                    User user = new User(username, password, rank);
                    user.setUserID(id); // Set the ID for the user
                    users.add(user); // Add the user to the ArrayList
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete a user by userID
    public void deleteUserByID() {
        loadUsersFromFile();

        System.out.print("Enter the userID of the user to delete: ");
        String userID = kb.next();
        
        boolean userFound = false;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                userFound = true;
                System.out.println("User found:");
                System.out.println("ID: " + user.getUserID());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Rank: " + user.getRank());
                System.out.print("Do you want to delete this user? (Yes/No): ");
                String confirm = kb.next();
                if (confirm.equalsIgnoreCase("Yes")) {
                    users.remove(user);
                    System.out.println("User deleted successfully.");
                    break;
                } else {
                    System.out.println("User not deleted.");
                }
            }
        }
        if (!userFound) {
            System.out.println("User not found.");
        }

        updateUserDataFile();
    }

    // Update the UserData file after deleting a user
    private void updateUserDataFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Data\\UserData.txt"))) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                String newUserID = "U-" + String.format("%03d", i + 1);
                user.setUserID(newUserID);
                writer.println(newUserID + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRank());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show All User
    public void showAllUsers() {
        loadUsersFromFile();
    
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
    
        for (User user : users) {
            System.out.println("ID: " + user.getUserID());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Rank: " + user.getRank());
            System.out.println("- - - - - - - - - - - - - - - -");
        }
        System.out.println("Total users: " + users.size());
    }

    // เมทอดสำหรับเลือกดูเฉพาะ user ที่เป็น Normal
    public void printNormalUsers() {
        loadUsersFromFile();
        int normalCount = 0;
        for (User user : users) {
            if (user.getRank().equals("Normal")) {
                System.out.println("ID: " + user.getUserID());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Rank: " + user.getRank());
                System.out.println("- - - - - - - - - - - - - - - -");
                normalCount++;
            }
        }
        System.out.println("Total Normal users: " + normalCount);
    }

    // เมทอดสำหรับเลือกดูเฉพาะ user ที่เป็น Premium
    public void printPremiumUsers() {
        loadUsersFromFile();
        int premiumCount = 0;
        for (User user : users) {
            if (user.getRank().equals("Premium")) {
                System.out.println("ID: " + user.getUserID());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Rank: " + user.getRank());
                System.out.println("- - - - - - - - - - - - - - - -");
                premiumCount++;
            }
        }
        System.out.println("Total Premium users: " + premiumCount);
    }
    
}
