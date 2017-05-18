package project.karhabty.technical;



import project.karhabty.Iservices.UsersIServices; 
import project.karhabty.services.UsersServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by iheb on 09/02/2017.
 *
 */
public class Mail {
    private String token;
    public  boolean confirmationMail(String Email){

        // Recipient's email ID needs to be mentioned.
        String to = Email;
        //Get User info
        UsersIServices user = new UsersServices();
        ResultSet result =user.getUser_infoByEmail(Email);

        // Sender's email ID needs to be mentioned
        String from = "chikitos.tunisie@gmail.com";
        final String username = "chikitos.tunisie@gmail.com";//change accordingly
        final String password = "azerty@my.com";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            while (result.next()){
                 token =result.getString("confirmation_token");
            }
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Activation de votre compte");

            // Now set the actual message
            message.setText("Bonjour MR ,\n" +
                    "Nous vous remercions davoir rejoint chikitos-tunisie.\n" +
                    "Veuillez cliquer sur le lien ci-dessous pour confirmer votre inscription et activer votre compte sur Karhabty.com.\n" +
                    "http://localhost/karhabty/activation.php?email="+Email+"&token="+token+"\n" +
                    "Vous trouverez sous la rubrique « Mon compte » les informations enregistrées vous concernant. Merci de prendre le temps de vérifier que ces informations sont correctes. Si vous n’avez pas ouvert un nouveau compte Karhabty, vous pouvez ignorer cet email sans cliquer sur le lien ci-dessus. Veuillez ne pas répondre à cet email, car toute réponse envoyée à cette adresse ne sera pas traitée. Pour nous contacter, connectez-vous à votre compte et cliquez sur Contactez-nous en bas de nimporte quelle page.\n" +
                    "Bien à vous,\n" +
                    "www.Karhabty.com");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            return true;

        } catch (MessagingException | SQLException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, e);
            //throw new RuntimeException(e);
            return false;
        }

    }
    public  boolean passwordRequest(String Email){
        // Recipient's email ID needs to be mentioned.
        String to = Email;

        // Sender's email ID needs to be mentioned
        String from = "chikitos.tunisie@gmail.com";
        final String username = "chikitos.tunisie@gmail.com";//change accordingly
        final String password = "azerty@my.com";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            UsersIServices user = new UsersServices();
            String token =user.set_confirmation_token(Email);
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Réinitialiser Mot de Passe");

            // Now set the actual message
            message.setText("Bonjour Mr ,\n" +
                    "Veuillez cliquer sur le lien ci-dessous pour Réinitialiser votre Mot de Passe .\n" +
                    "http://localhost/karhabty-web/web/app_dev.php/resetting/reset/"+token+"\n" +
                    "Cordialement,");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            return true;

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, e);
            //throw new RuntimeException(e);
            return false;
        }
    }
}
