/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.karhabty.GUI.LoadScene;
import project.karhabty.technical.Session;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentAccueilController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label Idname;
    @FXML 
    public void logout(ActionEvent event) throws IOException {
        Session.destroySession();
        LoadScene.HideStage();
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/javafxapplicationkarhabty/FXMLDocument.fxml"));
        Scene scene = new Scene(rootLogin);
        LoadScene log = new LoadScene(scene);
        log.DisplayScene("Application.css");
    }
    @FXML 
    public void ButtonAlert(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAlert.fxml"));
        Scene scene = new Scene(rootLogin);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML 
    public void ButtonAllAnnonce(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentListAnnonce.fxml"));
        Scene scene = new Scene(rootLogin);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML 
    public void ButtonFavorie(ActionEvent event) throws IOException {
      Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentListeFavorie.fxml"));
        Scene scene = new Scene(rootLogin);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML 
    public void ButtonAdd(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAdd.fxml"));
        Scene scene = new Scene(rootLogin);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
       // LoadScene log = new LoadScene(scene);
       // log.DisplayScene("Application.css");
    }
    @FXML 
    public void ButtonMyAnnonce(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentMyAnnonce.fxml"));
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
        // TODO
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_slidepanelcontents.fxml"));
            drawer.setSidePane(box);
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
                transition.setRate(transition.getRate()*-1);
                transition.play();

                if(drawer.isShown())
                {
                    drawer.close();
                }else
                    drawer.open();
            });
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }
        Idname.setText("Bonjour "+Session.getFirst_name());
        
    }    
    
}