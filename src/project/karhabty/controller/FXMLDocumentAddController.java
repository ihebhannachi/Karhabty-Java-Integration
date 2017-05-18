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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Annonce;
import project.karhabty.services.ComboboxValue;
import project.karhabty.services.CrudAnnonceService;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentAddController implements Initializable {
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
     private TextField Kilo; 
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
    @FXML
    private Label ValidMarque;
    @FXML
    private Label ValidKilom;

    @FXML
    private Label ValidPuissance;

    @FXML
    private Label ValidCouleur;

    @FXML
    private Label ValidAdresse;

    @FXML
    private Label ValidModel;

    @FXML
    private Label ValidPrix;

    @FXML
    private Label ValidEnergie;

    @FXML
    private Label ValidBoite;

    @FXML
    private Label ValidDesc;

    @FXML
    private Label ValidTelephone;
    
    @FXML
    private Label ValidDate;
     private File file; 
     private static String Mar; 

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
    public void AddButton (ActionEvent event) throws FileNotFoundException, IOException, SQLException, MessagingException {
        if (!this.Kilo.getText().isEmpty()&&(!this.Prix.getText().isEmpty())&&(!this.Adresse.getText().isEmpty())&&(!this.Couleur.getText().isEmpty())&&(!this.Desc.getText().isEmpty())&&(!this.Puissance.getText().isEmpty())&&(!this.Telephone.getText().isEmpty())&&(this.Telephone.getText().length()==8)&&
                (!this.BoiteCombobox.getSelectionModel().isEmpty())&&(!this.EnergieCombobox.getSelectionModel().isEmpty())&&(!this.MarqueCombobox.getSelectionModel().isEmpty())&&(!this.ModelCombobox.getSelectionModel().isEmpty())) {
        this.ValidKilom.setText("");
        this.ValidPrix.setText("");
        this.ValidAdresse.setText("");
        this.ValidCouleur.setText("");
        this.ValidDesc.setText("");
        this.ValidPuissance.setText("");
        this.ValidTelephone.setText("");
        this.ValidBoite.setText("");
        this.ValidEnergie.setText("");
        this.ValidMarque.setText("");
        this.ValidModel.setText("");
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
        crud.AddAnnonce(a);
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/FXMLDocumentAccueil.fxml")); 
               Scene scene = new Scene(rootLogin);
               Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               app_stage.hide();
               app_stage.setScene(scene);
               app_stage.show();
        Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("Ajout éffectué")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showConfirm();
               }
        else {
           Notifications not = Notifications.create()
                       .title("Opération échoué ")
                       .text("Vérifier vos information")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showError();
               
    if (this.Kilo.getText().isEmpty()) {
        this.ValidKilom.setText("Champ obligatoire");
    }
    else {
      this.ValidKilom.setText("");  
    }
    if (this.Prix.getText().isEmpty()) {
        this.ValidPrix.setText("Champ obligatoire");
    }
    else {
        this.ValidPrix.setText("");
    }
    if (this.Adresse.getText().isEmpty()) {
        this.ValidAdresse.setText("Champ obligatoire");
    }
    else {
       this.ValidAdresse.setText(""); 
    }
    if (this.Couleur.getText().isEmpty()) {
        this.ValidCouleur.setText("Champ obligatoire");
    }
    else {
        this.ValidCouleur.setText("");
    }
    if (this.Desc.getText().isEmpty()) {
        this.ValidDesc.setText("Champ obligatoire");
    }
    else {
       this.ValidDesc.setText(""); 
    }
    if (this.Puissance.getText().isEmpty()) {
        this.ValidPuissance.setText("Champ obligatoire");
    }
    else {
         this.ValidPuissance.setText("");
    }
    if (this.Telephone.getText().isEmpty()) {
         this.ValidTelephone.setText("Champ obligatoire");
    }
    else if(this.Telephone.getText().length()!=8) {
        this.ValidTelephone.setText("Vérifier le numero de téléphone");
    }
   else {
        this.ValidTelephone.setText("");
    }
    if (this.BoiteCombobox.getSelectionModel().isEmpty()) {
        this.ValidBoite.setText("Champ obligatoire");
    }
    else {
       this.ValidBoite.setText(""); 
    }
    if (this.EnergieCombobox.getSelectionModel().isEmpty()) {
        this.ValidEnergie.setText("Champ obligatoire");
    }
    else {
        this.ValidEnergie.setText("");
    }
    if (this.MarqueCombobox.getSelectionModel().isEmpty()) {
        this.ValidMarque.setText("Champ obligatoire");
    }
    else {
        this.ValidMarque.setText("");  
    }
    if (this.ModelCombobox.getSelectionModel().isEmpty()) {
        this.ValidModel.setText("Champ obligatoire");
    }
    else {
        this.ValidModel.setText("");
    }
        }
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
