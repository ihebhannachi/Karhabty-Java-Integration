/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.time.LocalDate;

/**
 *
 * @author KAMOUN
 */
public class Carburant {
    private int id;
    private String voiture;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int distanceParcourrue;
    private double montant;
    
    public Carburant() {
        
    }

    public Carburant(String voiture, LocalDate dateDebut, LocalDate dateFin, double montant, int distanceParcourrue) {
        this.voiture=voiture;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.distanceParcourrue=distanceParcourrue;
        this.montant=montant;
    }
    
    public int getId(){return id;}   
    public String getVoiture(){return voiture;}     
    public LocalDate getDateDebut(){return dateDebut;}     
    public LocalDate getDateFin(){return dateFin;}     
    public int getDistanceParcourrue(){return distanceParcourrue;}   
    public double getMontant(){return montant;}

    public void setId(int id){this.id=id;}
    public void setVoiture(String voiture){this.voiture=voiture;}
    public void setDateDebut(LocalDate dateDebut){this.dateDebut=dateDebut;}
    public void setDateFin(LocalDate dateFin){this.dateFin=dateFin;}
    public void setDistanceParcourrue(int distanceParcourrue){this.distanceParcourrue=distanceParcourrue;}
    public void setMontant(double montant){this.montant=montant;}
}
