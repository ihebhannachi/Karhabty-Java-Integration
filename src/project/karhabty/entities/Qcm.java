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
public class Qcm {
    private int reference;
    private String ennonce;
    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String alternative4;
    private String reponse;

    public Qcm() {
        
    }

    public Qcm(int reference, String ennonce, String alternative1, String alternative2, String alternative3, String alternative4, String reponse) {
        this.reference = reference;
        this.ennonce = ennonce;
        this.alternative1 = alternative1;
        this.alternative2 = alternative2;
        this.alternative3 = alternative3;
        this.alternative4 = alternative4;
        this.reponse = reponse;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getEnnonce() {
        return ennonce;
    }

    public void setEnnonce(String ennonce) {
        this.ennonce = ennonce;
    }

    public String getAlternative1() {
        return alternative1;
    }

    public void setAlternative1(String alternative1) {
        this.alternative1 = alternative1;
    }

    public String getAlternative2() {
        return alternative2;
    }

    public void setAlternative2(String alternative2) {
        this.alternative2 = alternative2;
    }

    public String getAlternative3() {
        return alternative3;
    }

    public void setAlternative3(String alternative3) {
        this.alternative3 = alternative3;
    }

    public String getAlternative4() {
        return alternative4;
    }

    public void setAlternative4(String alternative4) {
        this.alternative4 = alternative4;
    }

    public String getReponse_correcte() {
        return reponse;
    }

    public void setReponse_correcte(String reponse_correcte) {
        this.reponse = reponse_correcte;
    }
    
    
   
    
    
    
}
