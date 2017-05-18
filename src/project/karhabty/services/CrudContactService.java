/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import project.karhabty.technical.DBConfig;
import project.karhabty.entities.Contact;
import project.karhabty.Iservices.IserviceContact;
import project.karhabty.technical.Session;
/**
 *
 * @author PC NET
 */
public class CrudContactService implements IserviceContact{
    private Connection con;
    public CrudContactService() {
        this.con = DBConfig.getInstance().getConnection(); 
    }
    public void AddContact(Contact a) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            String requete = "INSERT INTO contacte (NomSource,emailDest,Contenu,Date,Utilisateur_idUtilisateur) VALUES (?,?,?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setString(1, a.getNomSource());
            pst.setString(2, a.getEmailDest());
            pst.setString(3, a.getContenu());
            pst.setDate(4,date );
            pst.setInt(5, a.getUtilisateur_idUtilisateur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme d'ajout ");
            ex.printStackTrace();
        }
    }
}
