package project.karhabty.services;

import com.csvreader.CsvWriter;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.AdminIServices;
import project.karhabty.technical.DBConfig;
import project.karhabty.technical.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 12/02/2017.
 * This Class Containts Admin Services
 */
public class AdminServices implements AdminIServices {
    private Connection connection;

    public AdminServices() {
  this.connection=DBConfig.getInstance().getConnection();
    }

    @Override
    public boolean disable_user(int id) {
        try {
            String request="UPDATE `users` SET `enabled`= ? WHERE `id`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setBoolean(1,false);
            statement.setInt(2,id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(AdminServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    @Override
    public boolean enable_user(int id) {
        try {
            String request="UPDATE `users` SET `enabled`= ? WHERE `id`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setBoolean(1,true);
            statement.setInt(2,id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(AdminServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public void notif_newusers() {
        try {

            String request="SELECT count(Creation_date) as result FROM `users` WHERE DATE( `Creation_date`)=DATE(CURRENT_TIMESTAMP) AND `User_roles`='Role_ADMIN'";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Image img = new Image("/img/user_male_add.png");
                Notifications notificationBuilder= Notifications.create()
                        .title("Notification")
                        .text("Aujourd'hui il y a "+result.getString("result")+" Compte(s) creer")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT);
                notificationBuilder.show();
            }


        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    @Override
    public String account_created() {
        try {

            String request="SELECT count(Creation_date) as accountcount FROM `users` WHERE `User_roles`='Role_ADMIN'";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
           result.next();
                return result.getString("accountcount");

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }


    }

    @Override
    public String account_created_yearly() {
        try {
            String request="SELECT count(Creation_date) as accountcount FROM `users` WHERE date_format(Creation_date,'%Y')=date_format(current_timestamp,'%Y') AND `User_roles`='Role_ADMIN'";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("accountcount");

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public String account_disabled() {
        try {
            String request="SELECT count(enabled) as account_enabled FROM `users` WHERE `enabled`=FALSE AND `User_roles`='Role_ADMIN'";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("account_enabled");

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public String requested_password_number() {
        try {
            String request="SELECT count(password_requested_at) as accountcount  FROM `users` WHERE `password_requested_at` IS NOT NULL";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("accountcount");

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean generate_backup() {
        try {
            String outputFile = "users.csv";
            // before we open the file check to see if it already exists
            boolean alreadyExists = new File(outputFile).exists();
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

            // if the file didn't already exist then we need to write out the header line
            if (!alreadyExists)
            {
                csvOutput.write("id");
                csvOutput.write("First_name");
                csvOutput.write("Last_name");
                csvOutput.write("email");
                csvOutput.write("password");
                csvOutput.write("Mobile");
                csvOutput.write("Tel");
                csvOutput.write("Address");
                csvOutput.write("confirmation_token");
                csvOutput.write("User_Role");
                csvOutput.write("Creation_date");
                csvOutput.write("enabled");
                csvOutput.endRecord();
            }

            String request="SELECT * FROM `users`";
            PreparedStatement statement= connection.prepareStatement(request);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                csvOutput.write(String.valueOf(result.getInt("id")));
                csvOutput.write(result.getString("First_name"));
                csvOutput.write(result.getString("Last_name"));
                csvOutput.write(result.getString("email"));
                csvOutput.write(result.getString("password"));
                csvOutput.write(String.valueOf(result.getInt("Mobile")));
                csvOutput.write(String.valueOf(result.getInt("Tel")));
                csvOutput.write(result.getString("Address"));
                csvOutput.write(result.getString("confirmation_token"));
                csvOutput.write(result.getString("User_roles"));
                csvOutput.write(String.valueOf(result.getDate("Creation_date")));
                csvOutput.write(String.valueOf(result.getBoolean("enabled")));
                csvOutput.endRecord();
            }
            csvOutput.close();
            return true;
        } catch (SQLException | IOException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
}
