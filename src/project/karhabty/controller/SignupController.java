package project.karhabty.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.entities.Users;
import project.karhabty.services.UsersServices;
import project.karhabty.technical.Mail;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iheb on 04/02/2017.
 *
 */
public class SignupController implements Initializable {
    @FXML
    private JFXTextField First_name;

    @FXML
    private JFXTextField Last_name;

    @FXML
    private JFXTextField Mobile_num;

    @FXML
    private JFXTextField Tel_num;

    @FXML
    private JFXTextField Address;

    @FXML
    private JFXTextField Email;

    @FXML
    private JFXPasswordField Password;

    @FXML
    private JFXPasswordField Confirm_password;
    @FXML
    private Label email_er_label;
    @FXML
    private Label mobile_er_label;

    @FXML
    private Label tel_er_label;

    @FXML
    private Label password_er_label;

    @FXML
    private Label ConfirmPassword_er_label;

    @FXML
    private Label lastname_er_label;

    @FXML
    private Label firstname_er_label;

    @FXML
    private Label address_er_label;

    public void Add_User(ActionEvent event){
        Users newuser = new Users();
        if (validatelastname() && validatefirstname() && validateEmail()&& validatepassword() && validateNumMobile() && validateNumTel() ) {
            newuser.setFirst_name(First_name.getText());
            newuser.setLast_name(Last_name.getText());
            newuser.setEmail(Email.getText());
            newuser.setPassword(Password.getText());
            newuser.setMobile_number(Integer.parseInt(Mobile_num.getText()));
            newuser.setTel_number(Integer.parseInt(Tel_num.getText()));
            newuser.setAddress(Address.getText());
            Image img = new Image("/img/confirm.png");
            UsersIServices user = new UsersServices();
            Mail mail = new Mail();
            if (!user.check_email(Email.getText())) {
                if (user.add_user(newuser)) {
                    System.out.println("userajouter");
                    if (mail.confirmationMail(Email.getText())) {
                        Notifications notificationBuilder = Notifications.create()
                                .title("bienvenue a karhabty")
                                .text("Votre compte a ete créé ")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(10))
                                .position(Pos.TOP_RIGHT);
                        notificationBuilder.show();
                        System.out.println("email envoyer");
                        Notifications notificationBuilder2 = Notifications.create()
                                .title("Email Confirmation")
                                .text("Un Email de confirmation a ete envoyer.\n consulter votre boite mail")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(10))
                                .position(Pos.TOP_RIGHT);
                        notificationBuilder2.show();
                        //redirect(event);
                    }
                }
            } else {
                email_er_label.setText("(*) Adresse mail existe");
                System.out.println("Email ex deja");
            }
        }

    }
    public void Back_to_login(ActionEvent event){
        try {
            Parent login_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_login.fxml"));
            Scene login_scene = new Scene(login_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(login_scene);
            app_stage.show();
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private boolean validateEmail(){
        email_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9.]*@[a-zA-Z0-9.]+([.][a-zA-Z]+)+");
        Matcher matcher=pattern.matcher(Email.getText());
        if (Email.getText().isEmpty()){
            email_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(Email.getText())){
            return true;
        }else {
            email_er_label.setText("(*)Enter une adresse mail valide");
            return false;
        }
    }
    private boolean validateNumMobile(){
        mobile_er_label.setText("");
        Pattern pattern=Pattern.compile("[0-9]+");
        Matcher matcher=pattern.matcher(Mobile_num.getText());
        if (Mobile_num.getText().isEmpty()){
            mobile_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(Mobile_num.getText())){
            return true;
        }else {
            mobile_er_label.setText("(*)Enter des chiffre");
            return false;
        }
    }
    private boolean validateNumTel(){
        tel_er_label.setText("");
        Pattern pattern=Pattern.compile("[0-9]+");
        Matcher matcher=pattern.matcher(Tel_num.getText());
        if (Tel_num.getText().isEmpty()){
            tel_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(Tel_num.getText())){
            return true;
        }else {
            tel_er_label.setText("(*)Enter des chiffre");
            return false;
        }
    }
    private boolean validatefirstname(){
        firstname_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(Last_name.getText());
        if (Last_name.getText().isEmpty()){
            firstname_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(Last_name.getText())){
            return true;
        }else {
            firstname_er_label.setText("(*)Enter des lettres");
            return false;
        }
    }
    private boolean validatelastname(){
        lastname_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(First_name.getText());
        if (First_name.getText().isEmpty()){
            lastname_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(First_name.getText())){
            return true;
        }else {
            lastname_er_label.setText("(*)Enter des lettres");
            return false;
        }
    }
    private boolean validatepassword(){
        password_er_label.setText("");
        if (Password.getText().isEmpty()){
            password_er_label.setText("(*)champ requis");
            return false;
        }else {
            return true;
        }

    }
    private void redirect(ActionEvent event){
        try {
            Parent logout_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_login.fxml"));
            Scene logout_scene = new Scene(logout_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(logout_scene);
            app_stage.show();
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                    /*Validate Confirmation password*/
        Confirm_password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Confirm_password.getText().equals(Password.getText())){
                ConfirmPassword_er_label.setTextFill(Color.GREEN);
                ConfirmPassword_er_label.setText("Mot de passe correspond");
            }else {
                ConfirmPassword_er_label.setText("Mot de passe ne correspond pas");
            }
        });
    }
}
