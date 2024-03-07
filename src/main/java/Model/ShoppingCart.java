package Model;

public class ShoppingCart {
    private int id;
    private int clientId;
    private int productId;
    private int orderQuantity;
    public ShoppingCart(){
        super();
    }
    public ShoppingCart(int orderId, int clientId, int productId, int orderQuantity) {
        super();
        this.id = orderId;
        this.clientId = clientId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
    public ShoppingCart(int clientId, int productId, int orderQuantity) {
        super();
        this.clientId = clientId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "Order [Order_id=" + id + ", ClientId=" + clientId + ", ProductId=" + productId + ", Quantity=" + orderQuantity
                + "]";
    }
}
