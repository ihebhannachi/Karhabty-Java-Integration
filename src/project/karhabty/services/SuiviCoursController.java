/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.services;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.karhabty.entities.*;
import project.karhabty.services.*;
import project.karhabty.technical.*;


/**
 *
 * @author PROXYINFO
 */
public class SuiviCoursController {
    private Connection connection;
    private SuiviCours suiviCours;

    public SuiviCoursController() {
                this.connection=DBConfig.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public SuiviCours getSuiviCours() {
        return suiviCours;
    }

    public void setSuiviCours(SuiviCours suiviCours) {
        this.suiviCours = suiviCours;
    }
    
   public ObservableList<SuiviCours> afficher()
    {
      
          try {
              String req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviCours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviCours=new SuiviCours();
                  this.suiviCours.setNom(res.getString("nom"));
                  this.suiviCours.setModule(res.getString("module"));
                  this.suiviCours.setAvancement(res.getString("avancement"));
                  this.suiviCours.setDate_inscription(res.getDate("date_inscription"));
                  data.add(this.suiviCours);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(SuiviCoursController.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
    
   
   
   public void lire(String nomFichier){

    //recuperer le nom du cours selectionné
    //recurpérer le fichier via une requette
    //recupérer le contenu du fichier et l'ouvrir
   
    Desktop desk = Desktop.getDesktop();
    try {
        desk.open(new File("C:\\Users\\PROXYINFO\\Desktop\\karhabty\\src\\karhabty\\user cours\\"+nomFichier));
    } catch (IOException ex) {
        Logger.getLogger(SuiviCoursController.class.getName()).log(Level.SEVERE, null, ex);
    }

    

    
  
   }
   
   
   public ObservableList<SuiviCours>  filtrer_par_module(String module)
    {
         try {
                                 String req;
             
             if(module.equals("MIXTE")){
              req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 ";
              }
             
             else{
                 req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and Cours.module='"+module+"'";
             }
             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviCours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviCours=new SuiviCours();
                  this.suiviCours.setNom(res.getString("nom"));
                  this.suiviCours.setDate_inscription(res.getDate("date_inscription"));
                  this.suiviCours.setAvancement(res.getString("avancement"));
                  this.suiviCours.setModule(res.getString("module"));
                  data.add(this.suiviCours);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    } 
     
              
              public ObservableList<SuiviCours>  filtrer_par_difficulte(String difficulte)
    {
         try {
                    String req;
             
             if(difficulte.equals("MIXTE")){
               req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 ";
             
              }
             
             else{
                  req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and Cours.avancement='"+difficulte+"'";
             }

             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviCours> data = FXCollections.observableArrayList();
              while (res.next()) {
                               this.suiviCours=new SuiviCours();
                  this.suiviCours.setNom(res.getString("nom"));
                  this.suiviCours.setDate_inscription(res.getDate("date_inscription"));
                  this.suiviCours.setAvancement(res.getString("avancement"));
                  this.suiviCours.setModule(res.getString("module"));
                  data.add(this.suiviCours);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              public ObservableList<SuiviCours>  filtrer_par_les_deux(String module,String difficulte)
    {
         try {
             String req;
             if(module.equals("MIXTE") ){
                 
                 if( difficulte.equals("MIXTE")){
                     req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 ";
                    
                 }
                 else{
                     req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and cours.avancement='"+difficulte+"'";
                 }
                 
                 
             }
             else{
                 if(difficulte.equals("MIXTE")){
                    req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and cours.module='"+module+"'";
                 }
                 else{
                      req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and cours.module='"+module+"' and cours.avancement='"+difficulte+"'";
                 }
                 
             }
            
                
             
             PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviCours> data = FXCollections.observableArrayList();
              while (res.next()) {
                       this.suiviCours=new SuiviCours();
                  this.suiviCours.setNom(res.getString("nom"));
                  this.suiviCours.setDate_inscription(res.getDate("date_inscription"));
                  this.suiviCours.setAvancement(res.getString("avancement"));
                  this.suiviCours.setModule(res.getString("module"));
                  data.add(this.suiviCours);
              
             }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              public List<Integer> generer_statistiques(){
        try {
            List<Integer> liste=new ArrayList();
               String req1="select count(*) as num from cours join suivicours where users=1 and cours.reference=suivicours.cours and module='juridique'";
            
            PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req1);
            ResultSet res = pre.executeQuery();
            while(res.next()){
                liste.add(res.getInt("num"));
            }
               String req2="select count(*) as num from cours join suivicours where users=1 and cours.reference=suivicours.cours and module='signaux'";
            
            PreparedStatement pre2 = (PreparedStatement) this.connection.prepareStatement(req2);
            ResultSet res2 = pre2.executeQuery();
            while(res2.next()){
                liste.add(res2.getInt("num"));
            }
                String req3="select count(*) as num from cours join suivicours where users=1 and cours.reference=suivicours.cours and module='priorités'";
            
            PreparedStatement pre3 = (PreparedStatement) this.connection.prepareStatement(req3);
            ResultSet res3 = pre3.executeQuery();
            while(res3.next()){
                liste.add(res3.getInt("num"));
            }
                String req4="select count(*) as num from cours join suivicours where users=1 and cours.reference=suivicours.cours and module='classements'";
            
            PreparedStatement pre4 = (PreparedStatement) this.connection.prepareStatement(req4);
            ResultSet res4 = pre4.executeQuery();
            while(res4.next()){
                liste.add(res4.getInt("num"));
            }
            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(SuiviCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  return null;
              }
              

              public ObservableList<Cours> lister_cours_conseils(){
                    
             
                  List<Integer> l=this.generer_statistiques();
                  String module="";
                            try {
                                
                                if(l.get(0)<l.get(1)&&l.get(0)<l.get(2)&&l.get(0)<l.get(3))
                                {
                                    module="juridique";
                                }
                                else if(l.get(1)<l.get(0)&&l.get(1)<l.get(2)&&l.get(1)<l.get(3))
                                {
                                    module="signaux";
                                }
                                else if(l.get(2)<l.get(1)&&l.get(2)<l.get(0)&&l.get(2)<l.get(3))
                                {
                                    module="priorités";
                                }
                                else if(l.get(3)<l.get(1)&&l.get(3)<l.get(2)&&l.get(3)<l.get(0))
                                {
                                    module="classement";
                                }
              String req="select * from cours where module='"+module+"'";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Cours> data = FXCollections.observableArrayList();
              while (res.next()) {
                 Cours c =new Cours();
                  c.setNom(res.getString("nom"));
                  
                  data.add(c);
                
                  
              }
              return data;
              
          } catch (SQLException ex) {
              Logger.getLogger(SuiviCoursController.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
                     
                      }
              
     
              
              public ObservableList<SuiviCours>  rechercherCours(String labelle)
    {
         try {
             
                
             String req="select DISTINCT Cours.nom,Cours.module,Cours.avancement,SuiviCours.date_inscription from Cours inner join SuiviCours where suivicours.cours=cours.reference and suivicours.users=1 and ( cours.module like'%"+labelle+"%' or cours.nom like'%"+labelle+"%' or cours.avancement like'%"+labelle+"%' ) ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<SuiviCours> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.suiviCours=new SuiviCours();
                  this.suiviCours.setNom(res.getString("nom"));
                  this.suiviCours.setModule(res.getString("module"));
                  this.suiviCours.setAvancement(res.getString("avancement"));
                  this.suiviCours.setDate_inscription(res.getDate("date_inscription"));
                  data.add(this.suiviCours);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
  
    
}
   

