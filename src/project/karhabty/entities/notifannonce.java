/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

/**
 *
 * @author PC NET
 */
public class notifannonce {
    private int id; 
    private int nbrannonce; 

    public notifannonce() {
    }

    public notifannonce(int id, int nbrannonce) {
        this.id = id;
        this.nbrannonce = nbrannonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrannonce() {
        return nbrannonce;
    }

    public void setNbrannonce(int nbrannonce) {
        this.nbrannonce = nbrannonce;
    }
    
    
}

