/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author KAMOUN
 */
public class Voiture {
    private String matricule;
    private int proprietaire;
    private int marque;
    private int modele;
    private LocalDate DateMiseCirculation;
    private int puissanceFiscale;
    private int puissanceDynamique;
    private String carburant; 
    private String transmission;
    private int kilometrage;
    /////////////
    private String modeleS;
    private String marqueS;

    public Voiture()
    {
        
    }
    public Voiture(String matricule,String marqueS, String modeleS, LocalDate DateMiseCirculation, int puissanceFiscale, int puissanceDynamique, String carburant, String transmission, int kilometrage) {
        this.matricule = matricule;
        this.proprietaire= proprietaire;
        this.marqueS = marqueS;
        this.modeleS = modeleS;
        this.DateMiseCirculation = DateMiseCirculation;
        this.puissanceFiscale = puissanceFiscale;
        this.puissanceDynamique = puissanceDynamique;
        this.carburant=carburant;
        this.transmission=transmission;
        this.kilometrage = kilometrage;
    }

    public LocalDate getDateMiseCirculation() {return DateMiseCirculation;}
    public int getProprietaire() {return proprietaire;}
    public int getKilometrage() {return kilometrage;}
    public int getMarque() {return marque;}
    public int getModele() {return modele;}
    public String getMatricule() {return matricule;}
    public int getPuissanceDynamique() {return puissanceDynamique;}
    public int getPuissanceFiscale() {return puissanceFiscale;}
    public String getCarburant(){return carburant;}
    public String getTransmission(){return transmission;}
    ///////////////
    public String getMarqueS(){return marqueS;}
    public String getModeleS(){return modeleS;}
    /////////////////SETTERS/////////////////////

    public void setDateMiseCirculation(LocalDate DateMiseCirculation) {this.DateMiseCirculation = DateMiseCirculation;}
    public void setProprietaire(int proprietaire){this.proprietaire=proprietaire;}
    public void setKilometrage(int kilometrage) {this.kilometrage = kilometrage;}
    public void setMarque(int marque) {this.marque = marque;}
    public void setMatricule(String matricule) {this.matricule = matricule;}
    public void setModele(int modele) {this.modele = modele;}
    public void setPuissanceDynamique(int puissanceDynamique) {this.puissanceDynamique = puissanceDynamique;}
    public void setPuissanceFiscale(int puissanceFiscale) {this.puissanceFiscale = puissanceFiscale;}
    public void setCarburant(String carburant){this.carburant=carburant;}
    public void setTransmission(String transmission){this.transmission=transmission;}
    //////////////////////////
    public void setModeleS (String modeleS){this.modeleS=modeleS;}
    public void setMarqueS(String marqueS){this.marqueS=marqueS;}
////////////////////////////////////////
    public String toString()
    {
        return this.getMatricule()+" "+this.getProprietaire()+" "+this.getMarque()+" "+this.getModele()+" "+this.getCarburant()+" "+this.getTransmission()+" "+this.getDateMiseCirculation()+" "+this.getPuissanceFiscale()+" "+this.getPuissanceDynamique()+" "+this.getKilometrage();
    }
}
