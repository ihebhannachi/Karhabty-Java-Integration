/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.controller;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.util.Duration;
import project.karhabty.entities.Contact;
import project.karhabty.services.CrudContactService;
import project.karhabty.services.Mailing;
import project.karhabty.technical.Session;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

/**
 *
 * @author PC NET
 */
public class FXMLDocumentContactController implements Initializable {
@FXML
private JFXTextArea Message; 
@FXML
private JFXTextField Titre; 
    /**
     * Initializes the controller class.
     */
   @FXML
    public void SendButton(ActionEvent event) throws MessagingException {
        Mailing m = new Mailing(); 
        m.sendMail(Session.getEmail(), this.Titre.getText(), Message.getText());        
        Contact c = new Contact(); 
        c.setContenu(Message.getText());
        c.setNomSource(Session.getFirst_name());
        c.setEmailDest(Session.getEmail());
        c.setUtilisateur_idUtilisateur(Session.getId());

        CrudContactService cont = new CrudContactService(); 
        cont.AddContact(c);

        Notifications not = Notifications.create()
                       .title("Opération reussite ")
                       .text("Message envoyer !!")
                       .graphic(null)
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT);            
               not.darkStyle(); 
               not.showConfirm();
               
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
