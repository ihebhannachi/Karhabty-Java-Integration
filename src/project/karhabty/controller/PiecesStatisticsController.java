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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.karhabty.entities.Entretien;
import project.karhabty.services.EntretienServices;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class PiecesStatisticsController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    private NumberAxis y;

    @FXML
    private CategoryAxis x;

    @FXML
    private JFXTextField Voiture_TF;

    @FXML
    private Label User_Lab;
    @FXML
    private JFXButton Annuler_Button;
    public static String voiture;
    @FXML
    private JFXButton Afficher;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert BarChart != null : "fx:id=\"BarChart\" was not injected: check your FXML file 'ui_PiecesStatistics.fxml'.";
        assert y != null : "fx:id=\"y\" was not injected: check your FXML file 'ui_PiecesStatistics.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'ui_PiecesStatistics.fxml'.";
        assert Voiture_TF != null : "fx:id=\"Voiture_TF\" was not injected: check your FXML file 'ui_PiecesStatistics.fxml'.";
        assert User_Lab != null : "fx:id=\"User_Lab\" was not injected: check your FXML file 'ui_PiecesStatistics.fxml'.";
                      Entretien e1=new Entretien();
  
       

         }
        
    
    public void setVoiture(String c) 
    {
        voiture=c;
        Voiture_TF.setText(voiture);
    }
    
    public void setUser(String c)
    {
        User_Lab.setText(c);
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
         Parent addNewCar_parent = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_MainGestionEntretiens.fxml"));       
        Scene signup_scene = new Scene(addNewCar_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signup_scene);
        app_stage.show();
    }

    @FXML
    private void Afficher(ActionEvent event) {
     Entretien e1=new Entretien();
        //e.setVoiture(Voiture_TF.getText());

        e1.setVoiture( Voiture_TF.getText());   
        System.out.println(e1.getVoiture());
        EntretienServices es= new EntretienServices()  ;
         try {
             es.DisplayDureeVie(e1);
         } catch (SQLException ex) {
             Logger.getLogger(PiecesStatisticsController.class.getName()).log(Level.SEVERE, null, ex);
         }
                 XYChart.Series set1=new XYChart.Series<>();

                   // System.out.println(es.getDuree().get(0)+" "+es.getDuree().get(2)+" "+es.getDuree().get(3));
                    set1.getData().add(new XYChart.Data("Batterie",es.getDuree().get(0)));
                    set1.getData().add(new XYChart.Data("courroie d alternance",es.getDuree().get(1)));
                    set1.getData().add(new XYChart.Data("courroie de distribution",es.getDuree().get(2)));
                    set1.getData().add(new XYChart.Data("huile de moteur",es.getDuree().get(3)));
                    set1.getData().add(new XYChart.Data("plaquette de frein",es.getDuree().get(4)));
                    set1.getData().add(new XYChart.Data("pneus",es.getDuree().get(5)));
                   BarChart.getData().addAll(set1);
    }
    
}
