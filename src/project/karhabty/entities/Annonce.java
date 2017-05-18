/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;
import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author PC NET
 */
public class Annonce {
    private int idAnnonce; 
    private String Marque; 
    private String Modele; 
    private int Kilo; 
    private double Prix; 
    private String Description; 
    private File Photo; 
    private Date DateAnnonce; 
    private String Adresse; 
    private int Telehphone; 
    private String DateCirculation; 
    private String Energie; 
    private String PuissanceFiscale; 
    private String Couleur; 
    private String Boite; 
    private int IdUser;
    private ImageView img; 
    public Annonce() {
        
    }

    public Annonce(String Marque, String Modele, int Kilo, double Prix, String Description, File Photo, String Adresse, int Telehphone, String DateCirculation, String Energie, String PuissanceFiscale, String Couleur, String Boite) {
        this.Marque = Marque;
        this.Modele = Modele;
        this.Kilo = Kilo;
        this.Prix = Prix;
        this.Description = Description;
        this.Photo = Photo;
        this.Adresse = Adresse;
        this.Telehphone = Telehphone;
        this.DateCirculation = DateCirculation;
        this.Energie = Energie;
        this.PuissanceFiscale = PuissanceFiscale;
        this.Couleur = Couleur;
        this.Boite = Boite;
    }

    public Annonce(int idAnnonce, String Marque, String Modele, int Kilo, double Prix, String Description, Date DateAnnonce, String Adresse, int Telehphone, String DateCirculation, String Energie, String PuissanceFiscale, String Couleur, String Boite, int IdUser,ImageView img) {
        this.idAnnonce = idAnnonce;
        this.Marque = Marque;
        this.Modele = Modele;
        this.Kilo = Kilo;
        this.Prix = Prix;
        this.Description = Description;
        this.DateAnnonce = DateAnnonce;
        this.Adresse = Adresse;
        this.Telehphone = Telehphone;
        this.DateCirculation = DateCirculation;
        this.Energie = Energie;
        this.PuissanceFiscale = PuissanceFiscale;
        this.Couleur = Couleur;
        this.Boite = Boite;
        this.IdUser = IdUser;
        this.img = img; 
    }
    
    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setModele(String Modele) {
        this.Modele = Modele;
    }

    public void setKilo(int Kilo) {
        this.Kilo = Kilo;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPhoto(File Photo) {
        this.Photo = Photo;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setTelehphone(int Telehphone) {
        this.Telehphone = Telehphone;
    }

    public void setDateCirculation(String DateCirculation) {
        this.DateCirculation = DateCirculation;
    }

    public void setEnergie(String Energie) {
        this.Energie = Energie;
    }

    public void setPuissanceFiscale(String PuissanceFiscale) {
        this.PuissanceFiscale = PuissanceFiscale;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public void setBoite(String Boite) {
        this.Boite = Boite;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public String getMarque() {
        return Marque;
    }

    public String getModele() {
        return Modele;
    }

    public int getKilo() {
        return Kilo;
    }

    public double getPrix() {
        return Prix;
    }

    public String getDescription() {
        return Description;
    }

    public File getPhoto() {
        return Photo;
    }

    public Date getDateAnnonce() {
        return DateAnnonce;
    }

    public String getAdresse() {
        return Adresse;
    }

    public int getTelehphone() {
        return Telehphone;
    }

    public String getDateCirculation() {
        return DateCirculation;
    }

    public String getEnergie() {
        return Energie;
    }

    public String getPuissanceFiscale() {
        return PuissanceFiscale;
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getBoite() {
        return Boite;
    }

    public int getIdUser() {
        return IdUser;
    }

    @Override
    public String toString() {
        return "Annonce{" + "Marque=" + Marque + ", Modele=" + Modele + ", Kilo=" + Kilo + ", Prix=" + Prix + ", Description=" + Description + ", Photo=" + Photo.getName() + ", Adresse=" + Adresse + ", Telehphone=" + Telehphone + ", DateCirculation=" + DateCirculation + ", Energie=" + Energie + ", PuissanceFiscale=" + PuissanceFiscale + ", Couleur=" + Couleur + ", Boite=" + Boite + '}';
    }
    
}

