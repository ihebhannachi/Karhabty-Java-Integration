/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Alert;
import project.karhabty.services.ComboboxValue;
import project.karhabty.services.CrudAlertServices;
import project.karhabty.technical.Session;
import org.controlsfx.control.Notifications;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentAlertController implements Initializable {
@FXML 
private JFXComboBox MarqueCombobox; 
@FXML 
private JFXComboBox ModelCombobox;
@FXML 
public void ValideButton(ActionEvent event) throws IOException {
    Alert al = new Alert(); 
    al.setMarque(this.MarqueCombobox.getSelectionModel().getSelectedItem().toString());
    al.setModele(this.ModelCombobox.getSelectionModel().getSelectedItem().toString());
    al.setUtilisateur_idUtilisateur(Session.getId());
    CrudAlertServices crud = new CrudAlertServices();
    crud.AddAlert(al);
    Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("Ajout éffectué")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT);                        
               not.darkStyle(); 
               not.showConfirm();
}
@FXML 
public void backButton(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboboxValue v = new ComboboxValue();
         try {
             v.GetMarque();
         } catch (SQLException ex) {
             Logger.getLogger(FXMLDocumentAlertController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.MarqueCombobox.setItems(v.GetList());
        this.MarqueCombobox.setEditable(true);
        this.MarqueCombobox.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {             
            try {
                ModelCombobox.getSelectionModel().clearSelection();
                ModelCombobox.getItems().clear();
                v.GetModel(t1);
                v.GetModelById();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentAlertController.class.getName()).log(Level.SEVERE, null, ex);
            }                                                 
        }    
    });     
        this.ModelCombobox.setItems(v.GetListModel());
        this.ModelCombobox.setEditable(true);
        // TODO
    }    
    
}
