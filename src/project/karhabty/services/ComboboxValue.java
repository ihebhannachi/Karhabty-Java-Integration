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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import project.karhabty.technical.DBConfig;
/**
 *
 * @author PC NET
 */
public class ComboboxValue {
        final ObservableList optionMarque;
        final ObservableList optionModel;
        private Connection con; 
        private int id;  
    public ComboboxValue() {
        optionMarque  = FXCollections.observableArrayList();
        optionModel = FXCollections.observableArrayList();
        this.con = DBConfig.getInstance().getConnection(); 
    }
    public void GetMarque() throws SQLException{
        String sql = "SELECT * FROM marques "; 
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql); 
        ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            this.optionMarque.add(res.getString(2)); 
        } 
    }
    public void GetModelById() throws SQLException {
    String sql = "SELECT * FROM modeles WHERE idMarque=?";
    PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
    pre.setInt(1, this.id);
    ResultSet res = pre.executeQuery(); 
    while (res.next()) {
           this.optionModel.add(res.getString(2)); 
        }
    }
    public void GetModel(String marque) throws SQLException {
    String sql = "SELECT * FROM marques WHERE nom=?";
    PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
    pre.setString(1, marque);
    ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            this.id = res.getInt(1); 
        }
}
    public ObservableList GetList() {
        return this.optionMarque; 
    }
    public ObservableList GetListModel() {
        return this.optionModel; 
    }
    public int getIdMarque () {
        return this.id; 
    }
}

