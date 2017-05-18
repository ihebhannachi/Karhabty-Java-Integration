/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import project.karhabty.entities.*;
import project.karhabty.technical.*;
/**
 *
 * @author PROXYINFO
 */
public class Courscontroller {
    
      private Connection connection;
    private Cours cours;

    public Courscontroller(Connection connection) {
        this.connection=DBConfig.getInstance().getConnection();
        
    }

    public Courscontroller() {
        this.connection=DBConfig.getInstance().getConnection();
    }

    public Courscontroller(Cours cours) {
        this.cours = cours;
    }

    public Courscontroller(Connection connection, Cours cours) {
        this.connection = connection;
        this.cours = cours;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "Courscontroller{" + "connection=" + connection + ", cours=" + cours + '}';
    }
    
    public void ajouter(){
    
        
          try {

                       
        

              
              String requete = "INSERT INTO cours (nom,module,avancement,date_creation,contenu) VALUES (?,?,?,CURRENT_DATE,?)";
              PreparedStatement pst = this.connection.prepareStatement(requete);
              pst.setString(1, cours.getNom());
              pst.setString(2, cours.getModule());
              //pst.setString(3, this.cours.getContenu());
              pst.setString(3, cours.getAvancement());
              

               cours.setContenu(this.cours.getContenu());
                          //   File  = new File ("C:\\Users\\PROXYINFO\\Desktop\\PrositsPLSQL.pdf");
            FileInputStream is;
              try {
                  is = new FileInputStream(this.cours.getContenu());
                  pst.setBinaryStream(4, is, (int) cours.getContenu().length());
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              pst.executeUpdate();
              
          }
          catch (SQLException ex) 
          {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
       
        
    }
    
    public void modifier(Cours nouveau){
        
        
          try {
              String requete = "update cours set nom="+nouveau.getNom()+",module="+nouveau.getModule()+",avancement="+nouveau.getAvancement()+",contenu="+nouveau.getAvancement()+"date_derniere_modification="+nouveau.getDate_derniere_modification()+" where reference="+this.cours.getReference();
              PreparedStatement pst = this.connection.prepareStatement(requete);
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
       
    }
    
    public void supprimer(){
        
        try {
              String requete ="delete from cours where reference="+this.cours.getReference();
              PreparedStatement pst = this.connection.prepareStatement(requete);
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public ObservableList<Cours> afficher()
    {
      
          try {
              String req="select * from cours  ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Cours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.cours=new Cours(res.getInt("reference"),res.getString("module"),res.getString("nom"),res.getString("avancement"),res.getDate("date_creation"));
                  this.cours.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  data.add(this.cours);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
    
    
    public ObservableList<Cours> afficherSuivi(){
        
          try {
              String req="select * from cours where reference not in (Select Cours from SuiviCours )  ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Cours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.cours=new Cours(res.getInt("reference"),res.getString("module"),res.getString("nom"),res.getString("avancement"),res.getDate("date_creation"));
                  this.cours.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  data.add(this.cours);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
    
    public ObservableList<Cours>  rechercher(String s)
    {
         try {
             
              String req="select * from cours where nom like'%"+s+"%' or module like'%"+s+"%' or reference like'%"+s+"%' or avancement like'%"+s+"%'";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Cours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.cours=new Cours(res.getInt("reference"),res.getString("module"),res.getString("nom"),res.getString("avancement"),res.getDate("date_creation"));
                  data.add(this.cours);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
    
    public void modifier_cours(){
        try {
              String requete ="update cours set nom='"+this.cours.getNom()+"',module='"+this.cours.getModule()+"',avancement='"+this.cours.getAvancement()+"' ,date_derniere_modification=CURRENT_DATE "+" where reference="+this.cours.getReference();
              PreparedStatement pst = this.connection.prepareStatement(requete);
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
    
}
    
public void afficher_pdf(){
                  FileChooser fc=new FileChooser();
              fc.setTitle("Open Resource File");
                 FileChooser.ExtensionFilter extFilter = 
                        new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fc.getExtensionFilters().add(extFilter);
                   
               File fBlob= fc.showOpenDialog(null);
              
               this.cours.setContenu(fBlob);
               
    
}

public void ajouter_suivi(){
    
      try {

              String requete = "INSERT INTO SuiviCours (users,cours,date_inscription) VALUES (?,?,CURRENT_DATE)";
              PreparedStatement pst = this.connection.prepareStatement(requete);
              pst.setInt(1,1);
              pst.setInt(2, cours.getReference());
              pst.executeUpdate();              
          }
          catch (SQLException ex) 
          {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
       
        
    }




    



            
}
