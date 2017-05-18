/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

/**
 *
 * @author PROXYINFO
 */
public class Reponse {
    private String reponse;
    private boolean correcte;

    public Reponse() {
    }

    public Reponse(String reponse, boolean correcte) {
        this.reponse = reponse;
        this.correcte = correcte;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }
    
    
    
}
