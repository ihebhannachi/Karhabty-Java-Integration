/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;
import java.sql.Date;

/**
 *
 * @author PC NET
 */
public class Contact {
    private int idContacte; 
    private String NomSource; 
    private String emailDest; 
    private String Contenu; 
    private int Utilisateur_idUtilisateur; 
    private Date Date; 

    public Contact() {
    }

    public Contact(int idContacte, String NomSource, String emailDest, String Contenu, int Utilisateur_idUtilisateur, Date Date) {
        this.idContacte = idContacte;
        this.NomSource = NomSource;
        this.emailDest = emailDest;
        this.Contenu = Contenu;
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
        this.Date = Date;
    }

    public int getIdContacte() {
        return idContacte;
    }

    public void setIdContacte(int idContacte) {
        this.idContacte = idContacte;
    }

    public String getNomSource() {
        return NomSource;
    }

    public void setNomSource(String NomSource) {
        this.NomSource = NomSource;
    }

    public String getEmailDest() {
        return emailDest;
    }

    public void setEmailDest(String emailDest) {
        this.emailDest = emailDest;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public int getUtilisateur_idUtilisateur() {
        return Utilisateur_idUtilisateur;
    }

    public void setUtilisateur_idUtilisateur(int Utilisateur_idUtilisateur) {
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return "Contact{" + "idContacte=" + idContacte + ", NomSource=" + NomSource + ", emailDest=" + emailDest + ", Contenu=" + Contenu + ", Utilisateur_idUtilisateur=" + Utilisateur_idUtilisateur + ", Date=" + Date + '}';
    }
    
}

