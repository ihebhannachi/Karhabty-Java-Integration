/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.karhabty.entities.Carburant;
import project.karhabty.entities.Entretien;
import project.karhabty.Iservices.InterfaceEntretienServices;
import project.karhabty.technical.DBConfig;

/**
 *
 * @author KAMOUN
 */
public class EntretienServices implements InterfaceEntretienServices{
        private final Connection con; 
        private ObservableList<Entretien> data;
        public ObservableList<Integer> entiers;
    public EntretienServices() {
    this.con=DBConfig.getInstance().getConnection();

    }
    @Override
    public void addPieces(String voiture) {
       String req="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,1,0)";
       try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req1="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,2,0)";
        try {
            PreparedStatement ps=con.prepareStatement(req1);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req2="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,3,0)";
        try {
            PreparedStatement ps=con.prepareStatement(req2);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req3="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,4,0)";
        try {
            PreparedStatement ps=con.prepareStatement(req3);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req4="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,5,0)";
        try {
            PreparedStatement ps=con.prepareStatement(req4);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req5="INSERT INTO `fichecheentretiens`(`voiture`, `piece`, `duree_vie`) VALUES (?,6,0)";
        try {
            PreparedStatement ps=con.prepareStatement(req5);
            ps.setString(1, voiture);
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisplayEntretien(String v) throws SQLException
    {   
        int p1 = 0,p2 = 0,p3 = 0,p4 = 0,p5 = 0,p6 = 0;
          data = FXCollections.observableArrayList();
        ArrayList<Integer> a = new ArrayList<Integer>();
        System.out.println(v);
        String sql = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=1 order by piece";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
        pre.setString(1, v);
        ResultSet res = pre.executeQuery(); 
            while (res.next()) {p1=(res.getInt("duree_vie"));}
        String sql1 = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=2 order by piece";
        PreparedStatement pre1 = (PreparedStatement) this.con.prepareStatement(sql1);
        pre1.setString(1, v);
        ResultSet res1 = pre1.executeQuery(); 
            while (res1.next()) {p2=(res1.getInt("duree_vie"));}
        String sql2 = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=3 order by piece";
        PreparedStatement pre2 = (PreparedStatement) this.con.prepareStatement(sql2);
        pre2.setString(1, v);
        ResultSet res2 = pre2.executeQuery(); 
            while (res2.next()) {p3=(res2.getInt("duree_vie"));}
        String sql3 = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=4 order by piece";
        PreparedStatement pre3 = (PreparedStatement) this.con.prepareStatement(sql3);
        pre3.setString(1, v);
        ResultSet res3 = pre3.executeQuery(); 
            while (res3.next()) {p4=(res3.getInt("duree_vie"));}
        String sql4 = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=5 order by piece";
        PreparedStatement pre4 = (PreparedStatement) this.con.prepareStatement(sql4);
        pre4.setString(1, v);
        ResultSet res4 = pre4.executeQuery(); 
            while (res4.next()) {p5=(res4.getInt("duree_vie"));}
        String sql5 = "SELECT duree_vie FROM fichecheentretiens WHERE voiture=? and piece=6 order by piece";
        PreparedStatement pre5 = (PreparedStatement) this.con.prepareStatement(sql5);
        pre5.setString(1, v);
        ResultSet res5 = pre5.executeQuery(); 
            while (res5.next()) {p6=(res5.getInt(1));}
       // System.out.println(p1+" "+p2+" "+p3+" "+p4+" "+" "+p5+" "+p6);
         Entretien e= new Entretien(p1,p2,p3,p4,p5,p6);
        data.add(e);
    }   
    
    public ObservableList<Entretien> getObs()
    {
        return this.data;
    }

    @Override
    public void UpdateEntretien(Entretien e) throws SQLException {
        String req="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=1";
       try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1, e.getP1());
            ps.setString(2, e.getVoiture());
                       ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req1="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=2";
        try {
            PreparedStatement ps1=con.prepareStatement(req1);
            ps1.setInt(1, e.getP2());
            ps1.setString(2, e.getVoiture());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req2="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=3";
        try {
            PreparedStatement ps2=con.prepareStatement(req2);
            ps2.setInt(1, e.getP3());
            ps2.setString(2, e.getVoiture());
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req3="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=4";
        try {
            PreparedStatement ps3=con.prepareStatement(req3);
            ps3.setInt(1, e.getP4());
            ps3.setString(2, e.getVoiture());
            ps3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req4="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=5";
        try {
            PreparedStatement ps4=con.prepareStatement(req4);
            ps4.setInt(1, e.getP5());
            ps4.setString(2, e.getVoiture());
            ps4.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       String req5="Update `fichecheentretiens` set `duree_vie`=? where Voiture=? and piece=6";
        try {
            PreparedStatement ps5=con.prepareStatement(req5);
            ps5.setInt(1, e.getP6());
            ps5.setString(2, e.getVoiture());
            ps5.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisplayDureeVie(Entretien e)throws SQLException
    {          entiers = FXCollections.observableArrayList();

        String req1="SELECT duree_vie from `fichecheentretiens` where Voiture=? order by piece";
        PreparedStatement pre1 = (PreparedStatement) this.con.prepareStatement(req1);
        pre1.setString(1, e.getVoiture());
        ResultSet res1 = pre1.executeQuery(); 
            while (res1.next()) {
                entiers.add(res1.getInt(1));
            }
    }
    
    public ObservableList<Integer> getDuree()
    {
        return this.entiers;
    }

}
