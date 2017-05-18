/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;

/**
 *
 * @author PC NET
 */
import javafx.scene.image.Image;
import project.karhabty.entities.Users;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

/**
 * Created by iheb on 05/02/2017.
 */
public interface UsersIServices {
    boolean add_user(Users u);
    String MD5Hashing(String password) throws NoSuchAlgorithmException;
    boolean check_email(String email);
    boolean signin_user(String email,String password);
    void  user_last_login();
    ResultSet getUser_info(int id);
    boolean update_user_info(Users u);
    boolean check_password(String password);
    boolean reset_user_password(Users u);
    ResultSet getUsers_info();
    ResultSet getUser_infoByEmail(String email);
    boolean set_ProfileImage(File file);
    Image get_ProfileImage();
    boolean set_Requested_password(String email);
    String set_confirmation_token(String email);
}