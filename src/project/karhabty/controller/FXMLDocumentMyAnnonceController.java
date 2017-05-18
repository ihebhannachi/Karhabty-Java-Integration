/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Annonce;
import project.karhabty.services.CrudAnnonceService; 
/**
 *
 * @author PC NET
 */
public class FXMLDocumentMyAnnonceController implements Initializable {
    @FXML 
    private JFXComboBox Tree;
    @FXML 
    private TableView<Annonce> Table;
    @FXML
    private TableColumn<Annonce, String> Marque;
    @FXML 
    private TableColumn<Annonce,String> Model;
    @FXML 
    private TableColumn<Annonce, Integer> Kilo;
    @FXML 
    private TableColumn<Annonce,Double> Prix;
    @FXML 
    private TableColumn<Annonce,String> Desc;
    @FXML 
    private TableColumn<Annonce,Date> DateAnn;
    @FXML 
    private TableColumn<Annonce,String> Datecircul;
    @FXML 
    private TableColumn<Annonce,String> Energie;
    @FXML 
    private TableColumn<Annonce,String> Puissance;
    @FXML 
    private TableColumn<Annonce,String> Couleur;
    @FXML 
    private TableColumn<Annonce,String> Boite;
    @FXML 
    private JFXTextField Search;
    private static int id=0; 
    @FXML 
    public void BackButton (ActionEvent event) throws IOException {
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML 
    public void RecupAnnonce(ActionEvent event) throws SQLException, IOException {
        CrudAnnonceService crud = new CrudAnnonceService(); 
        crud.SearchAnnonce(id);
Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentModify.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
    }
    @FXML
    public void AffichageImg (ActionEvent event) throws SQLException {
     CrudAnnonceService crud = new CrudAnnonceService(); 
     Stage primaryStage = new Stage(); 
     primaryStage.setTitle("Photo de voiture");
     StackPane layout = new StackPane();
     layout.getChildren().add(crud.SelectImage(id));
     Scene scene = new Scene(layout,500,500);
     primaryStage.setScene(scene);
     primaryStage.show();
    }
    @FXML
    public void DeleteButton(ActionEvent event) {
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation ");
alert.setHeaderText("Confirmation de suppression !!!");
alert.setContentText("Vous etes sure ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
        CrudAnnonceService crud = new CrudAnnonceService(); 
        crud.DeleteMyAnnonce(id);
        try {
            crud.DisplayMyAnnonce();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentMyAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
      Model.setCellValueFactory(new PropertyValueFactory<>("Modele"));
      Kilo.setCellValueFactory(new PropertyValueFactory<>("Kilo"));
      Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
      DateAnn.setCellValueFactory(new PropertyValueFactory<>("DateAnnonce"));
      Datecircul.setCellValueFactory(new PropertyValueFactory<>("DateCirculation"));
      Energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
      Puissance.setCellValueFactory(new PropertyValueFactory<>("PuissanceFiscale"));
      Couleur.setCellValueFactory(new PropertyValueFactory<>("Couleur"));
      Boite.setCellValueFactory(new PropertyValueFactory<>("Boite"));      
      Table.setItems(crud.getObs()); 
      Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Table.getSelectionModel().getSelectedItem() != null) 
        {     
           Annonce a = Table.getSelectionModel().getSelectedItem(); 
           FXMLDocumentMyAnnonceController.id = a.getIdAnnonce(); 
         }
         }          
     });       
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudAnnonceService crud = new CrudAnnonceService(); 
        try {
            crud.DisplayMyAnnonce();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentMyAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
      Model.setCellValueFactory(new PropertyValueFactory<>("Modele"));
      Kilo.setCellValueFactory(new PropertyValueFactory<>("Kilo"));
      Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
      DateAnn.setCellValueFactory(new PropertyValueFactory<>("DateAnnonce"));
      Datecircul.setCellValueFactory(new PropertyValueFactory<>("DateCirculation"));
      Energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
      Puissance.setCellValueFactory(new PropertyValueFactory<>("PuissanceFiscale"));
      Couleur.setCellValueFactory(new PropertyValueFactory<>("Couleur"));
      Boite.setCellValueFactory(new PropertyValueFactory<>("Boite"));      
      Table.setItems(crud.getObs()); 
      Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Table.getSelectionModel().getSelectedItem() != null) 
        {     
           Annonce a = Table.getSelectionModel().getSelectedItem(); 
           FXMLDocumentMyAnnonceController.id = a.getIdAnnonce(); 
         }
         }          
     });
      Search.textProperty().addListener(new InvalidationListener() {

        @Override
        public void invalidated(Observable observable) {
            if(Search.textProperty().get().isEmpty()) {
                Table.setItems(crud.getObs());
                return;
            }
            ObservableList<Annonce> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Annonce, ?>> cols = Table.getColumns();
            for(int i=0; i<crud.getObs().size(); i++) {

                for(int j=0; j<cols.size(); j++) {
                    TableColumn col = cols.get(j);
                    String cellValue = col.getCellData(crud.getObs().get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(Search.textProperty().get().toLowerCase())) {
                        tableItems.add(crud.getObs().get(i));
                        break;
                    }                        
                }
            }
            Table.setItems(tableItems);

        }
    }); 
      final ObservableList optionTree = FXCollections.observableArrayList();
      optionTree.add("DateAnnonce");
      optionTree.add("Kilo");
      optionTree.add("Prix"); 
      this.Tree.setItems(optionTree);
      this.Tree.setEditable(true);
      this.Tree.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {             
            try {
                crud.OderByAnnonce(t1);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentMyAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            Marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
      Model.setCellValueFactory(new PropertyValueFactory<>("Modele"));
      Kilo.setCellValueFactory(new PropertyValueFactory<>("Kilo"));
      Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
      DateAnn.setCellValueFactory(new PropertyValueFactory<>("DateAnnonce"));
      Datecircul.setCellValueFactory(new PropertyValueFactory<>("DateCirculation"));
      Energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
      Puissance.setCellValueFactory(new PropertyValueFactory<>("PuissanceFiscale"));
      Couleur.setCellValueFactory(new PropertyValueFactory<>("Couleur"));
      Boite.setCellValueFactory(new PropertyValueFactory<>("Boite"));      
      Table.setItems(crud.getObs()); 
      Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(Table.getSelectionModel().getSelectedItem() != null) 
        {     
           Annonce a = Table.getSelectionModel().getSelectedItem(); 
           FXMLDocumentMyAnnonceController.id = a.getIdAnnonce(); 
         }
         }          
     });
      Search.textProperty().addListener(new InvalidationListener() {

        @Override
        public void invalidated(Observable observable) {
            if(Search.textProperty().get().isEmpty()) {
                Table.setItems(crud.getObs());
                return;
            }
            ObservableList<Annonce> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Annonce, ?>> cols = Table.getColumns();
            for(int i=0; i<crud.getObs().size(); i++) {

                for(int j=0; j<cols.size(); j++) {
                    TableColumn col = cols.get(j);
                    String cellValue = col.getCellData(crud.getObs().get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(Search.textProperty().get().toLowerCase())) {
                        tableItems.add(crud.getObs().get(i));
                        break;
                    }                        
                }
            }
            Table.setItems(tableItems);

        }
    }); 
        }    
    }); 
     //ComboboxTree. 
    }
    
    
}

