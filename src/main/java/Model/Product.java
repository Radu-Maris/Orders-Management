package Model;
public class Product {
    private int id;
    private String productName;
    private int productPrice;
    private int productStock;
    public Product(){
        super();
    }
    public Product(int productId, String productName, int productPrice,int productStock) {
        super();
        this.id = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }
    public Product(String productName, int productPrice,int productStock) {
        super();
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductStock() {
        return productStock;
    }
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + productName + ", price=" + productPrice + ", stock=" + productStock
                + "]";
    }

}