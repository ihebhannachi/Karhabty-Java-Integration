/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import project.karhabty.entities.Voiture;
import project.karhabty.services.EntretienServices;
import project.karhabty.services.VoituresServices;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class AddNewCarController implements Initializable {


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
    private JFXComboBox<String> Marque_CB;

    @FXML
    private JFXComboBox<String> Modele_CB;

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
    private Label PuissFisc_Err_Lab;

    @FXML
    private Label Kilom_Err_Lab;

    @FXML
    private Label ChevauxDyn_Err_Lab;

    @FXML
    private Label Erreur_Lab;
    @FXML
    private Label User_Lab;
    @FXML
    private JFXButton Ajout_Button;
     @FXML
    private JFXButton Annuler_Button;
      private final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-mm-yyyy");
      private LocalDate l; 
       private final Color ACTIVE_COLOUR = Color.GREEN;
    private final Color INACTIVE_COLOUR = Color.RED;
    public AddNewCarController() {
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert matricule_Lab != null : "fx:id=\"matricule_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Marque_Lab != null : "fx:id=\"Marque_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Modele_Lab != null : "fx:id=\"Modele_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert DateMisCirculation_Lab != null : "fx:id=\"DateMisCirculation_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert puissanceFis_Lab != null : "fx:id=\"puissanceFis_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Carburant_Lab != null : "fx:id=\"Carburant_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert chevauxDyn_Lab != null : "fx:id=\"chevauxDyn_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert transmission_Lab != null : "fx:id=\"transmission_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Marque_CB != null : "fx:id=\"Marque_CB\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Modele_CB != null : "fx:id=\"Modele_CB\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert matricle_TF != null : "fx:id=\"matricle_TF\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert puissanceFiscake_TF != null : "fx:id=\"puissanceFiscake_TF\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert chevauxDyn_TF != null : "fx:id=\"chevauxDyn_TF\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert DateMisCirc_DP != null : "fx:id=\"DateMisCirc_DP\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Carburant_CB != null : "fx:id=\"Carburant_CB\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert transmission_CB != null : "fx:id=\"transmission_TF\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert kilometrage_Lab != null : "fx:id=\"kilometrage_Lab\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert kilometrage_TF != null : "fx:id=\"kilometrage_TF\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        assert Ajout_Button != null : "fx:id=\"Ajout_Button\" was not injected: check your FXML file 'ui_AddNewCar.fxml'.";
        final ObservableList optionEnergie = FXCollections.observableArrayList(); 
       final ObservableList optionBoite = FXCollections.observableArrayList();
             final ObservableList optionM = FXCollections.observableArrayList();

        
       
        DateMisCirc_DP.setOnAction((ActionEvent event) ->{
             l=DateMisCirc_DP.getValue();
        });
        
        
        puissanceFiscake_TF.textProperty().addListener(e->{
                    ValidChampNumber(puissanceFiscake_TF,PuissFisc_Err_Lab);
        });
        chevauxDyn_TF.textProperty().addListener(e->{
                    ValidChampNumber(chevauxDyn_TF,ChevauxDyn_Err_Lab);
        });
         kilometrage_TF.textProperty().addListener(e->{
                    ValidChampNumber(kilometrage_TF,Kilom_Err_Lab);
        });
        chevauxDyn_TF.setOnAction((ActionEvent event) ->{
            try{
            Integer.parseInt(chevauxDyn_TF.getText());
            ChevauxDyn_Err_Lab.setText("valide");
           // PuissFisc_Err_Lab.setForeground(ACTIVE_COLOUR);
            }catch(NumberFormatException e){
            ChevauxDyn_Err_Lab.setText("Ce champ n'accepte que des chiffres");
            }
        });
        kilometrage_TF.setOnAction((ActionEvent event) ->{
            try{
            Integer.parseInt(kilometrage_TF.getText());
            Kilom_Err_Lab.setText("valide");
           // PuissFisc_Err_Lab.setForeground(ACTIVE_COLOUR);
            }catch(NumberFormatException e){
            PuissFisc_Err_Lab.setText("Ce champ n'accepte que des chiffres");
            }
        });
        
       User_Lab.setText("1");

       optionEnergie.add("Essence"); 
       optionEnergie.add("Diesel");
       Carburant_CB.setItems(optionEnergie);
       Carburant_CB.setEditable(true);
       optionBoite.add("Manuelle"); 
       optionBoite.add("Automatique");
       transmission_CB.setItems(optionBoite);
       transmission_CB.setEditable(true);
        VoituresServices vs= new VoituresServices();
          try {
              vs.GetMarques();
          } catch (SQLException ex) {
              Logger.getLogger(AddNewCarController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          Marque_CB.setItems(vs.GetList());
         
              this.Marque_CB.setEditable(true);
              this.Marque_CB.valueProperty().addListener(new ChangeListener<String>() {

                  @Override
                     public void changed(ObservableValue ov, String t1, String t2) {
                        Modele_CB.getSelectionModel().clearSelection();
                        Modele_CB.getItems().clear();

                         try {                          
                            vs.GetModels(t2);
                        } catch (SQLException ex) {
                        Logger.getLogger(AddNewCarController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                         try {
                            vs.GetModelByIds();
                        } catch (SQLException ex) {
                            Logger.getLogger(AddNewCarController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Modele_CB.setItems(vs.GetListModel());
                        Modele_CB.setEditable(true);
                    }                   
            });         
              
             // puissanceFiscake_TF.addKeyListener()
    }   
    
    public boolean Control()
    {
        try{
            Integer.parseInt(puissanceFiscake_TF.getText());
             return true;  
        }catch(NumberFormatException e){
            PuissFisc_Err_Lab.setText("Ce champ n'accepte que des chiffres");
            return false;   
        }
        
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
    @FXML
    public void Ajouter(javafx.event.ActionEvent event) throws IOException, SQLException
    {
        ValidChampNumber(puissanceFiscake_TF,PuissFisc_Err_Lab);
       if(matricle_TF.getText().isEmpty()|| Marque_CB.getSelectionModel().getSelectedItem().isEmpty()|| Modele_CB.getSelectionModel().getSelectedItem().isEmpty()||puissanceFiscake_TF.getText().isEmpty()||chevauxDyn_TF.getText().isEmpty()||Carburant_CB.getSelectionModel().getSelectedItem().isEmpty()||transmission_CB.getSelectionModel().getSelectedItem().isEmpty()||kilometrage_TF.getText().isEmpty())
       {
           Erreur_Lab.setText("Tous les champs doivent etre remplis!");
       }
       else
       {
        Voiture v=new Voiture();
        VoituresServices vs= new VoituresServices();
        EntretienServices es=new EntretienServices();
        v.setMatricule(matricle_TF.getText());
        v.setProprietaire(1);
        vs.GetModels(Marque_CB.getSelectionModel().getSelectedItem());
        vs.GetModeleP(Modele_CB.getSelectionModel().getSelectedItem());
        v.setMarque(vs.getIdMarque());
        v.setModele(vs.getIdModele());
        v.setDateMiseCirculation(l);
        v.setPuissanceFiscale(Integer.parseInt(puissanceFiscake_TF.getText()));
        v.setPuissanceDynamique(Integer.parseInt(chevauxDyn_TF.getText()));
        v.setCarburant(Carburant_CB.getSelectionModel().getSelectedItem());
        v.setTransmission(transmission_CB.getSelectionModel().getSelectedItem());
        v.setKilometrage(Integer.parseInt(kilometrage_TF.getText()));
        System.out.println(v);
        vs.addCar(v);
        vs.SendMail();
        es.addPieces(v.getMatricule()); 
        FXMLLoader  loader=new FXMLLoader();
                Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml").openStream());            
                MainGestionEntretiensController f= (MainGestionEntretiensController) loader.getController();
                f.setUser(User_Lab.getText());
                Scene Disp_scene = new Scene(DispEditDele_parent);
                Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                disp_stage.setScene(Disp_scene);
                disp_stage.show(); 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter Voiture");
        alert.setHeaderText(null);
        alert.setContentText("Votre Voiture à été ajoutée!");
        alert.show();
       }
        
        
       
}
    
    
    void setUser(String s)
    {
        User_Lab.setText(s);
    }
    
    
    @FXML
    void Annuler(ActionEvent event) throws IOException {
        FXMLLoader  loader=new FXMLLoader();
        Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml").openStream());            
        MainGestionEntretiensController f= (MainGestionEntretiensController) loader.getController();
        f.setUser(User_Lab.getText());
        Scene Disp_scene = new Scene(DispEditDele_parent);
        Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        disp_stage.setScene(Disp_scene);
        disp_stage.show(); 
    }


    
   
    
}
