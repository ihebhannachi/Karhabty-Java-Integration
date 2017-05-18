package project.karhabty.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.entities.Users;
import project.karhabty.services.UsersServices;
import project.karhabty.technical.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 09/02/2017.
 */
public class UserAccountSettingsController implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXTextField First_name;

    @FXML
    private JFXTextField Last_name;

    @FXML
    private JFXTextField Mobile_num;

    @FXML
    private JFXTextField Tel_num;

    @FXML
    private JFXTextField Address;

    @FXML
    private JFXTextField Email;

    @FXML
    private JFXPasswordField NewPassword;

    @FXML
    private JFXPasswordField Confirm_password;

    @FXML
    private JFXPasswordField OldPassword;
    @FXML
    private Label oldpasswordlabel;
    @FXML
    private Label confirmpasswordlabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            UsersIServices user = new UsersServices() ;
            ResultSet result=user.getUser_info(Session.getId());
            while (result.next()){
                First_name.setText(result.getString("First_name"));
                Last_name.setText(result.getString("Last_name"));
                Mobile_num.setText(result.getString("Mobile"));
                Tel_num.setText(result.getString("Tel"));
                Address.setText(result.getString("Address"));
                Email.setText(result.getString("email"));

            }
            /*Validate Old Password*/
            OldPassword.textProperty().addListener((observable, oldValue, newValue) -> {

                if (user.check_password(OldPassword.getText())){
                    oldpasswordlabel.setTextFill(Color.GREEN);
                    oldpasswordlabel.setText("Password correct");
                }else {
                    oldpasswordlabel.setTextFill(Color.RED);
                    oldpasswordlabel.setText("Password incorrect");
                }
            });
            /*Validate Confirmation password*/
            Confirm_password.textProperty().addListener((observable, oldValue, newValue) -> {
                if (Confirm_password.getText().equals(NewPassword.getText())){
                    confirmpasswordlabel.setTextFill(Color.GREEN);
                    confirmpasswordlabel.setText("Mot de passe correspond");
                }else {
                    confirmpasswordlabel.setTextFill(Color.RED);
                    confirmpasswordlabel.setText("Mot de passe ne correspond pas");
                }
            });
            /*Loading Slide panel Contents*/
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
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void Refresh(){
        try {
        UsersIServices user = new UsersServices() ;
        ResultSet result=user.getUser_info(Session.getId());
            while (result.next()){
                First_name.setText(result.getString("First_name"));
                Last_name.setText(result.getString("Last_name"));
                Mobile_num.setText(result.getString("Mobile"));
                Tel_num.setText(result.getString("Tel"));
                Address.setText(result.getString("Address"));
                Email.setText(result.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void Update_info(ActionEvent event){
        Users updateuser = new Users();
        Image img = new Image("/img/confirm.png");
        updateuser.setFirst_name(First_name.getText());
        updateuser.setLast_name(Last_name.getText());
        updateuser.setMobile_number(Integer.parseInt(Mobile_num.getText()));
        updateuser.setTel_number(Integer.parseInt(Tel_num.getText()));
        updateuser.setAddress(Address.getText());
        UsersIServices user = new UsersServices() ;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmer modification");
        alert.setContentText("Vous êtes sûre ?");
        Optional<ButtonType> result =alert.showAndWait();
        if (result.get()==ButtonType.OK) {
            if (user.update_user_info(updateuser)) {
                System.out.println("user updated");
                Refresh();
                Notifications notificationBuilder= Notifications.create()
                        .title("Modification")
                        .text("Votre information personnel on ete modifier")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT);
                notificationBuilder.show();
            }
        }
    }
    public  void Update_password(ActionEvent event){
        Users updateuserpassword = new Users();
        UsersIServices user = new UsersServices() ;
        Image img = new Image("/img/confirm.png");
        if (OldPassword.getText().isEmpty() | Confirm_password.getText().isEmpty() | NewPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ATTENTION");
            alert.setHeaderText("VERIFIER LES CHAMPS");
            alert.setContentText("TOUT LES SONT OBLIGATOIRE");
            alert.showAndWait();
        }else {
            updateuserpassword.setPassword(OldPassword.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmer modification");
            alert.setContentText("Vous êtes sûre ?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                if (user.reset_user_password(updateuserpassword)) {
                    System.out.println("user password updated");
                    Refresh();
                    Notifications notificationBuilder= Notifications.create()
                            .title("Modification")
                            .text("Votre information de securité on été modifié")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(10))
                            .position(Pos.TOP_RIGHT);
                    notificationBuilder.show();
                }
            }


        }

    }

}
