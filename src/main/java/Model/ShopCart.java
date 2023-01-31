package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class ShopCart extends AbstractId{
    @Column
    private int userId;
    @Column
    private int productId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartShop{ Id = " + getId()+
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
