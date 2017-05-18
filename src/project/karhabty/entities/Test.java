/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author PROXYINFO
 */
public class Test {
    
    private int reference;
    private String libelle;
    private String difficulte;
    private String module;
    private Date date_creation;
    private Date date_derniere_modification;
    private ArrayList<Question> questions;

    public Test() {
    }

    public Test(int reference, String libelle) {
        this.reference = reference;
        this.libelle = libelle;
    }

    public Test(int reference, String libelle, String difficulte, String module, Date date_creation, Date date_derniere_modification, ArrayList<Question> questions) {
        this.reference = reference;
        this.libelle = libelle;
        this.difficulte = difficulte;
        this.module = module;
        this.date_creation = date_creation;
        this.date_derniere_modification = date_derniere_modification;
        this.questions = questions;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_derniere_modification() {
        return date_derniere_modification;
    }

    public void setDate_derniere_modification(Date date_derniere_modification) {
        this.date_derniere_modification = date_derniere_modification;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Test{" + "reference=" + reference + ", libelle=" + libelle + ", difficulte=" + difficulte + ", module=" + module + ", date_creation=" + date_creation + ", date_derniere_modification=" + date_derniere_modification + ", questions=" + questions + '}';
    }
    
    

   
    
    
    
    
}
