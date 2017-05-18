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
import project.karhabty.technical.DBConfig;
import project.karhabty.entities.Alert;
/**
 *
 * @author PC NET
 */
public class CrudNotif {
       private Connection con;
       //private notifannonce notif;  
    public CrudNotif() {
         this.con = DBConfig.getInstance().getConnection(); 
    }
    public int RecupNbrAnnonce() throws SQLException {
        String requete = "SELECT * FROM notif ";
        PreparedStatement pst = this.con.prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        res.first();         
        return res.getInt("NbrAnnonce");  
    }
    public void IncrementNbrAnnonce(int nbr) throws SQLException {
        String requete = "UPDATE notif SET NbrAnnonce=? WHERE id=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, nbr+1);
        pst.setInt(2, 1);
        pst.executeUpdate();
    }
    public void setZero() throws SQLException {
        String requete = "UPDATE notif SET NbrAnnonce=? WHERE id=?";
        PreparedStatement pst = this.con.prepareStatement(requete);
        pst.setInt(1, 0);
        pst.setInt(2, 1);
        pst.executeUpdate();
    }
}

