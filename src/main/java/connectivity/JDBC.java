package connectivity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private final String URL = "jdbc:mysql://localhost:3306/shop";
    private final String USER = "root";
    private final String PASSWORD = "3248";
    private Connection connection;
    private final Logger LOGGER = LogManager.getLogger(JDBC.class.getName());

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return connection;
    }

}
