package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class UserDetails extends AbstractId{
    @Column
    private String gender;
    @Column
    private byte age;
    @Column
    private int userId;


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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", userId=" + userId +
                '}';
    }
}
