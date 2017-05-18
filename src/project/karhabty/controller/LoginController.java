package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.services.UsersServices;
import project.karhabty.technical.Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 04/02/2017.
 *
 */
public class LoginController {
    @FXML
    private JFXTextField email_login;

    @FXML
    private JFXPasswordField password_login;

    @FXML
    private Label login_errorlabel;
    public void handleButtonAction(ActionEvent event){
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Inscrivez-vous":

                try {
                    Parent signup_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_signup.fxml"));
                Scene signup_scene = new Scene(signup_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(signup_scene);
                app_stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "Connectez-vous":
                UsersIServices user = new UsersServices() ;
                if(user.signin_user(email_login.getText(),password_login.getText())){
                    user.user_last_login();
                    System.out.println(Session.getUser_roles());                 //   System.out.println(Session.getFirst_name()+""+Session.getLast_name());
                   if (Session.getUser_roles().equals("Role_ADMIN")){
                        try {
                            Parent user_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_useraccount.fxml"));
                            Scene user_scene = new Scene(user_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            app_stage.hide();
                            app_stage.setScene(user_scene);
                            app_stage.show();
                        } catch (IOException e) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    if (Session.getUser_roles().equals("ROLE_SUPERADMIN")){
                        try {
                            Parent admin_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_admindashboard.fxml"));
                            Scene admin_scene = new Scene(admin_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            app_stage.hide();
                            app_stage.setScene(admin_scene);
                            app_stage.show();
                        } catch (IOException e) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                } else {
                    this.login_errorlabel.setText("(*) Adresse mail ou Password incorrect");
                    System.out.println("errror");
                }
                break;
            case "Mot de passe oublié ?":
                try {
                    Parent admin_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_requestpassword.fxml"));
                    Scene admin_scene = new Scene(admin_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(admin_scene);
                    app_stage.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
        }

    }

}
