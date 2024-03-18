package User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginForm {
    Scanner kb = new Scanner(System.in);

    private ArrayList<User> users;
    private String usernameField;
    private String passwordField;

    // LoginForm
    public void login() {
        loadUsersFromFile();

        boolean isValidUser = false;
        do {
            System.out.print("Username: ");
            usernameField = kb.next();

            // Check if the username exists
            for (User user : users) {
                if (user.getUsername().equals(usernameField)) {
                    isValidUser = true;
                    break;
                }
            }

            if (!isValidUser) {
                System.out.println("Invalid username. Please try again.");
            }
        } while (!isValidUser);

        isValidUser = false;
        do {
            System.out.print("Password: ");
            passwordField = kb.next();

            // Check if the password is correct for the username
            for (User user : users) {
                if (user.getUsername().equals(usernameField) && user.getPassword().equals(passwordField)) {
                    isValidUser = true;
                    break;
                }
            }

            if (!isValidUser) {
                System.out.println("Invalid password. Please try again.");
            }
        } while (!isValidUser);

        System.out.println("Login Success!!");
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
                    user.setUserID(id); // กำหนดไอดีให้กับ User
                    users.add(user); // เพิ่ม user เข้า ArrayList
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // แสดงข้อมูลทั้งหมดของผู้ใช้
    public void printAllUsers() {
        for (User user : users) {
            System.out.println("ID: " + user.getUserID());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Rank: " + user.getRank());
            System.out.println("- - - - - - - - - - - - - - - -");
        }
        System.out.println("Total users: " + users.size());
    }

    // เมทอดตรวจสอบ username และ password
    public boolean checkUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getRank(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getRank();
            }
        }
        return null; // หากไม่พบ username ที่ตรงกับที่ระบุให้คืนค่า null
    }

    public String getUsername() {
        return usernameField;
    }
}