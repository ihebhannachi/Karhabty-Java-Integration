package project.karhabty.technical;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 02/02/2017.
 */
public class DBConfig {
    private static DBConfig Instance ;

    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Properties properties;

    private DBConfig() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config.properties")));
            this.url=properties.getProperty("url");
            this.user=properties.getProperty("user");
            this.password=properties.getProperty("password");
            this.connection= DriverManager.getConnection(this.url,this.user,this.password);
            //System.out.println("Connection etablished");
        } catch (IOException | SQLException e) {
            //e.printStackTrace();
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static DBConfig getInstance() {
        if (Instance==null){Instance = new DBConfig();}
        return Instance;
    }
    public Connection getConnection(){
        return this.connection;
    }
}
