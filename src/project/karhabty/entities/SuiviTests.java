/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.sql.Date;

/**
 *
 * @author PROXYINFO
 */
public class SuiviTests {
    private String libelle;
    private String module;
    private String avancement;
    private Date date_passage;
    private int note;

    public SuiviTests() {
    }

    public SuiviTests(String libelle, String module, String avancement, Date date_passage, int note) {
        this.libelle = libelle;
        this.module = module;
        this.avancement = avancement;
        this.date_passage = date_passage;
        this.note = note;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

 
    
    
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAvancement() {
        return avancement;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
    }

    public Date getDate_passage() {
        return date_passage;
    }

    public void setDate_passage(Date date_passage) {
        this.date_passage = date_passage;
    }
    
        
  
    
}
