/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.karhabty.entities.*;
import project.karhabty.services.*;
import project.karhabty.controller.*;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author PROXYINFO
 */
public class FrontOfficeController implements Initializable {

    @FXML
    private TableView<Cours> F_inscri_cours_TV;
    @FXML
    private TableColumn<Cours,String> F_inscri_nom_cours_CV;
    @FXML
    private TableColumn<Cours,String> F_inscri_module_cours_CV;
    @FXML
    private TableColumn<Cours,String> F_inscri_avancement_cours_CV;
    @FXML
    private TableColumn<Cours,String> F_inscri_DataCreation_cours_CV;
    @FXML
    private TableColumn<Cours,String> F_inscri_DateModif_cours_CV;
    @FXML
    private JFXButton F_inscri_BT;
    @FXML
    private JFXComboBox<String> F_inscri_module_CB;
    @FXML
    private JFXComboBox<String> F_inscri_avancement_CB;
    @FXML
    private TabPane tabpaneFrontOffice;
    @FXML
    private TableView<SuiviCours> F_monSuivi_Cours_TV;
    @FXML
    private TableColumn<SuiviCours, String> F_monSuivi_CoursNom_CV;
    @FXML
    private TableColumn<SuiviCours,String> F_monSuivi_module_TV;
    @FXML
    private TableColumn<SuiviCours,String> F_monSuivi_CoursAvancement_TV;
    @FXML
    private TableColumn<SuiviCours,String> F_monSuivi_CoursDAte_TV;
    @FXML
    private TableView<SuiviTests> F_monSuivi_Tests_TV;
    @FXML
    private TableColumn<SuiviTests, String> F_monSuivi_Tests_Libelle_TV;
    @FXML
    private TableColumn<SuiviTests, String> F_monSuivi_Tests_module_TV;
    private TableColumn<SuiviTests, String> F_monSuivi_Tests_avancement_TV;
    @FXML
    private JFXComboBox<String> monsuivi_module_CB;
    @FXML
    private JFXComboBox<String> monsuivi_avancement_CB;
    @FXML
    private JFXTextField F_monsuivi_recherche_LE;
    @FXML
    private JFXButton nouveauTest_BT;
    @FXML
    private TableView<Test> Liste_des_tests_TV;
    @FXML
    private TableColumn<Test,String> Liste_des_tests_libelle_CV;
    @FXML
    private TableColumn<Test,String> Liste_des_tests_module_CV;
    @FXML
    private TableColumn<Test,String> Liste_des_tests_avancement_CV;
    @FXML
    private TableColumn<Test,String> Liste_des_tests_dateCreation_CV;
    @FXML
    private TableColumn<Test,String> Liste_des_tests_datemodif_CV;
    @FXML
    private JFXButton effectuer_test_BT;
    @FXML
    private TableView<Qcm> lieudutest_TV;
    @FXML
    private TableColumn<Qcm,String> ennonce_CV;
    @FXML
    private TableColumn<Qcm,String> a1_CV;
    @FXML
    private TableColumn<Qcm,String> a2_CV;
    @FXML
    private TableColumn<Qcm,String> a3_CV;
    @FXML
    private TableColumn<Qcm,String> a4_CV;
    @FXML
    private JFXButton lire_cours_BT;
    @FXML
    private ImageView search_iconImg;
    @FXML
    private JFXButton refaire_test_BT;
    @FXML
    private GridPane idontknow;
    @FXML
    private JFXComboBox<String> faireTestModule_CB;
    @FXML
    private JFXComboBox<String> FaireTestAvancement_CB;
    @FXML
    private StackPane stackpane;
    @FXML
    private JFXButton bibliotheque_BT;
    
             SuiviCoursController scs=new SuiviCoursController();

         
          PieChart chart = new PieChart(); 
    private TableView<Cours> Cours_Conseillés_TV;
    private TableColumn<Cours,String> Cours_conseillés_CV;
    private TableView<Cours> Cours_Conseilles_TV;
    @FXML
    private TableColumn<Cours,String> Cours_conseilles_CV;
    private TableView<Cours> Cours_conseilles_TV;
    @FXML
    private TableView<Cours> tablevieuuu;
    @FXML
    private TableColumn<Qcm, String> QCM_Reponse_CV;
    @FXML
    private TableColumn<?, ?> F_monSuivi_Tests_date_TV;
    @FXML
    private TableColumn<?, ?> F_monSuivi_Tests_note_TV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ObservableList<String> listModules = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        F_inscri_module_CB.setItems(listModules);
        ObservableList<String> listAvancement = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        F_inscri_avancement_CB.setItems(listAvancement);
        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.afficherSuivi();
        F_inscri_nom_cours_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        F_inscri_module_cours_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        F_inscri_avancement_cours_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        F_inscri_DataCreation_cours_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        F_inscri_DateModif_cours_CV.setCellValueFactory(new PropertyValueFactory<>("date_derniere_modification"));
        F_inscri_cours_TV.setItems(ob_cours);
        // TODO
        
                SuiviCours sc=new SuiviCours();
        SuiviCoursController scc=new SuiviCoursController();
        scc.setSuiviCours(sc);
                 F_monSuivi_CoursNom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
         F_monSuivi_module_TV.setCellValueFactory(new PropertyValueFactory<>("module"));
         F_monSuivi_CoursAvancement_TV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
         F_monSuivi_CoursDAte_TV.setCellValueFactory(new PropertyValueFactory<>("date_inscription"));
        F_monSuivi_Cours_TV.setItems(scc.afficher());
        
        tabpaneFrontOffice.getSelectionModel().select(0);
        //todoo
        
        
 
        monsuivi_module_CB.setItems(listModules);
        monsuivi_avancement_CB.setItems(listAvancement);
        SuiviTestsController st=new SuiviTestsController();
        
        ObservableList<SuiviTests> suitests = st.afficher();
        F_monSuivi_Tests_Libelle_TV.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        F_monSuivi_Tests_module_TV.setCellValueFactory(new PropertyValueFactory<>("module"));
        F_monSuivi_Tests_date_TV.setCellValueFactory(new PropertyValueFactory<>("date_passage"));
        F_monSuivi_Tests_note_TV.setCellValueFactory(new PropertyValueFactory<>("note"));
        F_monSuivi_Tests_TV.setItems(suitests);
        
        //todoo
        
        lieudutest_TV.setVisible(false);
        TestController t=new TestController();
                Liste_des_tests_libelle_CV.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        Liste_des_tests_module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        Liste_des_tests_avancement_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Liste_des_tests_dateCreation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        Liste_des_tests_datemodif_CV.setCellValueFactory(new PropertyValueFactory<>("date_derniere_modification"));
        Liste_des_tests_TV.setItems(t.afficher());
        Image image = new Image("/img/Zoom-01.png");
        search_iconImg.setImage(image);
        
        lire_cours_BT.setDisable(true);
        refaire_test_BT.setDisable(true);
        
        SuiviCoursController qc=new SuiviCoursController();
        SuiviTestsController tc=new SuiviTestsController();
        
       ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements","MIXTE");
        monsuivi_module_CB.setItems(listModule);
           monsuivi_module_CB.setOnAction((event2) -> {
      String difficulte = monsuivi_avancement_CB.getSelectionModel().getSelectedItem();
    String module= monsuivi_module_CB.getSelectionModel().getSelectedItem();
     if(difficulte!=null)
                  {
                       ObservableList<SuiviCours> Coursfiltredbydeux = qc.filtrer_par_les_deux(module,difficulte);
                       ObservableList<SuiviTests> Testsfiltredbydeux = tc.filtrer_par_les_deux(module,difficulte);

        F_monSuivi_Cours_TV.setItems(Coursfiltredbydeux);
        F_monSuivi_Tests_TV.setItems(Testsfiltredbydeux);
                  }
   else{
          ObservableList<SuiviCours> Coursfiltredbymodule = qc.filtrer_par_module(module);
                       ObservableList<SuiviTests> Testsfiltredbymodule = tc.filtrer_par_module(module);

        F_monSuivi_Cours_TV.setItems(Coursfiltredbymodule);
        F_monSuivi_Tests_TV.setItems(Testsfiltredbymodule);
       
                
    }
    
                

});

        ObservableList<String> listAvancementest = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe","MIXTE");
        monsuivi_avancement_CB.setItems(listAvancementest);
      
        
         monsuivi_avancement_CB.setOnAction((event2) -> {
    String difficulte = monsuivi_avancement_CB.getSelectionModel().getSelectedItem();
    String module= monsuivi_module_CB.getSelectionModel().getSelectedItem();
     if(monsuivi_module_CB.getSelectionModel().getSelectedItem()!=null)
                  {
                       ObservableList<SuiviCours> Coursfiltredbydeux = qc.filtrer_par_les_deux(module,difficulte);
                       ObservableList<SuiviTests> Testsfiltredbydeux = tc.filtrer_par_les_deux(module,difficulte);

        F_monSuivi_Cours_TV.setItems(Coursfiltredbydeux);
        F_monSuivi_Tests_TV.setItems(Testsfiltredbydeux);
                  }
   else{ ObservableList<SuiviCours> Coursfiltredbydifficulte = qc.filtrer_par_difficulte(difficulte);
                       ObservableList<SuiviTests> Testsfiltredbydifficulte = tc.filtrer_par_difficulte(difficulte);

        F_monSuivi_Cours_TV.setItems(Coursfiltredbydifficulte);
        F_monSuivi_Tests_TV.setItems(Testsfiltredbydifficulte);
                
    }
    
                

});
         
        
         // filtrage de l'onglet effectuer un test
         
             TestController tst=new TestController();
        
       //ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements","MIXTE");
faireTestModule_CB.setItems(listModule);
           faireTestModule_CB.setOnAction((event2) -> {
      String difficulte = FaireTestAvancement_CB.getSelectionModel().getSelectedItem();
    String module= faireTestModule_CB.getSelectionModel().getSelectedItem();
     if(difficulte!=null)
                  {
                      
                       ObservableList<Test> filtredbydeux = tst.filtrer_par_les_deux(module,difficulte);

        Liste_des_tests_TV.setItems(filtredbydeux);
                  }
   else{
                                 ObservableList<Test> filtredbymodule = tst.filtrer_par_module(module);

        
        Liste_des_tests_TV.setItems(filtredbymodule);
       
                
    }
    
                

});

        //ObservableList<String> listAvancementest = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe","MIXTE");
        FaireTestAvancement_CB.setItems(listAvancementest);
      
        
         FaireTestAvancement_CB.setOnAction((event2) -> {
    String difficulte = FaireTestAvancement_CB.getSelectionModel().getSelectedItem();
    String module= faireTestModule_CB.getSelectionModel().getSelectedItem();
     if(faireTestModule_CB.getSelectionModel().getSelectedItem()!=null)
                  {
                      
                       ObservableList<Test> filtredbydeux = tst.filtrer_par_les_deux(module,difficulte);

        
        Liste_des_tests_TV.setItems(filtredbydeux);
                  }
   else{ ObservableList<Test> filtredbydifficulte = tst.filtrer_par_difficulte(difficulte);
                      
        Liste_des_tests_TV.setItems(filtredbydifficulte);
                
    }
    
                

});
         
         
         //statiistiques 
         List<Integer> l = scs.generer_statistiques();

        chart.setTitle("progrès modulaires"); 
        chart.getData().setAll(new PieChart.Data("juridique", l.get(0)), new PieChart.Data("signaux", l.get(1)),  
                new PieChart.Data("priorités", l.get(2)), new PieChart.Data("classements", l.get(3))
                
        ); 
        
        stackpane.getChildren().add(chart); 
        

         Cours_conseilles_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tablevieuuu.setItems(scs.lister_cours_conseils());
        
        
         
    }    

    @FXML
    private void ajouter_suivi_cours(ActionEvent event) {
        Cours c=F_inscri_cours_TV.getSelectionModel().getSelectedItem();
        Courscontroller CC=new Courscontroller();
        
        CC.setCours(c);
        CC.ajouter_suivi();
        F_inscri_cours_TV.setItems(CC.afficherSuivi());
        SuiviCours sc=new SuiviCours();
        SuiviCoursController scc=new SuiviCoursController();
        scc.setSuiviCours(sc);
                 F_monSuivi_CoursNom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
         F_monSuivi_module_TV.setCellValueFactory(new PropertyValueFactory<>("module"));
         F_monSuivi_CoursAvancement_TV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
         F_monSuivi_CoursDAte_TV.setCellValueFactory(new PropertyValueFactory<>("date_inscription"));
        F_monSuivi_Cours_TV.setItems(scc.afficher());
        
                Image img=new Image("img/Capture.PNG");
        Notifications notification=Notifications.create().title("inscription confirmée").text("vous etes maintenant inscrit au cours "+c.getNom()).graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_RIGHT);
       notification.show();
       
        tabpaneFrontOffice.getSelectionModel().select(0);
        
       
         List<Integer> l = scs.generer_statistiques();
         
         
       chart.getData().get(0).setPieValue(l.get(0));
       chart.getData().get(1).setPieValue(l.get(1));
       chart.getData().get(2).setPieValue(l.get(2));
       chart.getData().get(3).setPieValue(l.get(3));
        
        
       

    }

    private void aller_vers_nouveu_cours(ActionEvent event) {
        tabpaneFrontOffice.getSelectionModel().select(2);
    }

    @FXML
    private void rechercherMonSuivi(KeyEvent event) {
       
        ObservableList<SuiviCours> ob_cours = new SuiviCoursController().rechercherCours(F_monsuivi_recherche_LE.getText());
        F_monSuivi_CoursNom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        F_monSuivi_module_TV.setCellValueFactory(new PropertyValueFactory<>("module"));
        F_monSuivi_CoursAvancement_TV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        F_monSuivi_CoursDAte_TV.setCellValueFactory(new PropertyValueFactory<>("date_inscription"));
        
                ObservableList<SuiviTests> ob_cours2 = new SuiviTestsController().rechercherTests(F_monsuivi_recherche_LE.getText());

             F_monSuivi_Tests_Libelle_TV.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        F_monSuivi_Tests_module_TV.setCellValueFactory(new PropertyValueFactory<>("module"));
        F_monSuivi_Tests_date_TV.setCellValueFactory(new PropertyValueFactory<>("date_passage"));
        F_monSuivi_Tests_note_TV.setCellValueFactory(new PropertyValueFactory<>("note"));
     
        F_monSuivi_Cours_TV.setItems(ob_cours);
        F_monSuivi_Tests_TV.setItems(ob_cours2);
        
    }

    @FXML
    private void aller_vers_nouveu_Test(ActionEvent event) {
        tabpaneFrontOffice.getSelectionModel().select(1);
        
    }

    @FXML
    private void effectuer_test(ActionEvent event) {
        lieudutest_TV.setVisible(true);
        //afficher les questions
        Test t=Liste_des_tests_TV.getSelectionModel().getSelectedItem();
        TestController test=new TestController();
        Liste_des_tests_TV.setEditable(false);
        
        ennonce_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        a1_CV.setCellValueFactory(new PropertyValueFactory<>("alternative1"));
        a2_CV.setCellValueFactory(new PropertyValueFactory<>("alternative2"));
        a3_CV.setCellValueFactory(new PropertyValueFactory<>("alternative3"));
        a4_CV.setCellValueFactory(new PropertyValueFactory<>("alternative4"));
        QCM_Reponse_CV.setCellValueFactory(new PropertyValueFactory<>("reponse"));
       
        //QCM_Reponse_CV.setCellFactory(ComboBoxTableCell.<>forTableColumn("A","B", "C"));



        lieudutest_TV.setItems(test.afficher_questions(t));
 
        
    }

    @FXML
    private void lire_cours_pdf(ActionEvent event) {
        SuiviCours s=F_monSuivi_Cours_TV.getSelectionModel().getSelectedItem();
        SuiviCoursController sc=new SuiviCoursController();
        sc.lire(s.getNom());
    }

    @FXML
    private void refaireTest(ActionEvent event) {
            ennonce_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        a1_CV.setCellValueFactory(new PropertyValueFactory<>("alternative1"));
        a2_CV.setCellValueFactory(new PropertyValueFactory<>("alternative2"));
        a3_CV.setCellValueFactory(new PropertyValueFactory<>("alternative3"));
        a4_CV.setCellValueFactory(new PropertyValueFactory<>("alternative4"));
        SuiviTests st=F_monSuivi_Tests_TV.getSelectionModel().getSelectedItem();
        TestController tc=new TestController();
        lieudutest_TV.setItems(tc.afficher_questions(st.getLibelle()));
        tabpaneFrontOffice.getSelectionModel().select(1);
        lieudutest_TV.setVisible(true);
        Liste_des_tests_TV.setVisible(true);
    }

    @FXML
    private void activerBouttonCours(MouseEvent event) {
        if(F_monSuivi_Cours_TV.getSelectionModel().getSelectedItem()!=null)
        {
            lire_cours_BT.setDisable(false);
        }
    }

    @FXML
    private void activerBoutonTest(MouseEvent event) {
        if(F_monSuivi_Tests_TV.getSelectionModel().getSelectedItem()!=null)
        {
            refaire_test_BT.setDisable(false);
        }
    }

    @FXML
    private void setbuttonsDisabled(MouseEvent event) {
        refaire_test_BT.setDisable(true);
        lire_cours_BT.setDisable(true);
    }

    @FXML
    private void allerversBibliotheque(ActionEvent event) {
        
        tabpaneFrontOffice.getSelectionModel().select(2);
   
                }
    
}
