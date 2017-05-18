/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.karhabty.entities.Voiture;
import project.karhabty.services.VoituresServices;
import javafx.scene.layout.Pane;
import java.time.format.DateTimeFormatter;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import project.karhabty.entities.CsvE;



/**
 * FXML Controller class
 *
 * @author KAMOUN
 */
public class MainGestionEntretiensController implements Initializable {

    /**
     * Initializes the controller class.
     */
  @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Voiture> CARS_Tab;
    
    //////////////////////////
    @FXML 
    private TableColumn<Voiture,String> Matricule;
    @FXML
    private TableColumn<Voiture, String> Marque;
    @FXML 
    private TableColumn<Voiture,String> Modele;
    @FXML 
    private TableColumn<Voiture,Date> DateMisCirculation;
    @FXML 
    private TableColumn<Voiture,Integer> PuissanceFiscale;
    @FXML 
    private TableColumn<Voiture,Integer> ChevauxDyn;
    @FXML 
    private TableColumn<Voiture, Integer> Kilometrage;
    @FXML 
    private TableColumn<Voiture,String> Carburant;
    @FXML 
    private TableColumn<Voiture,String> Transmission;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    ///////////////////////////
   @FXML
    private JFXTextField Search_TF;
    @FXML
    private JFXButton AddNewCar_Button;
     @FXML
    private Label User_Lab;
    @FXML
    private JFXButton Excel_Button;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
          private LocalDate l; 
          ObservableValue<Voiture> voi;
          ObservableList<Voiture> v1;

@Override
    public void initialize(URL location, ResourceBundle resources) {
        assert CARS_Tab != null : "fx:id=\"CARS_Tab\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Matricule != null : "fx:id=\"Matricule\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Marque != null : "fx:id=\"Marque\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Modele != null : "fx:id=\"Modele\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert DateMisCirculation != null : "fx:id=\"DateMisCirculation\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert PuissanceFiscale != null : "fx:id=\"PuissanceFiscale\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert ChevauxDyn != null : "fx:id=\"ChevauxDyn\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Kilometrage != null : "fx:id=\"Kilometrage\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Carburant != null : "fx:id=\"Carburant\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert Transmission != null : "fx:id=\"Transmission\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        assert AddNewCar_Button != null : "fx:id=\"AddNewCar_Button\" was not injected: check your FXML file 'ui_MainGestionEntretiens.fxml'.";
        User_Lab.setText("1");
        VoituresServices vs = new VoituresServices(); 
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
          vs.displayCars();
          v1=vs.getObs();
      } catch (SQLException | IOException ex) {
          Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      Matricule.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
      Matricule.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      Marque.setCellValueFactory(new PropertyValueFactory<>("MarqueS"));
      Marque.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      Modele.setCellValueFactory(new PropertyValueFactory<>("ModeleS"));
      Modele.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      DateMisCirculation.setCellValueFactory(new PropertyValueFactory<>("DateMiseCirculation"));
      DateMisCirculation.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      PuissanceFiscale.setCellValueFactory(new PropertyValueFactory<>("PuissanceFiscale"));
      PuissanceFiscale.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      ChevauxDyn.setCellValueFactory(new PropertyValueFactory<>("puissanceDynamique"));
      ChevauxDyn.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      Kilometrage.setCellValueFactory(new PropertyValueFactory<>("Kilometrage"));
      Kilometrage.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      Transmission.setCellValueFactory(new PropertyValueFactory<>("Transmission"));
      Transmission.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      Carburant.setCellValueFactory(new PropertyValueFactory<>("Carburant"));         
      Carburant.setMinWidth(CARS_Tab.getMaxWidth()+1000/10);
      CARS_Tab.setItems(vs.getObs()); 
      CARS_Tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(CARS_Tab.getSelectionModel().getSelectedItem() != null) 
        {     
           Voiture v = CARS_Tab.getSelectionModel().getSelectedItem(); 
           v.toString();
        }
         }          
     });
      
      Search_TF.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
        
             LocalDate l=null;
             Voiture v;
             if(Search_TF.getText().isEmpty())
              {
                  CARS_Tab.setItems(v1);
              }
                v = new Voiture(Search_TF.getText(),Search_TF.getText(),Search_TF.getText(), l, Integer.parseInt(Search_TF.getText()), Integer.parseInt(Search_TF.getText()), Search_TF.getText(), Search_TF.getText(), Integer.parseInt(Search_TF.getText()));
                v.setProprietaire(Integer.parseInt(User_Lab.getText()));
                System.out.println(v);
                try {
                    vs.searchCars(v);
                    CARS_Tab.setItems(vs.getObs());
                } catch (SQLException ex) {
                    Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
      });
    }
    
     @FXML
    void CustomizedDisplay(MouseEvent event) {

            try {
                FXMLLoader  loader=new FXMLLoader();
                Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_DisplayEditDeleteCar.fxml").openStream());            
                Voiture v = CARS_Tab.getSelectionModel().getSelectedItem(); 
                System.out.println(v);
                DisplayEditDeleteCarController d= (DisplayEditDeleteCarController) loader.getController();
                d.getVoiture(v);
                d.setUser(User_Lab.getText());
                MouseEvent m= event.copyFor(DispEditDele_parent, Marque, MouseEvent.MOUSE_CLICKED);
                Scene Disp_scene = new Scene(DispEditDele_parent);
                Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                disp_stage.setScene(Disp_scene);
                disp_stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    public void handleButtonAction(ActionEvent event) throws IOException
    {
         try{
               
            FXMLLoader  loader=new FXMLLoader();
            Pane DispEditDele_parent = loader.load(getClass().getResource("/project/karhabty/GUI/ui_AddNewCar.fxml").openStream());            
            AddNewCarController f= (AddNewCarController) loader.getController();
            f.setUser(User_Lab.getText());
            Scene Disp_scene = new Scene(DispEditDele_parent);
            Stage disp_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            disp_stage.setScene(Disp_scene);
            disp_stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
 
    @FXML
    void Search_cars(KeyEvent event) {
        Voiture v= new Voiture();
        VoituresServices vs= new VoituresServices();
        v.setMatricule(Search_TF.getText());
        v.setMarqueS(Search_TF.getText());
        v.setModeleS(Search_TF.getText());
       // l=dtf.parse(Search_TF.getText());
       
      //  v.setDateMiseCirculation(l);
        v.setPuissanceFiscale(Integer.parseInt(Search_TF.getText()));
        v.setPuissanceDynamique(Integer.parseInt(Search_TF.getText()));   
        v.setKilometrage(Integer.parseInt(Search_TF.getText()));
        v.setCarburant(Search_TF.getText());
        v.setTransmission(Search_TF.getText());
        v.setProprietaire(Integer.parseInt(User_Lab.getText()));
        System.out.println(v);
        try {
          vs.searchCars(v);
      } catch (SQLException ex) {
          Logger.getLogger(MainGestionEntretiensController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   


   @FXML
    void Excel(ActionEvent event) {
        VoituresServices vs=new VoituresServices();
        ObservableList<Voiture> e=(ObservableList<Voiture>) CARS_Tab.getItems();
        CsvE sv=new CsvE((ObservableList<Voiture>) CARS_Tab.getItems());
    }
  void setUser(String s)
    {
        User_Lab.setText(s);
    }
}
