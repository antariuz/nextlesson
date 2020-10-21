package db;

import dao.impl.MSCarDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {

    private final Logger LOGGER = LogManager.getLogger(MSCarDAO.class.getName());

    public Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(fileInputStream);
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (IOException | SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return connection;
    }

}
