package Model;

public class UserDetails {
    private int id;
    private String gender;
    private byte age;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", userId=" + userId +
                '}';
    }
}
