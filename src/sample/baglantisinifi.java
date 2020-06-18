package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class baglantisinifi {
    public Connection baglanti;

    public Connection getBaglanti() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/fruitjanissary";
            Properties connectionProps = new Properties();
            connectionProps.setProperty("user","root");
            connectionProps.setProperty("password","admin");
            connectionProps.setProperty("useSSL","false");
            connectionProps.setProperty("serverTimezone","UTC");
            connectionProps.setProperty("allowPublicKeyRetrieval","true");
            baglanti = DriverManager.getConnection(url,connectionProps);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return baglanti;
    }
}
