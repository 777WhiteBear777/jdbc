package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Order", schema = "shop")
public class Order extends AbstractId {
    private String product;
    @Column(name = "total_price")
    private Float totalPrice;
    @Column(name = "user_id")
    private Long userId;

    public Order() {
        this.product = "";
        this.totalPrice = 0f;
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
