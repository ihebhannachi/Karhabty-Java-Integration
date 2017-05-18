/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import project.karhabty.controller.FXMLDocumentAddController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Annonce;
import project.karhabty.services.ComboboxValue;
import project.karhabty.services.CrudAnnonceService;
import org.controlsfx.control.Notifications;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentModifyController implements Initializable {
    @FXML 
     private JFXComboBox ModelCombobox;
     @FXML 
     private JFXComboBox MarqueCombobox;
     @FXML 
     private JFXComboBox EnergieCombobox; 
     @FXML 
     private JFXComboBox BoiteCombobox;
     @FXML 
     private Label FileName; 
     @FXML 
     private JFXTextField Kilo; 
     @FXML 
     private JFXTextField Prix; 
     @FXML 
     private JFXDatePicker DateCirculation;
     @FXML
     private JFXTextField Puissance;
     @FXML
     private JFXTextField Couleur; 
     @FXML 
     private JFXTextField Adresse; 
     @FXML 
     private JFXTextField Telephone; 
     @FXML 
     private JFXTextArea Desc; 
     private File file; 
     private static String Mar;
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
    public void GetImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.file = fileChooser.showOpenDialog(LoadScene.getStage());
        if (file!= null) {
           FileName.setText(file.getName());
        }
        
    }
    @FXML 
    public void ModifyButton(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        Annonce a = new Annonce();
        a.setMarque(this.MarqueCombobox.getSelectionModel().getSelectedItem().toString());
        a.setModele(this.ModelCombobox.getSelectionModel().getSelectedItem().toString()); 
        a.setKilo(Integer.parseInt(this.Kilo.getText()));
        a.setPrix(Double.parseDouble(this.Prix.getText()));
        a.setDateCirculation(this.DateCirculation.getValue().toString());
        a.setEnergie(this.EnergieCombobox.getSelectionModel().getSelectedItem().toString());
        a.setPuissanceFiscale(this.Puissance.getText());
        a.setBoite(this.BoiteCombobox.getSelectionModel().getSelectedItem().toString());
        a.setCouleur(this.Couleur.getText());
        a.setDescription(this.Desc.getText());
        a.setPhoto(file);
        a.setAdresse(this.Adresse.getText());
        a.setTelehphone(Integer.parseInt(this.Telephone.getText()));
        CrudAnnonceService crud = new CrudAnnonceService(); 
        crud.UpdateAnnonce(a, id);
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
        Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("Modification éffectué")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showConfirm();
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Kilo.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        Prix.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        Telephone.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
        Couleur.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
        final ObservableList optionEnergie = FXCollections.observableArrayList(); 
       final ObservableList optionBoite = FXCollections.observableArrayList();
       optionEnergie.add("Essence"); 
       optionEnergie.add("Diesel");
       this.EnergieCombobox.setItems(optionEnergie);
       this.EnergieCombobox.setEditable(true);
       optionBoite.add("Manuelle"); 
       optionBoite.add("Automatique");
       this.BoiteCombobox.setItems(optionBoite);
       this.BoiteCombobox.setEditable(true);
        ComboboxValue v = new ComboboxValue();
         try {
             v.GetMarque();
         } catch (SQLException ex) {
             Logger.getLogger(FXMLDocumentAddController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FXMLDocumentAddController.class.getName()).log(Level.SEVERE, null, ex);
            }                                                 
        }    
    });     
        this.ModelCombobox.setItems(v.GetListModel());
        this.ModelCombobox.setEditable(true);
         Annonce a = CrudAnnonceService.getOneAnnonce();
         this.Kilo.setText(String.valueOf(a.getKilo()));
         this.Prix.setText(String.valueOf(a.getPrix()));
         this.Puissance.setText(a.getPuissanceFiscale());
         this.Couleur.setText(a.getCouleur());
         this.Adresse.setText(a.getAdresse());
         this.Telephone.setText(String.valueOf(a.getTelehphone()));
         this.Desc.setText(a.getDescription());
         id=a.getIdAnnonce(); 
    }
public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}    
/*****************************************************************************************/

 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}    
    
}
