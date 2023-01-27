package DAO;

import Model.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    List<UserDetails> getAllUserDetails();
    Integer addUserDetails(UserDetails userDetails);
    UserDetails getUserDetails(int id);
    void updateUserDetails(UserDetails userDetails);
    void deleteUserDetails(int id);
}
