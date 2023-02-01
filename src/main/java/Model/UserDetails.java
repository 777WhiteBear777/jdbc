package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "User_details",schema = "shop")
public class UserDetails extends AbstractId{
    private String gender;
    private byte age;
    @Column(name = "user_id")
    private Long userId;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDetails{ Id = " + getId()+
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", userId=" + userId +
                '}';
    }
}
