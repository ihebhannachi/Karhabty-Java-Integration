/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.karhabty.GUI.LoadScene;
import project.karhabty.entities.Annonce;
import project.karhabty.services.CrudAnnonceService; 
import project.karhabty.services.CrudNotif;
import project.karhabty.technical.Session;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
/**
 *
 * @author PC NET
 */
public class FXMLDocumentAdminController implements Initializable {
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
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
    public void BackButton (ActionEvent event) throws IOException, SQLException {
        CrudNotif notif = new CrudNotif(); 
        notif.setZero();
        Session.destroySession();
        LoadScene.HideStage();
        Parent rootLogin = FXMLLoader.load(getClass().getResource("/javafxapplicationkarhabty/FXMLDocument.fxml"));
        Scene scene = new Scene(rootLogin);
        LoadScene log = new LoadScene(scene);
        log.DisplayScene("Application.css");
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
    public void ExportExcel(ActionEvent event) throws SQLException {
        try {
    CrudAnnonceService an = new CrudAnnonceService();
     ResultSet res = an.ExportData();
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("lawix10");
    HSSFRow rowhead = sheet.createRow((short) 0);
    rowhead.createCell((short) 0).setCellValue("ID");
    rowhead.createCell((short) 1).setCellValue("MARQUE");
    rowhead.createCell((short) 2).setCellValue("MODELE");
    rowhead.createCell((short) 3).setCellValue("KILOMETRAGE");
    rowhead.createCell((short) 4).setCellValue("PRIX");
    rowhead.createCell((short) 5).setCellValue("DESCRIPTION");
    rowhead.createCell((short) 6).setCellValue("DATE");
    rowhead.createCell((short) 7).setCellValue("ADRESSE");
    rowhead.createCell((short) 8).setCellValue("TELEPHONE");
    rowhead.createCell((short) 9).setCellValue("DATE_CIR");
    rowhead.createCell((short) 10).setCellValue("ENERGIE");
    rowhead.createCell((short) 11).setCellValue("PUISSANCE");
    rowhead.createCell((short) 12).setCellValue("COULEUR");
    rowhead.createCell((short) 13).setCellValue("BOITE");
    rowhead.createCell((short) 14).setCellValue("ID_USER");
    int i = 1;
    while (res.next()){
        HSSFRow row = sheet.createRow((short) i);
        row.createCell((short) 0).setCellValue(Integer.toString(res.getInt("idAnnonce")));
        row.createCell((short) 1).setCellValue(res.getString("Marque"));
        row.createCell((short) 2).setCellValue(res.getString("Modele"));
        row.createCell((short) 3).setCellValue(Integer.toString(res.getInt("Kilo")));
        row.createCell((short) 4).setCellValue(Double.toString(res.getDouble("Prix")));
        row.createCell((short) 5).setCellValue(res.getString("Description"));
        Date date= res.getDate("DateAnnonce");
        row.createCell((short) 6).setCellValue(String.valueOf(date));
        row.createCell((short) 7).setCellValue(res.getString("Adresse"));
        row.createCell((short) 8).setCellValue(Integer.toString(res.getInt("Telephone")));
        row.createCell((short) 9).setCellValue(res.getString("Datecirculation"));
        row.createCell((short) 10).setCellValue(res.getString("Energie"));
        row.createCell((short) 11).setCellValue(res.getString("Puissancefiscale"));
        row.createCell((short) 12).setCellValue(res.getString("Couleur"));
        row.createCell((short) 13).setCellValue(res.getString("Boite"));
        row.createCell((short) 14).setCellValue(Integer.toString(res.getInt("IdUser")));
        i++; 
    }    
    String yemi = "C:/Users/PC NET/Desktop/test.xls";
    FileOutputStream fileOut = new FileOutputStream(yemi);
    workbook.write(fileOut);
    fileOut.close();
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
        Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("Export effectué")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT); 
                       
               not.darkStyle(); 
               not.showConfirm();
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
            crud.AdminDisplayAnnonce();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
           FXMLDocumentAdminController.id = a.getIdAnnonce(); 
         }
         }          
     });       
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           VBox box = FXMLLoader.load(getClass().getResource("/project/karhabty/GUI/ui_slidepaneldashboard.fxml"));
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
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        CrudAnnonceService crud = new CrudAnnonceService(); 
        try {
            crud.AdminDisplayAnnonce();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
           FXMLDocumentAdminController.id = a.getIdAnnonce(); 
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
                crud.AdminOrderByAnnonce(t1);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
           FXMLDocumentAdminController.id = a.getIdAnnonce(); 
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
    }    
    
}

