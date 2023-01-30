package org.example;

import DAO.Impl.UserHibernateDAO;
import Model.User;

import java.util.ArrayList;
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
       List<User> list = new ArrayList<>();
//        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//
//        System.out.println(userJDBCDAO.getById(2));
//        int a = userJDBCDAO.addObj(user);
//        user.setId(a - 1);
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//        user.setLastname("Kilaf");
//        System.out.println(user.getId());
//        userJDBCDAO.update(user);
//        list = userJDBCDAO.getAll();
//        System.out.println(list);
//
//        userJDBCDAO.delete(3);
//        list = userJDBCDAO.getAll();
//        System.out.println(list);

        UserHibernateDAO userDAO = new UserHibernateDAO();
//        userDAO.deleteUser(6);
        list= userDAO.getAllUser();
        System.out.println(list);
//
        userDAO.addUser(user);
    }
}
