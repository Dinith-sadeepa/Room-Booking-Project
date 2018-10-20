package lk.ijse.roombooking.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        File file = new File("resource/application.properties");
        FileReader fileReader = new FileReader(file);
        Properties properties = new Properties();
        properties.load(fileReader);

        String ip = properties.getProperty("ip");
        String port = properties.getProperty("port");
        String db = properties.getProperty("db");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        String jdbcURL = String.format("jdbc:mysql://%s:%s/%s", ip, port, db);

        connection = DriverManager.getConnection(jdbcURL, user, password);
    }

    public static DBConnection getInstance() throws Exception {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
