package workShop;

import model.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsWS {

    public static UserDetails createUserDetailsRS(ResultSet resultSet) throws SQLException {

        UserDetails userDetails = new UserDetails();

        if (resultSet.next()) {
            userDetails.setId(resultSet.getLong("id"));
            userDetails.setUserId(resultSet.getLong("user_id"));
            userDetails.setGender(resultSet.getString("gender"));
            userDetails.setAge(resultSet.getByte("age"));
        }

        return userDetails;
    }

    public static List<UserDetails> createAllUserDetailsRS(ResultSet resultSet) throws SQLException {

        List<UserDetails> list = new ArrayList<>();

        while (resultSet.next()) {
            UserDetails userDetails = new UserDetails();
            userDetails.setId(resultSet.getLong("id"));
            userDetails.setUserId(resultSet.getLong("user_id"));
            userDetails.setGender(resultSet.getString("gender"));
            userDetails.setAge(resultSet.getByte("age"));
            list.add(userDetails);
        }

        return list;
    }

}
