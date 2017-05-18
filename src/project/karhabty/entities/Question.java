/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import java.util.List;

/**
 *
 * @author PROXYINFO
 */
public class Question {
    
    private int reference;
    private String module;
    private String ennonce;
    private String difficulte;
    private String reponseCorrecte;
    private List<Reponse> listeDesReponses;

    public Question() {
    }

    public Question(String module, String ennonce, String difficulte, String reponseCorrecte) {
        this.module = module;
        this.ennonce = ennonce;
        this.difficulte = difficulte;
        this.reponseCorrecte = reponseCorrecte;
    }

    public Question(int reference, String module, String ennonce, String difficulte) {
        this.reference = reference;
        this.module = module;
        this.ennonce = ennonce;
        this.difficulte = difficulte;
    }

    
    public Question(int reference, String module, String ennonce, String difficulte, List<Reponse> listeDesReponses) {
        this.reference = reference;
        this.module = module;
        this.ennonce = ennonce;
        this.difficulte = difficulte;
        this.listeDesReponses = listeDesReponses;
    }
    
    
    

    public Question(int reference, String module, String ennonce, String difficulte, String reponseCorrecte, List<Reponse> listeDesReponses) {
        this.reference = reference;
        this.module = module;
        this.ennonce = ennonce;
        this.difficulte = difficulte;
        this.reponseCorrecte = reponseCorrecte;
        this.listeDesReponses = listeDesReponses;
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

    public String getEnnonce() {
        return ennonce;
    }

    public void setEnnonce(String ennonce) {
        this.ennonce = ennonce;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public String getReponseCorrecte() {
        return reponseCorrecte;
    }

    public void setReponseCorrecte(String reponseCorrecte) {
        this.reponseCorrecte = reponseCorrecte;
    }

    public List<Reponse> getListeDesReponses() {
        return listeDesReponses;
    }

    public void setListeDesReponses(List<Reponse> listeDesReponses) {
        this.listeDesReponses = listeDesReponses;
    }

    @Override
    public String toString() {
        return "Question{" + "reference=" + reference + ", module=" + module + ", ennonce=" + ennonce + ", difficulte=" + difficulte + ", reponseCorrecte=" + reponseCorrecte + ", listeDesReponses=" + listeDesReponses + '}';
    }
    
    

   
    
    
    
    
    
    
}
