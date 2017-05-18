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
import project.karhabty.entities.*;
import project.karhabty.technical.*;


/**
 *
 * @author PROXYINFO
 */
public class SuiviTestsController {
    private Connection connection ; 
    private SuiviTests suiviTests ;

    public SuiviTestsController() {
          this.connection=DBConfig.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public SuiviTests getSuiviTests() {
        return suiviTests;
    }

    public void setSuiviTests(SuiviTests suiviTests) {
        this.suiviTests = suiviTests;
    }
    
     public ObservableList<SuiviTests> afficher()
    {
      
          try {
              String req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle,tests.difficulte from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviTests> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviTests=new SuiviTests();
                  this.suiviTests.setLibelle(res.getString("libelle"));
                  this.suiviTests.setModule(res.getString("module"));
                  this.suiviTests.setAvancement(res.getString("difficulte"));
                  this.suiviTests.setDate_passage(res.getDate("date_passage"));
                  this.suiviTests.setNote(res.getInt("note"));
                  data.add(this.suiviTests);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(SuiviTestsController.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
     
        public ObservableList<SuiviTests>  filtrer_par_module(String module)
    {
         try {
                                 String req;
             
             if(module.equals("MIXTE")){
              req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 ";
              }
             
             else{
                req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and tests.module=' "+module+"'";
             }
             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviTests> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviTests=new SuiviTests();
                  this.suiviTests.setLibelle(res.getString("libelle"));
                  this.suiviTests.setNote(res.getInt("note"));
                  this.suiviTests.setModule(res.getString("module"));
                  this.suiviTests.setDate_passage(res.getDate("date_passage"));
                  data.add(this.suiviTests);
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    } 
     
              
              public ObservableList<SuiviTests>  filtrer_par_difficulte(String difficulte)
    {
         try {
                    String req;
             
             if(difficulte.equals("MIXTE")){
               req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 ";
             
              }
             
             else{
                 req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and tests.difficulte='"+difficulte+"'";
             }

             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviTests> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviTests=new SuiviTests();
                  this.suiviTests.setLibelle(res.getString("libelle"));
                  this.suiviTests.setNote(res.getInt("note"));
                  this.suiviTests.setModule(res.getString("module"));
                  this.suiviTests.setDate_passage(res.getDate("date_passage"));
                  data.add(this.suiviTests);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              public ObservableList<SuiviTests>  filtrer_par_les_deux(String module,String difficulte)
    {
         try {
             String req;
             if(module.equals("MIXTE") ){
                 
                 if( difficulte.equals("MIXTE")){
                    req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 ";
                    
                 }
                 else{
                    req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and tests.difficulte='"+difficulte+"'";
                 }
                 
                 
             }
             else{
                 if(difficulte.equals("MIXTE")){
                   req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and tests.module='"+module+"'";
                 }
                 else{
                    req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and tests.module='"+module+"' and tests.difficulte='"+difficulte+"'";
                 }
                 
             }
            
                
             
             PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviTests> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviTests=new SuiviTests();
                  this.suiviTests.setLibelle(res.getString("libelle"));
                  this.suiviTests.setNote(res.getInt("note"));
                  this.suiviTests.setModule(res.getString("module"));
                  this.suiviTests.setDate_passage(res.getDate("date_passage"));
                  data.add(this.suiviTests);
              
             }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
               public ObservableList<SuiviTests> rechercherTests(String labelle)
    {
      
          try {
              String req="select DISTINCT Suivitests.date_passage,SuiviTests.Note,Tests.module,tests.libelle,tests.difficulte from SuiviTests inner join Tests where SuiviTests.Test=Tests.reference and Suivitests.users=1 and (Tests.libelle like '%"+labelle+"%' or tests.module like '%"+labelle+"%' or SuiviTests.note like '%"+labelle+"%' )" ;
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviTests> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviTests=new SuiviTests();
                  this.suiviTests.setLibelle(res.getString("libelle"));
                  this.suiviTests.setModule(res.getString("module"));
                  this.suiviTests.setAvancement(res.getString("difficulte"));
                  this.suiviTests.setDate_passage(res.getDate("date_passage"));
                  this.suiviTests.setNote(res.getInt("note"));
                  data.add(this.suiviTests);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(SuiviTestsController.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
    
}
