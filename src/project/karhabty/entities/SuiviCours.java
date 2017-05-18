/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author PROXYINFO
 */
public class SuiviCours {
   private int id;
   private String nom;
   private String module;
   private String avancement;
   private Date date_inscription;
   private File contenu;

    public SuiviCours() {
    }

    public SuiviCours(int id ,String nom, String module, String avancement, Date date_inscription) {
        this.id=id;
        this.nom = nom;
        this.module = module;
        this.avancement = avancement;
        this.date_inscription = date_inscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getContenu() {
        return contenu;
    }

    public void setContenu(File contenu) {
        this.contenu = contenu;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }
   
   
   
            
            
                    
    
}
