package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();

    Integer addUser(User user);

    User getUser(int id);

    void updateUser(User user);

    void deleteUser(int id);
}
