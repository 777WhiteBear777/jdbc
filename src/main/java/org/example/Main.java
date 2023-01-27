package org.example;

import DAO.Impl.UserJDBCDAO;
import Model.User;

import java.util.List;

public class Main {


//    private static PreparedStatement statement ;
//    private static ResultSet resultSet;

    public static void main(String[] args) {
        User user = new User();
        user.setFirstname("Joe");
        user.setLastname("Baiden");

        final String insertUser = "INSERT INTO user (id, firstname, lastname) VALUES (?,?,?)";
        final String selectUserSql = "SELECT * FROM user";
        List<User> list;
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        list = userJDBCDAO.getAll();
        System.out.println(list);


        System.out.println(userJDBCDAO.getById(2));
        int a = userJDBCDAO.addObj(user);
        user.setId(a - 1);
        list = userJDBCDAO.getAll();
        System.out.println(list);

        user.setLastname("Kilaf");
        System.out.println(user.getId());
        userJDBCDAO.update(user);
        list = userJDBCDAO.getAll();
        System.out.println(list);

        userJDBCDAO.delete(3);
        list = userJDBCDAO.getAll();
        System.out.println(list);


//        try {
//
//            statement= connection.prepareStatement(selectUserSql);
//
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String firstname = resultSet.getString("firstname");
//                String lastname = resultSet.getString("lastname");
//                System.out.println("id: " + id + " | name: " + firstname + " | lastname: " + lastname);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                System.out.println("ResulSet error....");
//            }
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                System.out.println("Statement error....");
//            }
//        }

    }
}
