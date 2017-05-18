/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.karhabty.entities.Carburant;
import project.karhabty.services.CarburantServices;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class displayFuelSheetsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableView<Carburant> Carburant_TAB;

    @FXML
    private TableColumn<Carburant, Date> DateDebut;

    @FXML
    private TableColumn<Carburant, Date> DateFin;

    @FXML
    private TableColumn<Carburant, Double> Montant;
    
    @FXML
    private TableColumn<Carburant,Integer> DistanceParcourrue;

    @FXML
    private JFXButton NewFuelSheet_Button;
    
    @FXML
    private Label User_Lab;

    @FXML
    private JFXTextField Voiture_TF;
    
    private String s ;
 

       public static String Carb;
    @FXML
    private JFXButton Afficher_Button;
    @FXML
    private JFXButton Annuler_Button;

    
  
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        Carburant c=new Carburant();
        //Voiture_TF.setText(DisplayEditDeleteCarController.Carb);
             CarburantServices cs = new CarburantServices(); 
      try {
                 cs.DisplayFuelSheets(Carb);
      } catch (SQLException ex) {
           ex.printStackTrace();
       
        }
     // String voiture, LocalDate dateDebut, LocalDate dateFin, double montant, int distanceParcourrue
      DateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
      DateDebut.setMinWidth(Carburant_TAB.getMaxWidth()+1000/5);
      DateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
      DateFin.setMinWidth(Carburant_TAB.getMaxWidth()+1000/5);
      DistanceParcourrue.setCellValueFactory(new PropertyValueFactory<>("distanceParcourrue"));
      DistanceParcourrue.setMinWidth(Carburant_TAB.getMaxWidth()+1000/5);
      Montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
      Montant.setMinWidth(Carburant_TAB.getMaxWidth()+1000/5);
      
      Carburant_TAB.setItems(cs.getObs()); 
      Carburant_TAB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Carburant_TAB.getSelectionModel().getSelectedItem() != null) 
        {     
           Carburant c = Carburant_TAB.getSelectionModel().getSelectedItem(); 
         }
         }          
     });
    
    }    

        @FXML
    void AddFuels(ActionEvent event) {
try {
                FXMLLoader  loader=new FXMLLoader();
                Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_AddFuelSheet.fxml").openStream());            
                Carburant c=new Carburant();
                c.setVoiture(Voiture_TF.getText());
                AddFuelSheetController f= (AddFuelSheetController) loader.getController();
                f.setCarburant(c.getVoiture());
                
                Scene Disp_scene = new Scene(DispEditDele_parent);
                Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                disp_stage.setScene(Disp_scene);
                disp_stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
     public void setCarburant(String c)
    {
      //Voiture_TF.setText(c);
      s=c; 
    }

    @FXML
    private void Display(ActionEvent event) {
        CarburantServices cs = new CarburantServices(); 
      try {
          Voiture_TF.setText(Carb);
                 cs.DisplayFuelSheets(Carb);
      } catch (SQLException ex) {
           ex.printStackTrace();
       
        }
        Carburant_TAB.setItems(cs.getObs());
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
         Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));       
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();
    }

    
    
    
}
