package User;

import java.util.Arrays;
import java.util.Objects;

import Product.Inventory;
import Product.Product;

public class UserBasket {
    private User user;
    private Product[] userBasket = new Product[10];
    private double totalAmount;

    // Constructor
    public UserBasket() {
        user = null;
    }

    public UserBasket(User user) {
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Add Product to Basket
    public void addProduct(String productId) {
        // ค้นหาสินค้าจาก Inventory โดยใช้ ProductID
        Inventory inventory = new Inventory();
        inventory.loadProductsFromFile();
        Product product = inventory.findProductById(productId);
        if (product != null) {
            // เพิ่มสินค้าลงในตะกร้า
            addToBasket(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void addToBasket(Product product) {
        for (int i = 0; i < userBasket.length; i++) {
            if (userBasket[i] == null) {
                userBasket[i] = product;
                System.out.println("Product added to basket.");
                return;
            }
        }
        System.out.println("Basket is full.");
    }

    // Remove Product to Basket
    public void removeProduct(String productId) {
        for (int i = 0; i < userBasket.length; i++) {
            if (userBasket[i] != null && userBasket[i].getProductID().equals(productId)) {
                userBasket[i] = null;
                System.out.println("Product removed from basket.");
                return;
            }
        }
        System.out.println("Product not found in basket.");
    }

    // เมทอดสำหรับแสดงสินค้าในตะกร้า
    public void displayBasket() {
        totalAmount = 0;
        for (Product product : userBasket) {
            if (product != null) {
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Brand: " + product.getProductBrand());
                System.out.println("Model: " + product.getProductModel());
                System.out.println("Type: " + product.getProductType());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("-----------------------");
                totalAmount += product.getProductPrice();
            }
        }
        System.out.println("Total number of products: " + Arrays.stream(userBasket).filter(Objects::nonNull).count());
        System.out.println("Total price: " + totalAmount);
    }

    public Product[] getUserBasket() {
        return userBasket;
    }    
}
