package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.karhabty.technical.Session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.GUI.LoadScene;

/**
 * Created by iheb on 05/02/2017.
 */
public class SlidePanelController {
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException{
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Mon Compte":
                try {
                    Parent user_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_useraccount.fxml"));
                    Scene user_scene = new Scene(user_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(user_scene);
                    app_stage.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "Settings":
                try {
                    Parent settings_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_useraccountsettings.fxml"));
                    Scene settings_scene = new Scene(settings_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(settings_scene);
                    app_stage.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "Déconnexion" :
                Session.destroySession();
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
                break;
            case "Espace annonce" :
               Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
               //LoadScene log = new LoadScene(scene); 
              // log.DisplayScene("Application.css");
               Notifications not = Notifications.create()
                       .title("Espace annonce ")
                       .text("Bienvenu "+Session.getFirst_name())
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showConfirm();
                break;
            case "Espace mes Voitures":
                try {
                    Parent user_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));
                    Scene user_scene = new Scene(user_parent);
                    Stage app_stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage1.setScene(user_scene);
                    app_stage1.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
                case "Code de la route":
                try {
                    System.out.println("rchoudaaaaa");
                    Parent user_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/karhabtycode.fxml"));
                    Scene user_scene = new Scene(user_parent);
                    Stage app_stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage1.setScene(user_scene);
                    app_stage1.show();
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
        }

    }

    @FXML
    private void aaaaaaaaaaa(ActionEvent event) {
        try {
            //System.out.println("rchoudaaaaa");
            Parent user_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/frontOffice.fxml"));
            Scene user_scene = new Scene(user_parent);
            Stage app_stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage1.setScene(user_scene);
            app_stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SlidePanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
