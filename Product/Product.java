package Product;

public class Product {
    private String productID;
    private String productBrand;
    private String productModel;
    private String productType;
    private double productPrice;

    public Product(){
        productID = "A-***";
        productBrand = "***";
        productModel = "***";
        productType = "***";
        productPrice = 0;
    }

    public Product(String productID, String productBrand, String produtModel, String produtType, Double produtPrice){
        this.productID = productID;
        this.productBrand = productBrand;
        this.productModel = produtModel;
        this.productType = produtType;
        this.productPrice = produtPrice;
    }

    public String getProductID() {
        return productID;
    }
    
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public String getProductBrand() {
        return productBrand;
    }
    
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
    
    public String getProductModel() {
        return productModel;
    }
    
    public void setProductModel(String produtModel) {
        this.productModel = produtModel;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String produtType) {
        this.productType = produtType;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double produtPrice) {
        this.productPrice = produtPrice;
    }
    
}
