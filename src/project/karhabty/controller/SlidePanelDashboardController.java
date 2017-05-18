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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.services.CrudNotif;

/**
 * Created by iheb on 11/02/2017.
 */
public class SlidePanelDashboardController {
    public void handleButtonAction(ActionEvent event) throws SQLException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Déconnexion":
                Session.destroySession();
                CrudNotif notife = new CrudNotif(); 
                notife.setZero();
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
            case "Gérer les annonce":
                try {
                    Parent logout_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAdmin.fxml"));
                    Scene logout_scene = new Scene(logout_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide();
                    app_stage.setScene(logout_scene);
                    app_stage.show();
                    CrudNotif notif = new CrudNotif(); 
                System.out.println(notif.RecupNbrAnnonce());
                if (notif.RecupNbrAnnonce()!=0) {
                    Notifications notifannonce = Notifications.create()
                       .title("Vous avez des nouveaux annonce ")
                       .text(notif.RecupNbrAnnonce()+" Annonce(s) ajouté")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                    notifannonce.darkStyle(); 
               notifannonce.showConfirm();
                }
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                break; 
        }
    }
}
