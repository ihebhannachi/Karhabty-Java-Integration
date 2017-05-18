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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.karhabty.entities.Entretien;
import project.karhabty.services.EntretienServices;

/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class DisplayServiceBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField Batterie_TF;

    @FXML
    private JFXTextField courroieDalternance_TF;

    @FXML
    private JFXTextField courroieDeDist_TF;

    @FXML
    private JFXTextField HuileMoteur_TF;

    @FXML
    private JFXTextField PlaquettesFrein_TF;

    @FXML
    private JFXTextField Pneus_TF;

    @FXML
    private JFXButton Update_Button;

    @FXML
    private JFXButton Clear_Button;

    @FXML
    private Label Voiture_Lab;
    
    @FXML
    private Label User_Lab;
      @FXML
    private JFXButton Stats_Button;

    @FXML
    private JFXButton Annuler_Button;
    @FXML
    private JFXButton Afficher;
    @FXML
    void Stats(ActionEvent event) {
try {  
               
                
                
                
                FXMLLoader  loader=new FXMLLoader();
                Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_PiecesStatistics.fxml").openStream());            
                PiecesStatisticsController f= (PiecesStatisticsController) loader.getController();
                loader.getNamespace().put(Voiture_Lab.getText(), "External Text");
                f.setUser(User_Lab.getText());
                f.setVoiture(Voiture_Lab.getText());
                Scene Disp_scene = new Scene(DispEditDele_parent);
                Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                disp_stage.setScene(Disp_scene);
                disp_stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    @FXML
    void Annuler(ActionEvent event) {

    }

    @FXML
    void Clear_carnet(ActionEvent event) {
        Batterie_TF.setText(Integer.toString(0)); 
        courroieDalternance_TF.setText(Integer.toString(0));
        courroieDeDist_TF.setText(Integer.toString(0));
        HuileMoteur_TF.setText(Integer.toString(0));
        PlaquettesFrein_TF.setText(Integer.toString(0));
        Pneus_TF.setText(Integer.toString(0));
    }

    @FXML
    void UpdateServiceBook(ActionEvent event) throws SQLException, IOException {
       Entretien e=new Entretien(Integer.parseInt(Batterie_TF.getText()),Integer.parseInt(courroieDalternance_TF.getText()),Integer.parseInt(courroieDeDist_TF.getText()),Integer.parseInt(HuileMoteur_TF.getText()),Integer.parseInt(PlaquettesFrein_TF.getText()),Integer.parseInt(Pneus_TF.getText()));
       e.setVoiture(Voiture_Lab.getText());
        System.err.println(Voiture_Lab.getText());
       EntretienServices es=new EntretienServices();
        if(Batterie_TF.getText().isEmpty() ||courroieDalternance_TF.getText().isEmpty() ||courroieDeDist_TF.getText().isEmpty() ||HuileMoteur_TF.getText().isEmpty() ||PlaquettesFrein_TF.getText().isEmpty() ||Pneus_TF.getText().isEmpty() )
        {
            
        }
        else
        {
            System.out.println(e);
            es.UpdateEntretien(e);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setVoiture(String voiture)
    {
        this.Voiture_Lab.setText(voiture);
    }

    @FXML
    private void Afficher(ActionEvent event) {
        
        Entretien e=new Entretien();
        EntretienServices es=new EntretienServices();
         try {
            es.DisplayEntretien(Voiture_Lab.getText());
        } catch (SQLException ex) {
            Logger.getLogger(DisplayServiceBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Batterie_TF.setText(Integer.toString(es.getObs().get(0).getP1())); 
        courroieDalternance_TF.setText(Integer.toString(es.getObs().get(0).getP2()));
        courroieDeDist_TF.setText(Integer.toString(es.getObs().get(0).getP3()));
        HuileMoteur_TF.setText(Integer.toString(es.getObs().get(0).getP4()));
        PlaquettesFrein_TF.setText(Integer.toString(es.getObs().get(0).getP5()));
        Pneus_TF.setText(Integer.toString(es.getObs().get(0).getP6()));
       // System.out.println(es.getObs().get(0).getP1()+" "+es.getObs().get(0).getP2()+" "+es.getObs().get(0).getP3()+" "+es.getObs().get(0).getP4()+" "+es.getObs().get(0).getP5());
    }
}
