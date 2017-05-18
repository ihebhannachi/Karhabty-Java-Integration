/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

/**
 *
 * @author PC NET
 */
public class Favorie {
   private int idFavorie; 
   private String Model; 
   private String Description; 
   private double Prix;
   private int Annonce_idAnnonce; 
   private int Utilisateur_idUtilisateur; 

    public Favorie() {
    }

    public Favorie(int idFavorie, String Model, String Description, double Prix, int Annonce_idAnnonce, int Utilisateur_idUtilisateur) {
        this.idFavorie = idFavorie;
        this.Model = Model;
        this.Description = Description;
        this.Prix = Prix;
        this.Annonce_idAnnonce = Annonce_idAnnonce;
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
    }

    public int getIdFavorie() {
        return idFavorie;
    }

    public void setIdFavorie(int idFavorie) {
        this.idFavorie = idFavorie;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public int getAnnonce_idAnnonce() {
        return Annonce_idAnnonce;
    }

    public void setAnnonce_idAnnonce(int Annonce_idAnnonce) {
        this.Annonce_idAnnonce = Annonce_idAnnonce;
    }

    public int getUtilisateur_idUtilisateur() {
        return Utilisateur_idUtilisateur;
    }

    public void setUtilisateur_idUtilisateur(int Utilisateur_idUtilisateur) {
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
    }

    @Override
    public String toString() {
        return "Favorie{" + "idFavorie=" + idFavorie + ", Model=" + Model + ", Description=" + Description + ", Prix=" + Prix + ", Annonce_idAnnonce=" + Annonce_idAnnonce + ", Utilisateur_idUtilisateur=" + Utilisateur_idUtilisateur + '}';
    }
   
}
