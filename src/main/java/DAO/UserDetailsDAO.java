package DAO;

import Model.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    List<UserDetails> getAllUserDetails();

    Long addUserDetails(UserDetails userDetails);

    UserDetails getUserDetails(Long id);

    void updateUserDetails(UserDetails userDetails);

    void deleteUserDetails(Long id);
}
