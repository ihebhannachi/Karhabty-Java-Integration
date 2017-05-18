/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.karhabty.entities.Carburant;
import project.karhabty.entities.Entretien;
import project.karhabty.entities.Voiture;
import project.karhabty.services.VoituresServices;
import project.karhabty.technical.Session;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class DisplayEditDeleteCarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane DisplayEditDeleteAnchorPane;

    @FXML
    private Label matricule_Lab;

    @FXML
    private Label Marque_Lab;

    @FXML
    private Label Modele_Lab;

    @FXML
    private Label DateMisCirculation_Lab;

    @FXML
    private Label puissanceFis_Lab;

    @FXML
    private Label Carburant_Lab;

    @FXML
    private Label chevauxDyn_Lab;

    @FXML
    private Label transmission_Lab;

    @FXML
    private JFXTextField matricle_TF;

    @FXML
    private JFXTextField puissanceFiscake_TF;

    @FXML
    private JFXTextField chevauxDyn_TF;

    @FXML
    private JFXDatePicker DateMisCirc_DP;

    @FXML
    private JFXComboBox<String> Carburant_CB;

    @FXML
    private JFXComboBox<String> transmission_CB;

    @FXML
    private Label kilometrage_Lab;

    @FXML
    private JFXTextField kilometrage_TF;

    @FXML
    private JFXButton clear_Button;

    @FXML
    private JFXButton Delete_Button;

    @FXML
    private JFXTextField Marque_TF;

    @FXML
    private JFXTextField Modele_TF;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
    private LocalDate l;

    @FXML
    private JFXButton Entretien_Button;

    @FXML
    private JFXButton carburant_Button;

    @FXML
    private JFXButton Annuler_Button;

    @FXML
    private Label User_Lab;
    @FXML
    private JFXButton update_Button;
    @FXML
    private Label PuissaneFiscale_Err;
    @FXML
    private Label ChevauxDyn_Err;
    @FXML
    private Label Kilometrage_Err;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert DisplayEditDeleteAnchorPane != null : "fx:id=\"DisplayEditDeleteAnchorPane\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert matricule_Lab != null : "fx:id=\"matricule_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Marque_Lab != null : "fx:id=\"Marque_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Modele_Lab != null : "fx:id=\"Modele_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert DateMisCirculation_Lab != null : "fx:id=\"DateMisCirculation_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert puissanceFis_Lab != null : "fx:id=\"puissanceFis_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Carburant_Lab != null : "fx:id=\"Carburant_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert chevauxDyn_Lab != null : "fx:id=\"chevauxDyn_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert transmission_Lab != null : "fx:id=\"transmission_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert matricle_TF != null : "fx:id=\"matricle_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert puissanceFiscake_TF != null : "fx:id=\"puissanceFiscake_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert chevauxDyn_TF != null : "fx:id=\"chevauxDyn_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert DateMisCirc_DP != null : "fx:id=\"DateMisCirc_DP\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Carburant_CB != null : "fx:id=\"Carburant_CB\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert transmission_CB != null : "fx:id=\"transmission_CB\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert kilometrage_Lab != null : "fx:id=\"kilometrage_Lab\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert kilometrage_TF != null : "fx:id=\"kilometrage_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert clear_Button != null : "fx:id=\"clear_Button\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Delete_Button != null : "fx:id=\"Delete_Button\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Marque_TF != null : "fx:id=\"Marque_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        assert Modele_TF != null : "fx:id=\"Modele_TF\" was not injected: check your FXML file 'ui_DisplayEditDeleteCar.fxml'.";
        final ObservableList optionEnergie = FXCollections.observableArrayList();
        final ObservableList optionBoite = FXCollections.observableArrayList();
        DateMisCirc_DP.setOnAction((ActionEvent event) -> {
            l = DateMisCirc_DP.getValue();
        });

        optionEnergie.add("Essence");
        optionEnergie.add("Diesel");
        Carburant_CB.setItems(optionEnergie);
        Carburant_CB.setEditable(true);
        optionBoite.add("Manuelle");
        optionBoite.add("Automatique");
        transmission_CB.setItems(optionBoite);
        transmission_CB.setEditable(true);

        puissanceFiscake_TF.textProperty().addListener(e -> {
            ValidChampNumber(puissanceFiscake_TF, PuissaneFiscale_Err);
        });
        chevauxDyn_TF.textProperty().addListener(e -> {
            ValidChampNumber(chevauxDyn_TF, ChevauxDyn_Err);
        });
        kilometrage_TF.textProperty().addListener(e -> {
            ValidChampNumber(kilometrage_TF, Kilometrage_Err);
        });
    }

    public boolean ValidChampNumber(JFXTextField t, Label l) {
        l.setText("");
        Pattern pattern = Pattern.compile("[0-9]+");
        java.util.regex.Matcher matcher = pattern.matcher(t.getText());
        if (t.getText().isEmpty()) {
            l.setText("Ce champs Doit etre remplis");
            return false;
        } else if (matcher.find() && matcher.group().equals(t.getText())) {
            return true;
        } else {
            l.setText("Ce champs n'aacepte que des chifres");
            return false;
        }
    }

    @FXML
    void AddFuel(ActionEvent event) {
        /* */
        try {
            Carburant c = new Carburant();
            c.setVoiture(matricle_TF.getText());
            FXMLLoader loader = new FXMLLoader();
            AnchorPane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_displayFuelSheets.fxml").openStream());
            displayFuelSheetsController f = (displayFuelSheetsController) loader.getController();
            f.setCarburant(c.getVoiture());
            displayFuelSheetsController.Carb = c.getVoiture();
            System.err.println("Matricule " + displayFuelSheetsController.Carb);

            Scene Disp_scene = new Scene(DispEditDele_parent);
            Stage disp_stage = (Stage) (carburant_Button.getParent().getScene().getWindow());
            disp_stage.setScene(Disp_scene);
            disp_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void Reset(ActionEvent event) {
        Voiture v = new Voiture();
        matricle_TF.setText(v.getMatricule());
        Marque_TF.setText(v.getMarqueS());
        Modele_TF.setText(v.getModeleS());
        v.setDateMiseCirculation(l);
        DateMisCirc_DP.setValue(v.getDateMiseCirculation());
        puissanceFiscake_TF.setText(Integer.toString(v.getPuissanceFiscale()));
        chevauxDyn_TF.setText(Integer.toString(v.getPuissanceDynamique()));
        kilometrage_TF.setText(Integer.toString(v.getKilometrage()));
    }

    /**
     *
     * @param event
     */
    @FXML
    public void Delete_Car(javafx.event.ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression d'une voiture");
        alert.setHeaderText("Supprimer la voiture "+matricle_TF.getText()+" ?");
        alert.setContentText("Etes vous sur de vouloir supprimer cette  voiture?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        
        Voiture v = new Voiture();
        v.setMatricule(matricle_TF.getText());
        Marque_TF.setText(v.getMarqueS());
        Modele_TF.setText(v.getModeleS());
        v.setDateMiseCirculation(l);
        DateMisCirc_DP.setValue(v.getDateMiseCirculation());
        puissanceFiscake_TF.setText(Integer.toString(v.getPuissanceFiscale()));
        chevauxDyn_TF.setText(Integer.toString(v.getPuissanceDynamique()));
        kilometrage_TF.setText(Integer.toString(v.getKilometrage()));
        System.out.println(v.getMatricule());
        VoituresServices vs = new VoituresServices();
        vs.deleteCar(v);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();
    }

    @FXML
    public void update_Car(javafx.event.ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
if(matricle_TF.getText().isEmpty()||puissanceFiscake_TF.getText().isEmpty()||chevauxDyn_TF.getText().isEmpty()||Carburant_CB.getSelectionModel().getSelectedItem().isEmpty()||transmission_CB.getSelectionModel().getSelectedItem().isEmpty()||kilometrage_TF.getText().isEmpty())
       {            alert.setTitle("Modification d'une Voiture");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent etre remplis!");
            alert.show();
        } else {
            Voiture v = new Voiture();
            VoituresServices vs = new VoituresServices();
            v.setMatricule(matricle_TF.getText());
            v.setProprietaire(Session.getId());
            vs.GetModels(Marque_TF.getText());
            vs.GetModeleP(Modele_TF.getText());
            v.setMarque(vs.getIdMarque());
            v.setModele(vs.getIdModele());
            v.setDateMiseCirculation(l);
            v.setPuissanceFiscale(Integer.parseInt(puissanceFiscake_TF.getText()));
            v.setPuissanceDynamique(Integer.parseInt(chevauxDyn_TF.getText()));
            v.setCarburant(Carburant_CB.getSelectionModel().getSelectedItem());
            v.setTransmission(transmission_CB.getSelectionModel().getSelectedItem());
            v.setKilometrage(Integer.parseInt(kilometrage_TF.getText()));
            this.getVoiture(v);
            vs.updateCar(v);
            System.out.println(v);
            alert.setTitle("Modification d'une Voiture");
            alert.setHeaderText(null);
            alert.setContentText("les données de votre voiture ont ete modifié avec succés!");
            alert.show();
        }
        Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();

    }

    @FXML
    void Entretiens(ActionEvent event) throws IOException {
        try {
            Entretien e = new Entretien();
            e.setVoiture(matricle_TF.getText());
            FXMLLoader loader = new FXMLLoader();
            Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_DisplayServiceBook.fxml").openStream());
            DisplayServiceBookController f = (DisplayServiceBookController) loader.getController();
            f.setVoiture(e.getVoiture());
            Scene Disp_scene = new Scene(DispEditDele_parent);
            Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            disp_stage.setScene(Disp_scene);
            disp_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Annuler(ActionEvent event) throws IOException {
        Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/projet/karhabty/GUI/ui_MainGestionEntretiens.fxml"));
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();
    }

    public void getVoiture(Voiture v) {
        matricle_TF.setText(v.getMatricule());
        Marque_TF.setText(v.getMarqueS());
        Modele_TF.setText(v.getModeleS());
        v.setDateMiseCirculation(l);
        DateMisCirc_DP.setValue(v.getDateMiseCirculation());
        puissanceFiscake_TF.setText(Integer.toString(v.getPuissanceFiscale()));
        chevauxDyn_TF.setText(Integer.toString(v.getPuissanceDynamique()));
        kilometrage_TF.setText(Integer.toString(v.getKilometrage()));
    }

    public void setUser(String s) {
        User_Lab.setText(s);
    }

}
