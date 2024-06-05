package pjatk.tpo.demo3;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private int orderId;
    private String userName;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private Date orderDate;

    // Constructors
    public Order(int orderId, String userName, String productName, int quantity, BigDecimal price, Date orderDate) {
        this.orderId = orderId;
        this.userName = userName;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Order() {
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderID: " + orderId + ", User: " + userName + ", Product: " + productName +
                ", Quantity: " + quantity + ", Price: " + price + ", Order Date: " + orderDate;
    }
}
