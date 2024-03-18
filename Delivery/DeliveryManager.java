package Delivery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;

import Product.Product;

public class DeliveryManager {
    private static int deliveryCount = 0;

    public void writeDeliveryToFile(String deliveryType, String address, Product[] products, double totalAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data\\DeliveryData.txt", true))) {
            deliveryCount++; // Increment deliveryCount only when opening the file

            LocalDateTime printedDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            String formattedDateTime = printedDateTime.format(formatter);

            String deliveryID = "D-" + String.format("%03d", countLines("Data\\DeliveryData.txt") + 1);

            writer.write(deliveryID + ", " + deliveryType + ", " + formattedDateTime + ", " + address + ", ");
            writer.write("[");
            for (int i = 0; i < products.length; i++) {
                Product product = products[i];
                if (product != null) {
                    writer.write("(" + product.getProductBrand() + " " + product.getProductModel() + " "
                            + product.getProductType() + " " + product.getProductPrice() + ")");
                    if (i < products.length - 1) {
                        writer.write(", ");
                    }
                }
            }
            writer.write("], " + totalAmount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
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
