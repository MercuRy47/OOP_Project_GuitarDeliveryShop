package Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    private Product[] products;

    // โหลดข้อมูล Product ทั้งหมดจากไฟล์
    public void loadProductsFromFile() {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Data\\ProductData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        products = new Product[data.size()];
        for (int i = 0; i < data.size(); i++) {
            String[] parts = data.get(i).split(", ");
            products[i] = new Product(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
        }
    }

    // แสดงข้อมูลทั้งหมดของสินค้า
    public void printAllProducts() {
        for (Product product : products) {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Brand: " + product.getProductBrand());
            System.out.println("Model: " + product.getProductModel());
            System.out.println("Type: " + product.getProductType());
            System.out.println("Price: " + product.getProductPrice());
            System.out.println("- - - - - - - - - - - - - - - -");
        }
        System.out.println("Total products: " + products.length);
    }

    // แสดงข้อมูลสินค้าตาม Product ID ที่ระบุ
    public void printProductById(String productId) {
        int count = 0;
        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(productId)) {
                count++;
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("- - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("Total products: " + count);
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null; // ถ้าไม่พบสินค้า
    }

    // แสดงข้อมูลสินค้าตามแบรนด์ที่ระบุ
    public void printProductByBrand(String brand) {
        int count = 0;
        for (Product product : products) {
            if (product.getProductBrand().equalsIgnoreCase(brand)) {
                count++;
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("- - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("Total products: " + count);
    }

    // แสดงข้อมูลสินค้าตามประเภทที่ระบุ
    public void printProductByType(String type) {
        int count = 0;
        for (Product product : products) {
            if (product.getProductType().equalsIgnoreCase(type)) {
                count++;
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("- - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("Total products: " + count);
    }

    // แสดงข้อมูลสินค้าตามช่วงราคาที่กำหนด
    public void printProductByPriceRange(double minPrice, double maxPrice) {
        int count = 0;
        for (Product product : products) {
            if (product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice) {
                count++;
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("- - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("Total products: " + count);
    }

    // แสดงข้อมูลสินค้าตามโมเดลที่ระบุ
    public void printProductByModel(String model) {
        int count = 0;
        for (Product product : products) {
            if (product.getProductModel().equalsIgnoreCase(model)) {
                count++;
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("- - - - - - - - - - - - - - - -");
            }
        }
        System.out.println("Total products: " + count);
    }

    // เรียงลำดับสินค้าตามราคาจากต่ำไปสูง (Ascending)
    public Inventory sortByPriceAsc() {
        Arrays.sort(products, (p1, p2) -> Double.compare(p1.getProductPrice(), p2.getProductPrice()));
        return this;
    }

    // เรียงลำดับสินค้าตามราคาจากสูงไปต่ำ (Descending)
    public Inventory sortByPriceDesc() {
        Arrays.sort(products, (p1, p2) -> Double.compare(p2.getProductPrice(), p1.getProductPrice()));
        return this;
    }
}