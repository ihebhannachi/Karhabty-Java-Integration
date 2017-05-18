/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.io.File;
import java.sql.Date;

/**
 *
 * @author PROXYINFO
 */
public class Cours {
    
    private int reference;
    private String module;
    private File contenu;
    private  String nom;
    private String avancement;
    private Date date_creation;
    private Date date_derniere_modification;

    public Cours() {
    }

    public Cours(int reference, String module, File contenu, String nom, String avancement, Date date_creation, Date date_derniere_modification) {
        this.reference = reference;
        this.module = module;
        this.contenu = contenu;
        this.nom = nom;
        this.avancement = avancement;
        this.date_creation = date_creation;
        this.date_derniere_modification = date_derniere_modification;
    }

    public Cours(int reference, String module, String nom, String avancement) {
        this.reference = reference;
        this.module = module;
        this.nom = nom;
        this.avancement = avancement;
    }

    
    public Cours(String module, String nom, Date date_creation) {
        this.module = module;
        this.nom = nom;
        this.date_creation = date_creation;
    }

    public Cours(int reference, String module, String nom, String avancement, Date date_creation) {
        this.reference = reference;
        this.module = module;
        this.nom = nom;
        this.avancement = avancement;
        this.date_creation = date_creation;
    }

    

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public String getAvancement() {
        return avancement;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
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

    @Override
    public String toString() {
        return "Cours{" + "reference=" + reference + ", module=" + module + ", contenu=" + contenu + ", nom=" + nom + ", avancement=" + avancement + ", date_creation=" + date_creation + ", date_derniere_modification=" + date_derniere_modification + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cours other = (Cours) obj;
        if (this.reference != other.reference) {
            return false;
        }
        return true;
    }

    

    
    
    
    
    
    
}
