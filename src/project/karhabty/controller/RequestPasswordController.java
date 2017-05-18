package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.services.UsersServices;
import project.karhabty.technical.Mail;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iheb on 13/02/2017.
 *
 */
public class RequestPasswordController {
    @FXML
    private JFXTextField Email;
    @FXML
    private Label errorlabel;

    public void handleButtonAction(ActionEvent event){
        JFXButton btn = (JFXButton) event.getSource();
        UsersIServices user = new UsersServices() ;
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Recevoir mon mot de passe par email" :
                if (!validateEmail()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText(null);
                    alert.setContentText("VERIFIER LES CHAMPS");
                    alert.showAndWait();
                }else{
                    if (user.check_email(Email.getText())) {
                        Mail mail = new Mail();
                        if (mail.passwordRequest(Email.getText())) {
                            Image img = new Image("/img/confirm.png");
                            Notifications notificationBuilder2 = Notifications.create()
                                    .title("Réinitialistion mot de passe")
                                    .text("Un Email a ete envoyer.\n consulter votre boite mail")
                                    .graphic(new ImageView(img))
                                    .hideAfter(Duration.seconds(10))
                                    .position(Pos.TOP_RIGHT);
                            notificationBuilder2.show();
                            user.set_Requested_password(Email.getText());
                        }
                    }else {
                        errorlabel.setText("(*) Adresse mail n'existe pas");
                    }
                }
                break;
            case "Retour":
                try {
                    Parent login_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_login.fxml"));
                    Scene login_scene = new Scene(login_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //app_stage.hide();
                    app_stage.setScene(login_scene);
                    app_stage.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
        }
    }
    private boolean validateEmail(){
        errorlabel.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9.]*@[a-zA-Z0-9.]+([.][a-zA-Z]+)+");
        Matcher matcher=pattern.matcher(Email.getText());
        if (Email.getText().isEmpty()){
            errorlabel.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(Email.getText())){
            return true;
        }else {
            errorlabel.setText("(*)Enter une adresse mail valide");
            return false;
        }
    }

}
