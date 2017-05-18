/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.karhabty.technical.DBConfig; 
import project.karhabty.entities.Alert;
import project.karhabty.entities.Annonce;
import project.karhabty.Iservices.IServiceAnnonce;
import project.karhabty.technical.Session; 
import javax.mail.MessagingException;
/**
 *
 * @author PC NET
 */
public class CrudAnnonceService implements IServiceAnnonce{

    private Connection con;
    private ObservableList<Annonce> data;
    private static List<Annonce> ListAnnonce = new ArrayList<Annonce>();

    public CrudAnnonceService() {
        this.con = DBConfig.getInstance().getConnection(); 
         
    }

    public void AddAnnonce(Annonce a) throws FileNotFoundException, SQLException, MessagingException {
        CrudAlertServices al = new CrudAlertServices(); 
        al.AlertToList();
        al.DisplayList();
        System.out.println(al.AlertExiste(a.getMarque(), a.getModele()));
        if (al.AlertExiste(a.getMarque(), a.getModele())) {
            for (Alert s : al.GetList(a.getMarque(), a.getModele())) {                
                 System.out.println(GetMail(s.getUtilisateur_idUtilisateur()));
                 Mailing m = new Mailing(); 
                 m.sendMail(GetMail(s.getUtilisateur_idUtilisateur()), "Alert de recherche ", "annnonce trouvé !!!");
            }
        }
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            String requete = "INSERT INTO annonce (Marque,Modele,Kilo,Prix,Description,Photo,DateAnnonce,Adresse,Telephone,Datecirculation,Energie,Puissancefiscale,Couleur,Boite,IdUser) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setString(1, a.getMarque());
            pst.setString(2, a.getModele());
            pst.setInt(3, a.getKilo());
            pst.setDouble(4, a.getPrix());
            pst.setString(5, a.getDescription());
            pst.setString(6,"Logo1.png");
            pst.setDate(7, date);          
            pst.setString(8, a.getAdresse());
            pst.setInt(9, a.getTelehphone());
            pst.setString(10, a.getDateCirculation());
            pst.setString(11, a.getEnergie());
            pst.setString(12, a.getPuissanceFiscale());
            pst.setString(13, a.getCouleur());
            pst.setString(14, a.getBoite());
            pst.setInt(15,Session.getId());
            pst.executeUpdate(); 
        } catch (SQLException ex) {
            System.err.println("Probleme d'ajout ");
        }
        CrudNotif notif = new CrudNotif(); 
        int nbr = notif.RecupNbrAnnonce(); 
        notif.IncrementNbrAnnonce(nbr);
        System.out.println(notif.RecupNbrAnnonce());
    }
    public void OderByAnnonce(String order) throws SQLException {
        data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce WHERE IdUser=? order by "+order;
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt(Session.getId()), null));
        }
    }
    public void AdminOrderByAnnonce(String order) throws SQLException {
        data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce order by "+order;
        PreparedStatement pst = this.con.prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"),res.getInt("IdUser"), null));
        }
    }
    public ResultSet ExportData() throws SQLException {
        String requete = "SELECT * FROM annonce";
        PreparedStatement pst = this.con.prepareStatement(requete);
        return pst.executeQuery(); 
    }
    public void UpdateAnnonce(Annonce a,int id) throws FileNotFoundException, SQLException {
        

        InputStream inputStream = new FileInputStream(a.getPhoto());
        try {
            String requete = "UPDATE annonce SET Marque=?,Modele=?,Kilo=?,Prix=?,Description=?,Photo=?,Adresse=?,Telephone=?,Datecirculation=?,Energie=?,Puissancefiscale=?,Couleur=?,Boite=?,IdUser=? WHERE idAnnonce=?";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setString(1, a.getMarque());
            pst.setString(2, a.getModele());
            pst.setInt(3, a.getKilo());
            pst.setDouble(4, a.getPrix());
            pst.setString(5, a.getDescription());
            pst.setString(6,"Logo1.png");
            pst.setString(7, a.getAdresse());
            pst.setInt(8, a.getTelehphone());
            pst.setString(9, a.getDateCirculation());
            pst.setString(10, a.getEnergie());
            pst.setString(11, a.getPuissanceFiscale());
            pst.setString(12, a.getCouleur());
            pst.setString(13, a.getBoite());
            pst.setInt(14, Session.getId());
            pst.setInt(15, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme de modification ");
        }

    }
    public void DisplayMyAnnonce() throws SQLException {
        data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce WHERE IdUser=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt("idUser"), null));
        }
    }
    public void AdminDisplayAnnonce() throws SQLException {
       data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce";
        PreparedStatement pst = this.con.prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt("IdUser"), null));
        } 
    }

    public void DeleteMyAnnonce(int id) {
        try {
            String requete = "DELETE FROM annonce WHERE idAnnonce = ?";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Personne supprimer");
        } catch (SQLException ex) {
            System.err.println("Remove faild");
        }
    }

    public ImageView SelectImage(int id) throws SQLException {
        String requete = "SELECT Photo FROM annonce WHERE idAnnonce=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        res.first();
        Blob b = res.getBlob("Photo");
        byte[] ndata = b.getBytes(1, (int) b.length());
        Image img = new Image(new ByteArrayInputStream(ndata));
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);
        return imageView;
    }

    public void SearchAnnonce(int id) throws SQLException {
        String requete = "SELECT * FROM annonce WHERE idAnnonce=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            ListAnnonce.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt("idUser"),null));
        }
    }

    public static Annonce getOneAnnonce() {
        return ListAnnonce.stream().findAny().get();
    }
//public 
    public void DisplayAllAnnonce() throws SQLException {
        data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce WHERE IdUser!=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt("IdUser"),null));
        }
    }
    public void OrderByAllAnnonce(String order) throws SQLException {
        data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM annonce WHERE IdUser!=? order by "+order;
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Annonce(res.getInt("idAnnonce"), res.getString("Marque"), res.getString("Modele"), res.getInt("Kilo"), res.getDouble("Prix"), res.getString("Description"), res.getDate("DateAnnonce"), res.getString("Adresse"), res.getInt("Telephone"), res.getString("Datecirculation"), res.getString("Energie"), res.getString("Puissancefiscale"), res.getString("Couleur"), res.getString("Boite"), res.getInt("IdUser"), null));
        }
    }
    public String GetMail(int id) throws SQLException {
        String requete = "SELECT * FROM users WHERE id=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        res.first();
        return res.getString(4); 
    }
    public ObservableList<Annonce> getObs() {
        return this.data;
    }
}