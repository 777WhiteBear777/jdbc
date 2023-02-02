package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends AbstractId {

    private String product = "";
    @Column(name = "total_price")
    private Float totalPrice = 0f;
    @Column(name = "user_id" , nullable = false)
    private Long userId;

    public Order() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product += product;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice += totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order {id = " + getId() +
                ", product='" + product + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }

}
