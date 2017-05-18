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
public class Alert {
    private int idAlert; 
    private String Marque; 
    private String Modele; 
    private int Utilisateur_idUtilisateur; 

    public Alert() {
    }

    public Alert(int idAlert, String Marque, String Modele, int Utilisateur_idUtilisateur) {
        this.idAlert = idAlert;
        this.Marque = Marque;
        this.Modele = Modele;
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
    }

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public String getModele() {
        return Modele;
    }

    public void setModele(String Modele) {
        this.Modele = Modele;
    }

    public int getUtilisateur_idUtilisateur() {
        return Utilisateur_idUtilisateur;
    }

    public void setUtilisateur_idUtilisateur(int Utilisateur_idUtilisateur) {
        this.Utilisateur_idUtilisateur = Utilisateur_idUtilisateur;
    }

    @Override
    public String toString() {
        return "Alert{" + "idAlert=" + idAlert + ", Marque=" + Marque + ", Modele=" + Modele + ", Utilisateur_idUtilisateur=" + Utilisateur_idUtilisateur + '}';
    }
    
}
