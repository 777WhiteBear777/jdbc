package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shop_cart", schema = "shop")
public class ShopCart {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "product_id")
    private Long productId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartShop{ " +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
