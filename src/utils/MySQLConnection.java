package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try (FileInputStream fis = new FileInputStream("src/utils/Mysqldb.properties")) {

            Properties properties = new Properties();
            properties.load(fis);

            Class.forName(properties.getProperty("driver"));

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
