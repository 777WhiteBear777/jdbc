package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Order extends AbstractId {
    @Column
    private String product;
    @Column(name = "total-price")
    private double totalPrice;
    @Column
    private int userId;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product += product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", product='" + product + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }
}
