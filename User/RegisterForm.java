package User;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class RegisterForm {
    Scanner kb = new Scanner(System.in);

    private String usernameField;
    private String passwordField;
    private String confirmpassword;

    public RegisterForm() {
        usernameField = "***";
        passwordField = "***";
        confirmpassword = "***";
    }

    public RegisterForm(String username, String password, String confirmPassword) {
        this.usernameField = username;
        this.passwordField = password;
        this.confirmpassword = confirmPassword;
    }

    // RegisterForm
    public void register(){
        do {
            System.out.print("Username: ");
            usernameField = kb.next();
            
            try (BufferedReader br = new BufferedReader(new FileReader("Data\\UserData.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (parts.length >= 2) {
                        String username = parts[1];
                        if (username.equals(usernameField)) {
                            System.out.println("Username already exists. Please try again with a different username.");
                            usernameField = null; // Reset usernameField to trigger re-entry
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading user data: " + e.getMessage());
            }
        } while (usernameField == null);
    
        System.out.print("Password: ");
        passwordField = kb.next();
        
        do {
            System.out.print("Confirm Password: ");
            confirmpassword = kb.next();
            
            if (!passwordField.equals(confirmpassword)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!passwordField.equals(confirmpassword));
    
        addNewUserToFile();
    }
    
    // Adds a new user to the file
    public void addNewUserToFile() {
        if (!passwordField.equals(confirmpassword)) {
            System.out.println("Passwords do not match. Please try again.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Data\\UserData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    String username = parts[1];
                    if (username.equals(usernameField)) {
                        System.out.println("Username already exists. Please try again with a different username.");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Data\\UserData.txt", true))) {
            // สร้าง ID ใหม่โดยนับจำนวนบรรทัดในไฟล์และเพิ่ม 1
            int newId = countLines("Data\\UserData.txt") + 1;
            String id = "U-" + String.format("%03d", newId);
            // เขียนข้อมูลของสมาชิกใหม่ลงในไฟล์
            writer.println(id + ", " + usernameField + ", " + passwordField + ", Normal");
            System.out.println("User added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    private int countLines(String filename) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null)
                lines++;
        }
        return lines;
    }
}
