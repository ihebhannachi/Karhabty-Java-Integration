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
import java.util.List;
import java.util.stream.Collectors;
import project.karhabty.entities.Alert;
import project.karhabty.Iservices.IServiceAlert;
import project.karhabty.technical.DBConfig;

/**
 *
 * @author PC NET
 */
public class CrudAlertServices implements IServiceAlert{
    private Connection con;
    private List<Alert> mylist; 

    public CrudAlertServices() {
        this.con = DBConfig.getInstance().getConnection(); 
        this.mylist = new ArrayList<>(); 
    }
  public void AddAlert(Alert a) {
        try {
            String requete = "INSERT INTO alert (Marque,Modele,Utilisateur_idUtilisateur) VALUES (?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(requete);
            pst.setString(1, a.getMarque());
            pst.setString(2, a.getModele());
            pst.setInt(3, a.getUtilisateur_idUtilisateur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Probleme d'ajout ");
            ex.printStackTrace();
        }
    }
public void AlertToList() throws SQLException {
        String requete = "SELECT * FROM alert ";
        PreparedStatement pst = this.con.prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            mylist.add(new Alert(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4))); 
        }
}
public List<Alert> GetList(String marque,String modele) {
    return this.mylist.stream().filter(e-> e.getMarque().equals(marque)&& e.getModele().equals(modele)).collect(Collectors.toList()); 
}
public void DisplayList() {
    this.mylist.stream().forEach(System.out::println);
}
public boolean AlertExiste(String marque,String modele) {
   return this.mylist.stream().anyMatch(e-> e.getMarque().equals(marque)&& e.getModele().equals(modele));
}
public void DisplayGettedList(List<Alert> list) {
    list.stream().forEach(System.out::println);
}
}
