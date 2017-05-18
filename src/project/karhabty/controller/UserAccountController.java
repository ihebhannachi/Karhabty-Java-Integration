package project.karhabty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import project.karhabty.Iservices.UsersIServices;
import project.karhabty.services.UsersServices;
import project.karhabty.technical.Session;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by iheb on 05/02/2017.
 *
 */
public class UserAccountController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView profile_image;

    @FXML
    private JFXButton image_btn;
    @FXML
    private Text username_text;
    private static boolean isLoaded=false;
    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!isLoaded) {
            loadwelcameScreen();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }
        UsersIServices user = new UsersServices() ;
        
         profile_image.setImage(user.get_ProfileImage());
         profile_image.setFitWidth(150);
         profile_image.setFitHeight(150);

         //User profil Info
        username_text.setText(Session.getFirst_name()+" "+Session.getLast_name());

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
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/project/karhabty/GUI/ui_useraccount.fxml")));
                    anchorpane.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });


        } catch (IOException e) {
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, e);
        }


    }
    public void GetImg(ActionEvent event) {
        UsersIServices user = new UsersServices() ;
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        this.file = fileChooser.showOpenDialog(app_stage);
        if (file!= null) {
            //image_btn.setText(file.getName());
            if (user.set_ProfileImage(file))
            {
                Image img = new Image("/img/image_add.png");
                Notifications notificationBuilder = Notifications.create()
                        .title("Infromation")
                        .text("l'image de profile a ete modifier ")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.TOP_RIGHT);
                notificationBuilder.show();
                System.out.println("image ajouter");
            }
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                profile_image.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
