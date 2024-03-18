package Tester;

import java.util.ArrayList;
import java.util.Scanner;

import Delivery.DeliveryManager;
import Delivery.FastDeliveryService;
import Delivery.NormalDeliveryService;
import ManagerStore.Manager;
import Product.Inventory;
import Product.Product;
import User.LoginForm;
import User.PremiumUser;
import User.RegisterForm;
import User.User;

public class Tester {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String choose;
        double totalAmount = 0;

        // Class
        // Class
        Inventory inventory = new Inventory();
        Manager manager = new Manager();
        RegisterForm newUser = new RegisterForm();
        LoginForm loginForm = new LoginForm();
        User user = new User();
        User premium = new PremiumUser();
        DeliveryManager deliveryManager = new DeliveryManager();
        NormalDeliveryService normalDelivery = new NormalDeliveryService(350);
        FastDeliveryService fastDelivery = new FastDeliveryService(500);

        inventory.loadProductsFromFile();

        // Login / Register / Manager / Exit
        System.out.print("[Welcome to Guitar Shop]" + "\nL - Login" + "\nR - Register" + "\nM - Manager" + "\nE - Exit" + "\nChoose: ");
        choose = kb.next();
        if (choose.equals("L")) {
            System.out.println("[Login]");
            loginForm.login();
            while (true) {
                // Add Product / Delete Product / Basket / Inventory / Exit
                System.out.print("A - Add Product" + "\nR - Remove Product" + "\nB - Basket" + "\nC - Check out" + "\nI - Inventory" + "\nE - Exit" + "\nChoose: ");
                choose = kb.next();
                // Add Product
                if (choose.equals("A")){
                    System.out.println("[Add Produc]");
                    String productID = "";
                    System.out.print("Product ID: ");
                    productID = kb.next();
                    user.getBasket().addProduct(productID);
                // Remove Product
                } else if (choose.equals("R")) {
                    System.out.println("[Remove Product]");
                    String productID = "";
                    System.out.print("Product ID: ");
                    productID = kb.next();
                    user.getBasket().removeProduct(productID);
                // All products in basket
                } else if (choose.equals("B")) {
                    System.out.println("[All products in " + loginForm.getUsername() + " basket]");
                    user.getBasket().displayBasket();
                // Check out]
                } else if (choose.equals("C")) {
                    System.out.println("=======[Check out]=======");
                    System.out.println("[All products in " + loginForm.getUsername() + " basket]");
                    user.getBasket().displayBasket();
                    System.out.println();
                    // Check Rank
                    if (loginForm.getRank(loginForm.getUsername()) != null) {
                        if (loginForm.getRank(loginForm.getUsername()).equals("Normal")) {
                            System.out.println("Your rank: Normal (not receive a discount)");
                            totalAmount = user.getBasket().getTotalAmount();
                        } else if (loginForm.getRank(loginForm.getUsername()).equals("Premium")) {
                            System.out.println("Your rank: Premium (receive 30% discount)");
                            System.out.println( "Remaining price: " + user.getBasket().getTotalAmount() * premium.getDiscount());
                            totalAmount = user.getBasket().getTotalAmount() - (user.getBasket().getTotalAmount() * premium.getDiscount());
                        } else {
                            System.out.println("Invalid rank");
                        }
                    } else {
                        System.out.println("Rank not found");
                    }
                    // Delivery Service
                    // Normal Delivery(within 3 days) / Fast Delivery(within 24 hours) / Exit
                    System.out.print("N - Normal Delivery(within 3 days) + 350 Baht" + "\nF - Fast Delivery(within 24 hours) + 500 Baht" + "\nE - Exit" + "\nChoose: ");
                    choose = kb.next();
                    String deliveryType = "";
                    // Normal Delivery
                    if (choose.equals("N")){
                        totalAmount += normalDelivery.deliverPrice(); 
                        deliveryType = "Normal Delivery";
                    // Fast Delivery
                    } else if (choose.equals("F")) {
                        totalAmount += fastDelivery.deliverPrice(); 
                        deliveryType = "Fast Delivery";
                    // Exit
                    } else if (choose.equals("E")) {
                        System.out.println("You have exited the program.");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice.");
                        break;
                    }
                    System.out.println("[Total Amount: " + totalAmount + " Baht]");
                    // Address
                    String address = "";
                    boolean okay = true;
                    while (okay) {
                        System.out.println("[Your Address]");
                        System.out.print("Address: ");
                        address = kb.next();

                        System.out.print("Confirm address or not? (Yes/No): ");
                        choose = kb.next();
                        if (choose.equalsIgnoreCase("Yes")) {
                            okay = false;
                            System.out.println("Thank you for purchasing from our store.");
                            // Save to File
                            deliveryManager.writeDeliveryToFile(deliveryType, address, user.getBasket().getUserBasket(), totalAmount);
                            System.exit(0);
                        } else if (choose.equalsIgnoreCase("No")){
                            break;
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                // Products
                } else if (choose.equals("I")) {
                    System.out.println("[Inventory]");
                    // Search System
                    // All Product / Product ID / Product Brand / Product Model / Product Type / Product Price(Max-Min/Min-Max) / Exit
                    System.out.print("AP - All Product" + "\nPID - Product ID" + "\nPB = Product Brand" + "\nPM = Product Model" + "\nPT = Product Type" + "\nPP = Product Price(Max-Min/Min-Max)" + "\nE - Exit" + "\nChoose: ");
                    choose = kb.next();
                    while (true) {
                        // printAllProducts
                        if (choose.equals("AP")) {
                            System.out.println("[All Product]");
                            inventory.printAllProducts();
                            break;
                        // printProductById
                        } else if (choose.equals("PID")){
                            System.out.println("[Product ID]");
                            String productID = "";
                            System.out.print("ID: ");
                            productID = kb.next();
                            inventory.printProductById(productID);
                            break;
                        // printProductByBrand
                        } else if (choose.equals("PB")){
                            System.out.println("[Product Brand]");
                            String productbrand = "";
                            System.out.print("Brand: ");
                            productbrand = kb.next();
                            inventory.printProductByBrand(productbrand);
                            break;
                        // printProductByModel
                        } else if (choose.equals("PM")){
                            System.out.println("[Product Model]");
                            String productmodel = "";
                            System.out.print("Model: ");
                            productmodel = kb.next();
                            inventory.printProductByModel(productmodel);
                            break;
                        // printProductByType
                        } else if (choose.equals("PT")){
                            System.out.println("[Product Type]");
                            String producttype = "";
                            System.out.print("Type: ");
                            producttype = kb.next();
                            inventory.printProductByType(producttype);
                            break;
                        // printProductByPriceRange
                        } else if (choose.equals("PP")){
                            int min = 0;
                            int max = 0;
                            System.out.println("[Product Price(Min-Max)]");
                            System.out.print("Min: ");
                            min = kb.nextInt();
                            System.out.print("Max: ");
                            max = kb.nextInt();
                            System.out.println("[Product Price (" + min + "-" + max + ")]");
                            inventory.printProductByPriceRange(min, max);
                            break;
                        } else if (choose.equals("E")){
                            System.out.println("You have exited the program.");
                            System.exit(0);
                        } else {
                            System.out.println("Invalid choice.");
                            break;
                        }
                    }
                } else if (choose.equals("E")) {
                    System.out.println("You have exited the program.");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice.");
                }
            }
            System.out.println("[User - Mode]");
        } else if (choose.equals("R")) {
            System.out.println("[Register]");
            newUser.register();
        } else if (choose.equals("E")) {
            System.out.println("You have exited the program.");
            System.exit(0);
        } else if (choose.equals("M")) {
            System.out.println("[Manager - Login]");
            loginForm.login();
            if (loginForm.getUsername().equals("wanasart")) {
                System.out.println("[Manager - Mode]");
                while (true) {
                    // Delete User / List User / List Normal Users / List Premium Users / Inventory / Exit
                    System.out.print("D - Delete User" + "\nL - List User" + "\nN - List Normal Users" + "\nR - List Premium Users" + "\nI - Inventory" + "\nE - Exit" + "\nChoose: ");
                    choose = kb.next();
                    if (choose.equals("D")){
                        manager.deleteUserByID();
                    } else if (choose.equals("L")) {
                        System.out.println("[List User]");
                        manager.showAllUsers();
                    } else if (choose.equals("N")) {
                        System.out.println("[List Normal Users]");
                        manager.printNormalUsers();
                    } else if (choose.equals("P")) {
                        System.out.println("[List Premium Users]");
                        manager.printPremiumUsers();
                    } else if (choose.equals("I")) {
                        System.out.println("[Inventory]");
                        // Search System
                        // All Product / Product ID / Product Brand / Product Model / Product Type / Product Price(Max-Min/Min-Max) / Exit
                        System.out.print("AP - All Product" + "\nPID - Product ID" + "\nPB = Product Brand" + "\nPM = Product Model" + "\nPT = Product Type" + "\nPP = Product Price(Max-Min/Min-Max)" + "\nE - Exit" + "\nChoose: ");
                        choose = kb.next();
                        while (true) {
                            // printAllProducts
                            if (choose.equals("AP")) {
                                System.out.println("[All Product]");
                                inventory.printAllProducts();
                                break;
                            // printProductById
                            } else if (choose.equals("PID")){
                                System.out.println("[Product ID]");
                                String productID = "";
                                System.out.print("ID: ");
                                productID = kb.next();
                                inventory.printProductById(productID);
                                break;
                            // printProductByBrand
                            } else if (choose.equals("PB")){
                                System.out.println("[Product Brand]");
                                String productbrand = "";
                                System.out.print("Brand: ");
                                productbrand = kb.next();
                                inventory.printProductByBrand(productbrand);
                                break;
                            // printProductByModel
                            } else if (choose.equals("PM")){
                                System.out.println("[Product Model]");
                                String productmodel = "";
                                System.out.print("Model: ");
                                productmodel = kb.next();
                                inventory.printProductByModel(productmodel);
                                break;
                            // printProductByType
                            } else if (choose.equals("PT")){
                                System.out.println("[Product Type]");
                                String producttype = "";
                                System.out.print("Type: ");
                                producttype = kb.next();
                                inventory.printProductByType(producttype);
                                break;
                            // printProductByPriceRange
                            } else if (choose.equals("PP")){
                                int min = 0;
                                int max = 0;
                                System.out.println("[Product Price(Min-Max)]");
                                System.out.print("Min: ");
                                min = kb.nextInt();
                                System.out.print("Max: ");
                                max = kb.nextInt();
                                System.out.println("[Product Price (" + min + "-" + max + ")]");
                                inventory.printProductByPriceRange(min, max);
                                break;
                            } else if (choose.equals("E")){
                                System.out.println("You have exited the program.");
                                System.exit(0);
                            } else {
                                System.out.println("Invalid choice.");
                                break;
                            }
                        }
                    } else if (choose.equals("E")) {
                        System.out.println("You have exited the program.");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice.");
                        break;
                    }
                }
            } else {
                System.out.println("You do not have permission to access the manager mode.");
            }
        } else {
            System.out.println("Invalid choice.");
        }

    }
}