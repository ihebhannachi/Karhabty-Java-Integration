/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;
import project.karhabty.entities.Carburant;
import project.karhabty.services.CarburantServices;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class AddFuelSheetController implements Initializable {

    @FXML
    private JFXTextField Voiture_TF;

    @FXML
    private JFXTextField DistanceParcourrue_TF;

    @FXML
    private JFXDatePicker DateDebut_DP;

    @FXML
    private JFXDatePicker DateFin_DP;
     @FXML
    private JFXTextField Montant_TF;
    @FXML
    private JFXButton Annuler_Button;
    @FXML
    private JFXButton Confirmer_Button;
    @FXML
    private Label Montant_Err;
    @FXML
    private Label Distance_Err;
    @FXML
    void Annuler(ActionEvent event) throws IOException {
        Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));       
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();
    }
    @FXML
    void AddFuelS(ActionEvent event) throws IOException {
        Carburant c=new Carburant();
        CarburantServices cs= new CarburantServices();
        c.setVoiture(Voiture_TF.getText());
        c.setDateDebut(DateDebut_DP.getValue());
        c.setDateFin(DateFin_DP.getValue());
        c.setDistanceParcourrue(Integer.parseInt(DistanceParcourrue_TF.getText()));
        c.setMontant(Integer.parseInt(Montant_TF.getText()));
        
      //  Alert a=new Alert(AlertType.WARNING);
       // a.setTitle("Nouvelle Fiche Carburant");
        
        cs.AddFuelSheet(c);

        Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));
        
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show(); 
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert Voiture_TF != null : "fx:id=\"Voiture_TF\" was not injected: check your FXML file 'ui_AddFuelSheet.fxml'.";
        assert DistanceParcourrue_TF != null : "fx:id=\"DistanceParcourrue_TF\" was not injected: check your FXML file 'ui_AddFuelSheet.fxml'.";
        assert DateDebut_DP != null : "fx:id=\"DateDebut_DP\" was not injected: check your FXML file 'ui_AddFuelSheet.fxml'.";
        assert DateFin_DP != null : "fx:id=\"DateFin_DP\" was not injected: check your FXML file 'ui_AddFuelSheet.fxml'.";
        assert Confirmer_Button != null : "fx:id=\"Confirmer_Button\" was not injected: check your FXML file 'ui_AddFuelSheet.fxml'.";
         DistanceParcourrue_TF.textProperty().addListener(e->{
                    ValidChampNumber(DistanceParcourrue_TF,Distance_Err);
        });
          Montant_TF.textProperty().addListener(e->{
                    ValidChampNumber(Montant_TF,Montant_Err);
        });
    } 
    
        public boolean ValidChampNumber(JFXTextField t,Label l)
    {
        l.setText("");
        Pattern pattern=Pattern.compile("[0-9]+");
          java.util.regex.Matcher matcher=pattern.matcher(t.getText());
        if(t.getText().isEmpty())
        {
            l.setText("Ce champs Doit etre remplis");
            return false;
        }
        else if(matcher.find() && matcher.group().equals(t.getText()))
        {
            return true;
        }
        else 
        {
            l.setText("Ce champs n'aacepte que des chifres");
            return false;
        }
    }
    public void setCarburant(String c)
    {
        Voiture_TF.setText(c);
    }
    
    
}


