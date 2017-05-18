/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.services.*;
import project.karhabty.entities.*;
import project.karhabty.controller.*;


/**
 * FXML Controller class
 *
 * @author PROXYINFO
 */
public class FXMLDocumentController_1 implements Initializable {
    @FXML
    private ComboBox<String> J_ajouterModule_CB;
    @FXML
    private ComboBox<String> J_ajouterAvancement_CB;
    private TextField J_ajouterNom_LE;
    @FXML
    private Button validerAjout_Bt;
    @FXML
    private TableView<Cours> ListeCours_TV;
    @FXML
    private TableColumn<Cours, String> nom_CV;
    @FXML
    private TableColumn<Cours, String> module_CV;
    @FXML
    private TableColumn<Cours, String> avancement_CV;
    @FXML
    private TableColumn<Cours, Integer> reference_CV;
    @FXML
    private TableColumn<Cours, Date> date_creation_CV;
    @FXML
    private TextField J_recherche_LE;
    @FXML
    private Tab listeCours_scene;
    private TableColumn<Cours, Boolean> checked_CV;
    @FXML
    private TextField question_reference_LE;
    private TextField question_module_LE;
    @FXML
    private TextArea question_ennonce_TA;
    @FXML
    private ComboBox<String> question_difficulte_CB;
    @FXML
    private TextArea alternative1_TA;
    @FXML
    private TextArea alternative2_TA;
    @FXML
    private TextArea alternative3_TA;
    @FXML
    private TextArea alternative4_TA;
    @FXML
    private JFXComboBox<String> reponse_correcte_LE;
    @FXML
    private Button valider_ajout_question_BT;
    @FXML
    private ComboBox<String> question_module_CB;
    @FXML
    private Tab Ajoutquestions_tab;
    @FXML
    private TableColumn<?, ?> Reference_Question_CV;
    @FXML
    private TableColumn<?, ?> module_Question_CV;
    @FXML
    private TableColumn<?, ?> difficulte_Question_CV;
    @FXML
    private TableColumn<?, ?> Ennonce_Question_CV;
    @FXML
    private TableView<Question> listeQuestion_TV;
    @FXML
    private TableView<Reponse> listeReponses_TV;
    @FXML
    private TableColumn<Reponse, String> reponse_CV;
    @FXML
    private TableColumn<Reponse, String> vraiFaux_CV;
    @FXML
    private TextField recherche_question_LE;
    @FXML
    private Button supprimer_cours_BT;
    @FXML
    private TableView<Question> listeQuestionTEST_TV;
    @FXML
    private TableColumn<Question, String> Reference_Question_CV1;
    @FXML
    private TableColumn<Question, String> module_Question_CV1;
    @FXML
    private TableColumn<Question, String> difficulte_Question_CV1;
    @FXML
    private TableColumn<Question, String> Ennonce_Question_CV1;
    @FXML
    private Button ajouter_au_test_BT;
    @FXML
    private Label module_test_LE;
    @FXML
    private Button retirer_du_test_BT;
    @FXML
    private TableView<Question> listeQuestionChoisisTEST_TV;
    @FXML
    private TableColumn<Question, String> Reference_Question_CV11;
    @FXML
    private TableColumn<Question, String> module_Question_CV11;
    @FXML
    private TableColumn<Question,String> difficulte_Question_CV11;
    @FXML
    private TableColumn<Question,String> Ennonce_Question_CV11;
    @FXML
    private Button confirmer_TEST_BT;
    @FXML
    private ComboBox<String> difficulte_TEST_CB;
    @FXML
    private ComboBox<String> moduleTest_CB;
     ObservableList<Question> data=FXCollections.observableArrayList();
    @FXML
    private TextField libelle_LE;
    @FXML
    private TextField reference_teste_LE;
    @FXML
    private JFXButton modifier_cours_BT;
    @FXML
    private GridPane modification_cours_labels;
    @FXML
    private JFXTextField modif_cours_nom_LE;
    @FXML
    private JFXComboBox<String> modif_cours_module_CB;
    @FXML
    private JFXComboBox<String> modif_cours_avancement_CB;
    @FXML
    private JFXButton confirmer_modif_cours_BT;
    @FXML
    private TableColumn<?, ?> date_derniere_modif_CV;
    @FXML
    private Label nom_cours_var_label;
    @FXML
    private JFXButton parcourir_pdf_BT;
    private Cours Courspdf ;
    @FXML
    private JFXButton supprimer_question_BT;
    @FXML
    private JFXButton modifier_question_BT;
    @FXML
    private AnchorPane anchorpaneQuestionModif_AP;
    @FXML
    private JFXTextField modif_ref_question_LE;
    @FXML
    private JFXTextArea modif_ennonce_ques_TA;
    @FXML
    private JFXTextField modif_rep2_ques_LE;
    @FXML
    private JFXTextField modif_rep1_ques_LE;
    @FXML
    private JFXTextField modif_rep3_ques_LE;
    @FXML
    private JFXTextField modif_rep4_ques_LE;
    @FXML
    private JFXComboBox<String> modif_module_ques_CB;
    @FXML
    private JFXComboBox<String> modif_diff_ques_CB;
    @FXML
    private JFXButton valider_modif_question_BT;
    @FXML
    private JFXComboBox<String> moodif_rep_correcte_LE;
    @FXML
    private ImageView searchlogo;
    @FXML
    private ImageView imgSearcha;
    @FXML
    private ImageView deleteCours_icon;
    @FXML
    private ImageView updateimageIcon;
    @FXML
    private ImageView addIcon;
    @FXML
    private AnchorPane anchorpane_ListeCours;
    @FXML
    private ImageView validerIcon;
    @FXML
    private ImageView validerImage;
    @FXML
    private AnchorPane espaceExt_AP;
    @FXML
    private Label Controle_nbr_LB;
    @FXML
    private Label controle_existance_LB;
    @FXML
    private Label labelleValide_LB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorpaneQuestionModif_AP.setVisible(false);
        ObservableList<String> listModules = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        J_ajouterModule_CB.setItems(listModules);
        ObservableList<String> listAvancement = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        J_ajouterAvancement_CB.setItems(listAvancement);
        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.afficher();
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        date_derniere_modif_CV.setCellValueFactory(new PropertyValueFactory<>("date_derniere_modification"));
        //checked_CV.setCellFactory(column -> new CheckBoxTableCell());
        //checked_CV.setCellValueFactory(new PropertyValueFactory<>("check"));
        ListeCours_TV.setItems(ob_cours);

        ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        question_module_CB.setItems(listModule);

        ObservableList<String> listAvancemen = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        question_difficulte_CB.setItems(listAvancement);

        QuestionController qc = new QuestionController();
        ObservableList<Question> ob_question = qc.afficher();
        Reference_Question_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestion_TV.setItems(ob_question);
        
        
         ObservableList<String> listModuletest = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements","MIXTE");
        moduleTest_CB.setItems(listModuletest);
        
        
             

            
        
        
                  Reference_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        
          moduleTest_CB.setOnAction((event2) -> {
    String module = moduleTest_CB.getSelectionModel().getSelectedItem();
     if(difficulte_TEST_CB.getSelectionModel().getSelectedItem()!=null)
                  {
                       ObservableList<Question> questionfiltredbydeux = qc.filtrer_par_les_deux(module,difficulte_TEST_CB.getSelectionModel().getSelectedItem());

        listeQuestionTEST_TV.setItems(questionfiltredbydeux);
                  }
   else{
                ObservableList<Question> questionfiltredbymodule = qc.filtrer_par_module(module);

        listeQuestionTEST_TV.setItems(questionfiltredbymodule);
                
    }
    
                

});

        ObservableList<String> listAvancementest = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe","MIXTE");
        difficulte_TEST_CB.setItems(listAvancementest);
        
          Reference_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV1.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestionTEST_TV.setItems(ob_question);
        
        
        
        
         difficulte_TEST_CB.setOnAction((event2) -> {
    String difficulte = difficulte_TEST_CB.getSelectionModel().getSelectedItem();
    String module= moduleTest_CB.getSelectionModel().getSelectedItem();
     if(moduleTest_CB.getSelectionModel().getSelectedItem()!=null)
                  {
                       ObservableList<Question> questionfiltredbydeux = qc.filtrer_par_les_deux(module,difficulte);

        listeQuestionTEST_TV.setItems(questionfiltredbydeux);
                  }
   else{
                ObservableList<Question> questionfiltredbydifficulte = qc.filtrer_par_difficulte(difficulte);

        listeQuestionTEST_TV.setItems(questionfiltredbydifficulte);
                
    }
    
                

});
        
        modif_cours_module_CB.setItems(listModules);
        modif_cours_avancement_CB.setItems(listAvancement);
       modification_cours_labels.setVisible(false);
       confirmer_modif_cours_BT.setVisible(false);
       
       
       modif_module_ques_CB.setItems(listModule);
       modif_diff_ques_CB.setItems(listAvancement);
       
ObservableList<String> o = FXCollections.observableArrayList("alternative 1","alternative 2","alternative 3","alternative 4");
       moodif_rep_correcte_LE.setItems(o);
        // TODO
        
        modifier_cours_BT.setDisable(true);
        supprimer_cours_BT.setDisable(true);
        
                Image image = new Image("/karhabty/gui/Zoom-01.png");
        searchlogo.setImage(image);
             
        imgSearcha.setImage(image);
                        Image image2 = new Image("/karhabty/gui/delete.png");

        deleteCours_icon.setImage(image2);
              Image image3 = new Image("/karhabty/gui/update.png");
              updateimageIcon.setImage(image3);
              Image addimage = new Image("/karhabty/gui/add.png");
              addIcon.setImage(addimage);
              Image valider = new Image("/karhabty/gui/valider.png");
              validerIcon.setImage(valider);
              validerImage.setImage(valider);
              
              ObservableList<String> ceci = FXCollections.observableArrayList("alternative1", "alternative2", "alternative3", "alternative4");
        reponse_correcte_LE.setItems(ceci);
        
        supprimer_question_BT.setDisable(true);
        modifier_question_BT.setDisable(true);
        
        ajouter_au_test_BT.setDisable(true);
        retirer_du_test_BT.setDisable(true);
        confirmer_TEST_BT.setDisable(true);
        
        labelleValide_LB.setVisible(false);
        controle_existance_LB.setVisible(false);
        Controle_nbr_LB.setVisible(false);
       
            valider_ajout_question_BT.setDisable(false);  
    }

    @FXML
    private void ajouterCours(ActionEvent event) {

       
       Courspdf.setNom(nom_cours_var_label.getText());
        Courspdf.setModule(J_ajouterModule_CB.getValue());
        Courspdf.setAvancement(J_ajouterAvancement_CB.getSelectionModel().getSelectedItem());
        Courscontroller ccc = new Courscontroller();
        ccc.setCours(Courspdf);
        
        ccc.ajouter();
//        J_ajouterNom_LE.clear();
        J_ajouterAvancement_CB.getSelectionModel().clearSelection();
        J_ajouterModule_CB.getSelectionModel().clearSelection();
        nom_cours_var_label.setText("");
        
        
        ObservableList<String> listModules = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        J_ajouterModule_CB.setItems(listModules);
        ObservableList<String> listAvancement = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        J_ajouterAvancement_CB.setItems(listAvancement);
        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.afficher();
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        //checked_CV.setCellFactory(column -> new CheckBoxTableCell());
        //checked_CV.setCellValueFactory(new PropertyValueFactory<>("check"));
        ListeCours_TV.setItems(ob_cours);

        ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        question_module_CB.setItems(listModule);

        ObservableList<String> listAvancemen = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        question_difficulte_CB.setItems(listAvancement);

        QuestionController qc = new QuestionController();
        ObservableList<Question> ob_question = qc.afficher();
        Reference_Question_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestion_TV.setItems(ob_question);
        
        
        Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("ajout confirmé").text("votre ajout a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.TOP_RIGHT);
       notification.show();
       

        // TODO
        /* Redirection vers la page lokhra
        
        Scene scene = ListeCours_TV.getScene();
        Node nodeToFind = scene.lookup("#listeCours_scene");
        Scene newscene=nodeToFind.getScene();
        Stage stage=(Stage) nodeToFind.getScene().getWindow();
        stage.setScene(newscene);
        stage.show();
         */
    }

    @FXML
    private void rechercherParTousLesChamps(KeyEvent event) {

        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.rechercher(J_recherche_LE.getText());
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        ListeCours_TV.setItems(ob_cours);

    }

    @FXML
    private void ajouter_question(ActionEvent event) {

        Reponse r1 = new Reponse(alternative1_TA.getText(), false);
        Reponse r2 = new Reponse(alternative2_TA.getText(), false);
        Reponse r3 = new Reponse(alternative3_TA.getText(), false);
        Reponse r4 = new Reponse(alternative4_TA.getText(), false);
        if (reponse_correcte_LE.getSelectionModel().getSelectedItem().equals("alternative1")) {
            r1.setCorrecte(true);
        } else if (reponse_correcte_LE.getSelectionModel().getSelectedItem().equals("alternative2")) {
            r2.setCorrecte(true);
        } else if (reponse_correcte_LE.getSelectionModel().getSelectedItem().equals("alternative3")) {
            r3.setCorrecte(true);
        } else if (reponse_correcte_LE.getSelectionModel().getSelectedItem().equals("alternative4")) {
            r4.setCorrecte(true);

        }
        List<Reponse> l = new ArrayList();
        l.add(r1);
        l.add(r2);
        l.add(r3);
        l.add(r4);
        Question ques = new Question();
        ques.setReference(Integer.parseInt(question_reference_LE.getText()));
        ques.setModule(question_module_CB.getSelectionModel().getSelectedItem());
        ques.setEnnonce(question_ennonce_TA.getText());
        ques.setDifficulte(question_difficulte_CB.getSelectionModel().getSelectedItem());
        ques.setListeDesReponses(l);
        QuestionController qestcont = new QuestionController();
        qestcont.setQuestion(ques);
        qestcont.ajouter();
        question_reference_LE.clear();
        question_module_CB.getSelectionModel().clearSelection();
        question_ennonce_TA.clear();
        question_difficulte_CB.getSelectionModel().clearSelection();
        alternative1_TA.clear();
        alternative2_TA.clear();
        alternative3_TA.clear();
        alternative4_TA.clear();
        reponse_correcte_LE.getSelectionModel().clearSelection();
        
        
        ObservableList<String> listModules = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        J_ajouterModule_CB.setItems(listModules);
        ObservableList<String> listAvancement = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        J_ajouterAvancement_CB.setItems(listAvancement);
        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.afficher();
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        //checked_CV.setCellFactory(column -> new CheckBoxTableCell());
        //checked_CV.setCellValueFactory(new PropertyValueFactory<>("check"));
        ListeCours_TV.setItems(ob_cours);

        ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        question_module_CB.setItems(listModule);

        ObservableList<String> listAvancemen = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        question_difficulte_CB.setItems(listAvancement);

        QuestionController qc = new QuestionController();
        ObservableList<Question> ob_question = qc.afficher();
        Reference_Question_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestion_TV.setItems(ob_question);
        
        
        Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("ajout confirmé").text("votre ajout a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.show();
        // TODO

    }

    @FXML
    private void afficher_questions(MouseEvent event) {
        if(listeQuestion_TV.getSelectionModel().getSelectedItem()!=null)
        {
            
                    supprimer_question_BT.setDisable(false);
        modifier_question_BT.setDisable(false);
        Question q = listeQuestion_TV.getSelectionModel().getSelectedItem();
        
        QuestionController qc = new QuestionController();
        ObservableList<Reponse> ob_question = qc.afficher_reponses(q);
        reponse_CV.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        vraiFaux_CV.setCellValueFactory(new PropertyValueFactory<>("correcte"));
        listeReponses_TV.setItems(ob_question);
              anchorpaneQuestionModif_AP.setVisible(false);

        }
    }

   @FXML
    private void rechercher_question(KeyEvent event) {

        QuestionController qc = new QuestionController();
        ObservableList<Question> ob_question = qc.rechercher(recherche_question_LE.getText());

        Reference_Question_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestion_TV.setItems(ob_question);
        

    }

    @FXML
    private void supprimer_cours(ActionEvent event) {
        Cours q = ListeCours_TV.getSelectionModel().getSelectedItem();
        Courscontroller ccc = new Courscontroller();
        ccc.setCours(q);
        ccc.supprimer();
        
        ObservableList<String> listModules = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        J_ajouterModule_CB.setItems(listModules);
        ObservableList<String> listAvancement = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        J_ajouterAvancement_CB.setItems(listAvancement);
        Courscontroller cc = new Courscontroller();
        ObservableList<Cours> ob_cours = cc.afficher();
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        ListeCours_TV.setItems(ob_cours);

        ObservableList<String> listModule = FXCollections.observableArrayList("juridique", "signaux", "priorités", "classements");
        question_module_CB.setItems(listModule);

        ObservableList<String> listAvancemen = FXCollections.observableArrayList("trivial", "facile", "moyen", "difficile", "complexe");
        question_difficulte_CB.setItems(listAvancement);

        QuestionController qc = new QuestionController();
        ObservableList<Question> ob_question = qc.afficher();
        Reference_Question_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        listeQuestion_TV.setItems(ob_question);
        
        Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("suppression confirmée").text("votre suppression a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.show();
       
        // TODO
        

    }

    
    
  
    @FXML
    private void ajouter_question_au_test(ActionEvent event) {
        

               
                       if(listeQuestionTEST_TV.getSelectionModel().getSelectedItem()!=null)
        {
        Question q = listeQuestionTEST_TV.getSelectionModel().getSelectedItem();
        
        data.add(q);
                Reference_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        
        listeQuestionChoisisTEST_TV.setItems(data);
        
    listeQuestionTEST_TV.getItems().remove(q);
        listeQuestionTEST_TV.setItems(listeQuestionTEST_TV.getItems());
        
      
       
        }
    }

    @FXML
    private void retirer_question_du_test(ActionEvent event) {
        
        
                       if(listeQuestionChoisisTEST_TV.getSelectionModel().getSelectedItem()!=null)
        {
        Question q = listeQuestionChoisisTEST_TV.getSelectionModel().getSelectedItem();
        
        data.remove(q);
                Reference_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("reference"));
        module_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("module"));
        difficulte_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
        Ennonce_Question_CV11.setCellValueFactory(new PropertyValueFactory<>("ennonce"));
        
        listeQuestionChoisisTEST_TV.setItems(data);
            listeQuestionTEST_TV.getItems().add(q);
        listeQuestionTEST_TV.setItems(listeQuestionTEST_TV.getItems());
        
    }
    }
    @FXML
    private void ajouter_test(ActionEvent event) {
        
        Test test=new Test();
        TestController tc=new TestController();
        test.setReference(Integer.parseInt(reference_teste_LE.getText()));
        test.setLibelle(libelle_LE.getText());
        test.setModule(moduleTest_CB.getSelectionModel().getSelectedItem());
        test.setDifficulte(difficulte_TEST_CB.getSelectionModel().getSelectedItem());
        tc.setTest(test);
        tc.ajouter();
        
        ArrayList<Question> questions =new ArrayList();
        for(Question q : listeQuestionChoisisTEST_TV.getItems())
        {
        questions.add(q);    
        }
        
        test.setQuestions(questions);
        if(questions.size()>=5){
        tc.affecter_questions();
          Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("ajout confirmé").text("votre ajout a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.show();
        }
        else {
            //Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("ajout reporté").text("votre ajout a été reporté veuillez ajouter plus de questions").hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.showWarning();
        }
                
        
    }

    @FXML
    private void modifier_cours(ActionEvent event) {
        
        
                Cours q = ListeCours_TV.getSelectionModel().getSelectedItem();
                if(q != null){
                   
                    modification_cours_labels.setVisible(true);
                            confirmer_modif_cours_BT.setVisible(true);

                    

         modif_cours_nom_LE.setText(q.getNom());
         modif_cours_module_CB.setValue(q.getModule());
        modif_cours_avancement_CB.setValue(q.getAvancement());
       
        
                        
                }


    }

    @FXML
    private void confirmer_modification_cours(ActionEvent event) {
        Cours q = ListeCours_TV.getSelectionModel().getSelectedItem();
                       Courscontroller ccc = new Courscontroller();   
         q.setNom(modif_cours_nom_LE.getText());
         q.setModule(modif_cours_module_CB.getSelectionModel().getSelectedItem());
         q.setAvancement(modif_cours_avancement_CB.getSelectionModel().getSelectedItem());
           ccc.setCours(q);
           
        ccc.modifier_cours();
        modification_cours_labels.setVisible(false);
        confirmer_modif_cours_BT.setVisible(false);
        
               ObservableList<Cours> ob_cours = ccc.afficher();
        nom_CV.setCellValueFactory(new PropertyValueFactory<>("nom"));
        module_CV.setCellValueFactory(new PropertyValueFactory<>("module"));
        avancement_CV.setCellValueFactory(new PropertyValueFactory<>("avancement"));
        reference_CV.setCellValueFactory(new PropertyValueFactory<>("reference"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        date_creation_CV.setCellValueFactory(new PropertyValueFactory<>("date_derniere_modification"));
        ListeCours_TV.setItems(ob_cours);
        
         Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("ce cours a bien été modifié").text("votre modification a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.show();
       

        
    }

    @FXML
    private void ajouterpdf(ActionEvent event) {
        
       Courspdf=new Cours();
        Courscontroller ccc = new Courscontroller();
        ccc.setCours(Courspdf);
        ccc.afficher_pdf();
        nom_cours_var_label.setText(Courspdf.getContenu().getName());

        
        
    }

    @FXML
    private void supprimer_question(ActionEvent event) {
        
        
         Question q = listeQuestion_TV.getSelectionModel().getSelectedItem();
        QuestionController ccc = new QuestionController();
        ccc.setQuestion(q);
        ccc.supprimer();
        listeQuestion_TV.setItems(ccc.afficher());
        listeReponses_TV.setItems(null);
        
        
    }

    @FXML
    private void modifier_question(ActionEvent event) {
        
        ObservableList<Reponse> r =listeReponses_TV.getItems();
        
        Question q=listeQuestion_TV.getSelectionModel().getSelectedItem();
        q.setListeDesReponses(r);
        if (q!=null){
            modif_ref_question_LE.setText(Integer.toString(q.getReference()));
      modif_module_ques_CB.setValue(q.getModule());
      modif_diff_ques_CB.setValue(q.getDifficulte());
        modif_ennonce_ques_TA.setText(q.getEnnonce());
        modif_rep1_ques_LE.setText(r.get(0).getReponse());
       modif_rep2_ques_LE.setText(r.get(1).getReponse());
       modif_rep3_ques_LE.setText(r.get(2).getReponse());
      modif_rep4_ques_LE.setText(r.get(3).getReponse());
      if(q.getListeDesReponses().get(0).getCorrecte()==true){
      moodif_rep_correcte_LE.setValue("alternative 1");
      }
      else if(q.getListeDesReponses().get(1).getCorrecte()==true){
      moodif_rep_correcte_LE.setValue("alternative 2");
      }
      else if(q.getListeDesReponses().get(2).getCorrecte()==true){
      moodif_rep_correcte_LE.setValue("alternative 3");
      }
      else if(q.getListeDesReponses().get(3).getCorrecte()==true){
      moodif_rep_correcte_LE.setValue("alternative 4");
      }
      anchorpaneQuestionModif_AP.setVisible(true);
        }
    }

    @FXML
    private void valider_modification_question(ActionEvent event) {
        
        Question q=new Question();
        QuestionController c=new QuestionController();
        q.setReference(Integer.parseInt(modif_ref_question_LE.getText()));
        q.setModule(modif_module_ques_CB.getValue());
      q.setDifficulte(modif_diff_ques_CB.getValue());
      q.setEnnonce(modif_ennonce_ques_TA.getText());
      List<Reponse> list=new ArrayList<Reponse>();
      list.add(new Reponse(modif_rep1_ques_LE.getText(),false));
      list.add(new Reponse(modif_rep2_ques_LE.getText(),false));
      list.add(new Reponse(modif_rep3_ques_LE.getText(),false));
      list.add(new Reponse(modif_rep4_ques_LE.getText(),false));
      if(moodif_rep_correcte_LE.getValue().equals("alternative 1")){
        list.get(0).setCorrecte(true);
    }
      else if(moodif_rep_correcte_LE.getValue().equals("alternative 2")){
             list.get(1).setCorrecte(true);
      }
      else if(moodif_rep_correcte_LE.getValue().equals("alternative 3")){
             list.get(2).setCorrecte(true);
      }
      else if(moodif_rep_correcte_LE.getValue().equals("alternative 4")){
             list.get(3).setCorrecte(true);
      }
       
      q.setListeDesReponses(list);
      c.setQuestion(q);
              c.modifier();
               Image img=new Image("/karhabty/gui/Capture.PNG");
        Notifications notification=Notifications.create().title("cette question a bien été modifié").text("votre modification a été éfféctué avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT);
       notification.show();
      anchorpaneQuestionModif_AP.setVisible(false);
      listeQuestion_TV.setItems(c.afficher());
      listeReponses_TV.setItems(c.afficher_reponses(q));
        
    }

    @FXML
    private void CoursChoisis(MouseEvent event) {
        
        if(ListeCours_TV.getSelectionModel().getSelectedItem()!=null)
        {
        modifier_cours_BT.setDisable(false);
        supprimer_cours_BT.setDisable(false);
        }
    }

    @FXML
    private void appuisEnDehor(MouseEvent event) {
            modifier_cours_BT.setDisable(true);
             supprimer_cours_BT.setDisable(true);
           
        
    }

    @FXML
    private void bouttonRetirerValiderActifs(MouseEvent event) {
        retirer_du_test_BT.setDisable(false);
        confirmer_TEST_BT.setDisable(false);
    }

    @FXML
    private void espaceExterieur(MouseEvent event) {
           retirer_du_test_BT.setDisable(true);
           ajouter_au_test_BT.setDisable(true);
        confirmer_TEST_BT.setDisable(true);
    }

    @FXML
    private void desactivationBott(MouseEvent event) {
        supprimer_question_BT.setDisable(true);
        modifier_question_BT.setDisable(true);
    }

    @FXML
    private void activbtsource(MouseEvent event) {
        ajouter_au_test_BT.setDisable(false);
        
    }

    @FXML
    private void controler_reference_question(KeyEvent event) {
        if(question_reference_LE.getText().equals("")){
            valider_ajout_question_BT.setDisable(true);
            labelleValide_LB.setVisible(false);
              controle_existance_LB.setVisible(false);
              Controle_nbr_LB.setVisible(false);
        } 
        else{
        
        if(!question_reference_LE.getText().matches("[0-9]+")){
            Controle_nbr_LB.setVisible(true);
            valider_ajout_question_BT.setDisable(true);
             labelleValide_LB.setVisible(false);
              controle_existance_LB.setVisible(false);
        }
        else if(!new QuestionController().referenceCntrolledeSaisie(Integer.parseInt(question_reference_LE.getText()))){
            controle_existance_LB.setVisible(true);
            valider_ajout_question_BT.setDisable(true);
            Controle_nbr_LB.setVisible(false);
            labelleValide_LB.setVisible(false);
            
        }
        else if(new QuestionController().referenceCntrolledeSaisie(Integer.parseInt(question_reference_LE.getText()))){
             valider_ajout_question_BT.setDisable(false);
             labelleValide_LB.setVisible(true);
             controle_existance_LB.setVisible(false);
              Controle_nbr_LB.setVisible(false);
        }
        }
        
    }
    
    
    
    
    
    

}
