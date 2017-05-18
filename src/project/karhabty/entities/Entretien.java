/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

/**
 *
 * @author KAMOUN
 */
public class Entretien {
    private int id;
    private String voiture;
    private int piece;
    private int dureeVie;
    
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    private int p5;
    private int p6;

    public Entretien() {
    
    }
    
    public Entretien(int p1,int p2,int p3,int p4,int p5,int p6)
    {
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.p4=p4;
        this.p5=p5;
        this.p6=p6;
        
    }
    
    public int getId(){return id;}
    public String getVoiture(){return voiture;}
    public int getpiece(){return piece;}
    public int getDureeVie(){return dureeVie;}
    public int getP1(){return p1;}
    public int getP2(){return p2;}
    public int getP3(){return p3;}
    public int getP4(){return p4;}
    public int getP5(){return p5;}
    public int getP6(){return p6;}
    
    public void setId(int id){this.id=id;}
    public void setVoiture(String voiture){this.voiture=voiture;}
    public void setPiece(int piece){this.piece=piece;}
    public void setDureeVie(int dureeVie){this.dureeVie=dureeVie;}
    
    public void setP1(int p1){this.p1=p1;}
    public void setP2(int p2){this.p2=p2;}
    public void setP3(int p3){this.p3=p3;}
    public void setP4(int p4){this.p4=p4;}
    public void setP5(int p5){this.p5=p5;}
    public void setP6(int p6){this.p6=p6;}

    @Override
    public String toString() {
        return this.getVoiture()+" "+this.getP1()+" "+this.getP2()+" "+this.getP3()+" "+this.getP4()+" "+this.getP5()+" "+this.getP6(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    

}
