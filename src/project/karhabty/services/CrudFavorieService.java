/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.karhabty.technical.DBConfig;
import project.karhabty.entities.Annonce;
import project.karhabty.entities.Favorie;
import project.karhabty.Iservices.IServiceFavorie;
import project.karhabty.technical.Session;
/**
 *
 * @author PC NET
 */
public class CrudFavorieService implements IServiceFavorie{
    private Connection con;
    private ObservableList<Favorie> data;
    private static String AnnonceMarque; 
    private static double AnnoncePrix; 
    private static String AnnonceDescription; 
    private List<Favorie> Listfav; 
    public CrudFavorieService() {
        this.con = DBConfig.getInstance().getConnection();
        this.Listfav = new ArrayList<>(); 
    }
    public void AddFavorie(Favorie f) {
        try {
            String requete = "INSERT INTO favorie (Marque,Description,Prix,Annonce_idAnnonce,Utilisateur_idUtilisateur) VALUES (?,?,?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setString(1, AnnonceMarque);
            pst.setString(2, AnnonceDescription);
            pst.setDouble(3, AnnoncePrix);
            pst.setInt(4, f.getAnnonce_idAnnonce());
            pst.setInt(5, f.getUtilisateur_idUtilisateur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme d'ajout ");
            ex.printStackTrace();
        }
    }
    
    public void Display() throws SQLException {
    data = FXCollections.observableArrayList();
        String requete = "SELECT * FROM favorie WHERE Utilisateur_idUtilisateur=? ";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            data.add(new Favorie(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getInt(5), res.getInt(6)));
        }    
    }
    public void FavorieToList() throws SQLException {
        String requete = "SELECT * FROM favorie WHERE Utilisateur_idUtilisateur=? ";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
          Listfav.add(new Favorie(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getInt(5), res.getInt(6)));
        }         
    }
public void AllFaovorieToList(int id) throws SQLException {
    String requete = "SELECT * FROM favorie WHERE Annonce_idAnnonce=? and Utilisateur_idUtilisateur!=? ";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, id);
        pst.setInt(2, Session.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
          Listfav.add(new Favorie(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getInt(5), res.getInt(6)));
        }
}    
    public void DeleteFavorie(int id) {
        try {
            String requete = "DELETE FROM favorie WHERE idFavorie = ?";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Personne supprimer");
        } catch (SQLException ex) {
            System.err.println("Remove faild");
        }
    }
    public void GetAnnoncePrixDescription(int id) throws SQLException {
        String requete = "SELECT * FROM annonce WHERE idAnnonce=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        res.first();
        AnnonceMarque= res.getString("Marque"); 
        AnnoncePrix = res.getDouble("Prix"); 
        AnnonceDescription = res.getString("Description"); 
    }
    public boolean ExisteAnnonce(int id) {
      return this.Listfav.stream().anyMatch(p->p.getAnnonce_idAnnonce()==id); 
    }
    public int NumberAnnonce() {
        return (int) this.Listfav.stream().count(); 
    }
    public ObservableList<Favorie> getObs() {
        return this.data;
    }
}
