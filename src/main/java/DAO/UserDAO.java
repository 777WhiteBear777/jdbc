package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();

    Long addUser(User user);

    User getUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}
