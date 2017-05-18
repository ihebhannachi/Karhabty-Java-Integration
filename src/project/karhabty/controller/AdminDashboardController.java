package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.AdminIServices;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.entities.Users;
import project.karhabty.services.AdminServices;
import project.karhabty.services.UsersServices;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 11/02/2017.
 * This Class represent ui_admindashboard.fxml Controller
 */
public class AdminDashboardController implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<Users> tableview;

    @FXML
    private TableColumn<Users, Integer> id;

    @FXML
    private TableColumn<Users, String> last_name;

    @FXML
    private TableColumn<Users, String> first_name;

    @FXML
    private TableColumn<Users, String> email;

    @FXML
    private TableColumn<Users, Integer> tel_num;

    @FXML
    private TableColumn<Users, Integer> mobile_num;

    @FXML
    private TableColumn<Users, String> address;

    @FXML
    private TableColumn<Users, Date> last_login;

    @FXML
    private TableColumn<Users, Date> creation_date;

    @FXML
    private TableColumn<Users, Boolean> enabled;

    @FXML
    private JFXButton disable_btn;

    @FXML
    private JFXButton enable_btn;
    @FXML
    private Text account_number;
    @FXML
    private Text account_number_yearly;
    @FXML
    private Text account_disabled;
    @FXML
    private Text requested_pwd_num;
    @FXML
    private TextField search;
    private int value;
    private static boolean isLoaded=false;
    @FXML
    private ObservableList<Users> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (isLoaded){
             /*notificaton*/
            AdminIServices admin = new AdminServices();
            admin.notif_newusers();
            /**/
        }
        if (!isLoaded) {
            loadwelcameScreen();
        }


        /* Initialize table view contents.*/

        try {

            UsersIServices user = new UsersServices() ;
            ResultSet result = user.getUsers_info();
            while (result.next()){
                data.addAll(new Users(result.getInt("id"),result.getString("First_name")
                        ,result.getString("Last_name")
                        ,result.getString("email")
                        ,result.getInt("Mobile")
                        ,result.getInt("Tel")
                        ,result.getString("Address")
                        ,result.getDate("last_login")
                        ,result.getDate("Creation_date")
                        ,result.getBoolean("enabled")));

            }

            id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            mobile_num.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
            tel_num.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            last_login.setCellValueFactory(new PropertyValueFactory<>("last_login"));
            creation_date.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
            enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));

            tableview.setItems(data);
            /*Enable block button*/
            tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(tableview.getSelectionModel().getSelectedItem() != null)
            {
                value=tableview.getSelectionModel().getSelectedItem().getUser_id();
                if(tableview.getSelectionModel().getSelectedItem().getEnabled()){
                    enable_btn.setDisable(true);
                    disable_btn.setDisable(false);
                }else {
                    disable_btn.setDisable(true);
                    enable_btn.setDisable(false);
                }
            }
            });
            /*Search in table */
            search.textProperty().addListener(observable -> {
                if (search.getText().isEmpty()){
                    tableview.setItems(data);
                    return;
                }

                ObservableList<Users> tableItems = FXCollections.observableArrayList();

                ObservableList<TableColumn<Users, ?>> cols = tableview.getColumns();

                for (Users aData : data) {


                    for (TableColumn<Users, ?> col : cols) {

                        //TableColumn col = cols.get(j);

                        String cellValue = String.valueOf(col.getCellData(aData)).toLowerCase();

                        //cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(search.textProperty().get().toLowerCase())) {

                            tableItems.add(aData);

                            break;

                        }

                    }


                }

                tableview.setItems(tableItems);
            });

            /*Initialize slide panel contents*/
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
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
        //Statistic
        AdminIServices admin = new AdminServices();
        account_number.setText("Nombre des Comptes Créer (depuis le lancement de l'application : "+admin.account_created()+" Comptes");
        account_number_yearly.setText("Nombre des Comptes Créer Cette année est : "+admin.account_created_yearly()+" Comptes");
        account_disabled.setText("Nombre des Comptes Bloquée est : "+admin.account_disabled()+" Comptes");
        requested_pwd_num.setText("Nombre de comptes demandant un mot de passe au moin une fois est : "+admin.requested_password_number()+" Comptes");

    }
    public void handleButtonAction(ActionEvent event){
        AdminIServices admin = new AdminServices();
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Bloquer compte":
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmer modification");
                alert.setContentText("Vous êtes sûre ?");
                Optional<ButtonType> result =alert.showAndWait();
                if (result.get()==ButtonType.OK) {
                    admin.disable_user(value);
                    refresh_tableview();
                    System.out.println(value);
                }

                break;
            case "Débloquer compte":
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation");
                alert1.setHeaderText("Confirmer modification");
                alert1.setContentText("Vous êtes sûre ?");
                Optional<ButtonType> result1 =alert1.showAndWait();
                if (result1.get()==ButtonType.OK) {
                    admin.enable_user(value);
                    refresh_tableview();
                    System.out.println(value);
                }

                break;
            case "Generate csv backup":
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Confirmation");
                alert2.setHeaderText(null);
                alert2.setContentText("Vous êtes sûre ?");
                Optional<ButtonType> result2 =alert2.showAndWait();
                if (result2.get()==ButtonType.OK) {
                    if (admin.generate_backup()){
                        Image img = new Image("/img/confirm.png");
                        Notifications notificationBuilder= Notifications.create()
                                .title("Notification")
                                .text("Le fichier users.csv a ete créer avec success")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(10))
                                .position(Pos.TOP_RIGHT);
                        notificationBuilder.show();
                    }
                }
                break;

        }
    }
    private  void refresh_tableview(){
        try {
            UsersIServices user = new UsersServices() ;
            ResultSet result = user.getUsers_info();
            data.removeAll(tableview.getItems());
            while (result.next()){
                data.addAll(new Users(result.getInt("id"),result.getString("First_name")
                        ,result.getString("Last_name")
                        ,result.getString("email")
                        ,result.getInt("Mobile")
                        ,result.getInt("Tel")
                        ,result.getString("Address")
                        ,result.getDate("last_login")
                        ,result.getDate("Creation_date")
                        ,result.getBoolean("enabled")));

            }

            id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            mobile_num.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
            tel_num.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            last_login.setCellValueFactory(new PropertyValueFactory<>("last_login"));
            creation_date.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
            enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
            tableview.getItems().removeAll(new Users());
            tableview.setItems(data);
        } catch ( SQLException e) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void loadwelcameScreen() {
        isLoaded=true;
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(("/project/karhabty/GUI/ui_welcame.fxml")));
            anchorpane.getChildren().setAll(pane);


            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/project/karhabty/GUI/ui_admindashboard.fxml")));
                    anchorpane.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });


        } catch (IOException e) {
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }


    }
}
