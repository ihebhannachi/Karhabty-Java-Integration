package project.karhabty.services;

import javafx.scene.image.Image;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.entities.Users;
import project.karhabty.technical.DBConfig;
import project.karhabty.technical.Session;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.karhabty.technical.BCrypt;

/**
 * Created by iheb on 05/02/2017.
 * This Class contain all Users class methode
 */
public class UsersServices implements UsersIServices {
    private Connection connection;

    public UsersServices() {
        this.connection= DBConfig.getInstance().getConnection();
    }

    @Override
    public boolean add_user(Users u) {
        try {
        String requete="INSERT INTO `users`(`First_name`, `Last_name`, `email`, `password`, `Mobile`, `Tel`, `Address`, `confirmation_token`, `User_roles`, `enabled`,`username`,`username_canonical`,`email_canonical`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
         PreparedStatement statement= connection.prepareStatement(requete);
         statement.setString(1,u.getFirst_name());
         statement.setString(2, u.getLast_name());
         statement.setString(3, u.getEmail());
         statement.setString(4, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13)));
         statement.setInt(5, u.getMobile_number());
         statement.setInt(6, u.getTel_number());
         statement.setString(7, u.getAddress());
         statement.setString(8,MD5Hashing(u.getEmail()));
         statement.setString(9, "Role_ADMIN");
         statement.setBoolean(10,true);
         statement.setString(11,u.getFirst_name()+u.getLast_name());
         statement.setString(12,u.getFirst_name()+u.getLast_name());
         statement.setString(13, u.getEmail());
         statement.executeUpdate();
         return true;
        } catch (SQLException | NoSuchAlgorithmException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    @Override
    public String MD5Hashing(String password) throws NoSuchAlgorithmException {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
             StringBuilder hexString = new StringBuilder();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);

            }
            return hexString.toString();

    }

    @Override
    public boolean check_email(String email) {

        try {
            String request="SELECT DISTINCT `email` FROM `users` WHERE `email`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                if(result.getString("email").equals(email)){
                    return true;
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return false;
    }

    @Override
    public boolean signin_user(String email,String password) {
        try {

            String request="SELECT * FROM `users` WHERE `email`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                //result.getString("email").equals(password)
                if(BCrypt.checkpw(password, result.getString("password"))&&result.getBoolean("enabled")){
                    Session.setId(result.getInt("id"));
                    Session.setFirst_name(result.getString("First_name"));
                    Session.setLast_name(result.getString("Last_name"));
                    Session.setEmail(result.getString("email"));
                    Session.setUser_roles(result.getString("User_roles"));
                    return true;
                }
            }
        
        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return false;
    }

    @Override
    public void user_last_login() {

        try {
            String request="UPDATE `users` SET `last_login`=CURRENT_TIMESTAMP WHERE `id`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1, Session.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet getUser_info(int id) {
        try {

            String request="SELECT * FROM `users` WHERE `id`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);

        }
        return null;
    }

    @Override
    public boolean update_user_info(Users u) {
        try {
        String requete="UPDATE `users` SET `First_name`=?,`Last_name`=?,`Mobile`=?,`Tel`=?,`Address`=? WHERE `id`=?";
        PreparedStatement statement= connection.prepareStatement(requete);
        statement.setString(1,u.getFirst_name());
        statement.setString(2, u.getLast_name());
        statement.setInt(3, u.getMobile_number());
        statement.setInt(4, u.getTel_number());
        statement.setString(5, u.getAddress());
        statement.setInt(6, Session.getId());
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
        return false;
    }

    }

    @Override
    public boolean check_password(String password) {
        try {
            String request="SELECT DISTINCT `password` FROM `users` WHERE `id`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setInt(1,Session.getId());
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                if(BCrypt.checkpw(password, result.getString("password"))){
                    return true;
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return false;
    }

    @Override
    public boolean reset_user_password(Users u) {
        try {
            String requete="UPDATE `users` SET `password`=? WHERE `id`=?";
            PreparedStatement statement= connection.prepareStatement(requete);
            statement.setString(1,BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13)));
            statement.setInt(2, Session.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    @Override
    public ResultSet getUsers_info() {
        try {

            String request="SELECT * FROM `users` WHERE `User_roles`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,"Role_ADMIN");
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);

        }
        return null;
    }

    @Override
    public ResultSet getUser_infoByEmail(String email) {
        try {

            String request="SELECT * FROM `users` WHERE `email`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    @Override
    public boolean set_ProfileImage(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            String requete="UPDATE `users` SET `Profile_image`= ? WHERE `id`= ?";
            PreparedStatement statement= connection.prepareStatement(requete);
            statement.setBlob(1,inputStream);
            statement.setInt(2, Session.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | FileNotFoundException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    @Override
    public Image get_ProfileImage() {
        try {

            String request="SELECT `Profile_image` FROM `users` WHERE `id`= ?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setInt(1,Session.getId());
            ResultSet result = statement.executeQuery();
            result.next();
                String image =result.getString("Profile_image");
                Image img = new Image("/img/profil_image.png");
                if (image==null){
                    return img;
                }else {
                   // byte[] imageBytes = image.getBytes(1, (int) image.length());
                    // img = new Image(new ByteArrayInputStream(imageBytes));
                   // return img;
                }
                return img;

        } catch (SQLException e) {
            Logger.getLogger(UsersServices.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    @Override
    public boolean set_Requested_password(String email) {
        try {
            String request="UPDATE `users` SET `password_requested_at`=CURRENT_TIMESTAMP WHERE `email`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, email);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String set_confirmation_token(String email) {
        try {
            String token= MD5Hashing(email);
            String request="UPDATE `users` SET `confirmation_token`=? WHERE `email`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, token);
            statement.setString(2, email);
            statement.executeUpdate();
            return token;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
