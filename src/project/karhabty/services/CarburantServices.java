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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.karhabty.entities.Voiture;
import project.karhabty.entities.Carburant;
import project.karhabty.Iservices.InterfaceCarburantServices;
import project.karhabty.technical.DBConfig;

/**
 *
 * @author KAMOUN
 */
public class CarburantServices implements InterfaceCarburantServices{

        private final Connection con; 
        private ObservableList<Carburant> data;
    public CarburantServices() {
    this.con=DBConfig.getInstance().getConnection();

    }

    
    @Override
    public void AddFuelSheet(Carburant c) {
           String req="INSERT INTO `fichecarburant`(`voiture`, `dateDebut`, `dateFin`, `distanceParcourrue`,`Montant`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps= con.prepareStatement(req);
            ps.setString(1, c.getVoiture());
            ps.setDate(2, java.sql.Date.valueOf(c.getDateDebut()));
            ps.setDate(3, java.sql.Date.valueOf(c.getDateFin()));
            ps.setInt(4,c.getDistanceParcourrue());
            ps.setDouble(5, c.getMontant());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisplayFuelSheets(String c) throws SQLException
    {
        data = FXCollections.observableArrayList();
        String req="SELECT `voiture`, `dateDebut`, `dateFin`, `distanceParcourrue`, `Montant` FROM `fichecarburant` where voiture=?";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(req);
        pre.setString(1, c);
        ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            data.add(new Carburant(res.getString("voiture"),res.getDate("dateDebut").toLocalDate(),res.getDate("dateFin").toLocalDate(),res.getDouble("Montant"),res.getInt("DistanceParcourrue")));
            System.out.println(res.getString("voiture")+' '+res.getDate("DateDebut").toLocalDate()+' '+res.getDate("DateFin").toLocalDate()+' '+res.getDouble("montant")+' '+res.getInt("distanceParcourrue"));
        }
    }
    
    
    public ObservableList<Carburant> getObs()
    {
        return this.data;
    }
    
    
    
    
}
