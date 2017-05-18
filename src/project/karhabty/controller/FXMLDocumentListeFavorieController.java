/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Annonce;
import project.karhabty.entities.Favorie;
import project.karhabty.services.CrudFavorieService;
import org.controlsfx.control.Notifications;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentListeFavorieController implements Initializable {

    @FXML 
    private TableView<Favorie> Table;
    @FXML
    private TableColumn<Favorie, String> Marque;
    @FXML 
    private TableColumn<Favorie,String> Description;
    @FXML 
    private TableColumn<Favorie, Double> Prix;
    private static int id; 
    /**
     * Initializes the controller class.
     */
    @FXML
    public void ButtonBack(ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML 
    public void ButtonDelete(ActionEvent event) {
        CrudFavorieService crud = new CrudFavorieService(); 
        crud.DeleteFavorie(id);
         Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("favorie supprimer")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showConfirm();
               try {
            crud.Display();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentListeFavorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Marque.setCellValueFactory(new PropertyValueFactory<>("Model"));
         Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
         Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
         Table.setItems(crud.getObs());
         Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Table.getSelectionModel().getSelectedItem() != null) 
        {     
           Favorie f = Table.getSelectionModel().getSelectedItem(); 
           FXMLDocumentListeFavorieController.id = f.getIdFavorie(); 
         }
         }          
     });            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudFavorieService crud  = new CrudFavorieService(); 
        try {
            crud.Display();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentListeFavorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Marque.setCellValueFactory(new PropertyValueFactory<>("Model"));
         Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
         Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
         Table.setItems(crud.getObs());
         Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Table.getSelectionModel().getSelectedItem() != null) 
        {     
           Favorie f = Table.getSelectionModel().getSelectedItem(); 
           FXMLDocumentListeFavorieController.id = f.getIdFavorie(); 
         }
         }          
     });
    }    
    
}

