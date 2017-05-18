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
import project.karhabty.entities.*;
import project.karhabty.services.*;
import project.karhabty.technical.*;


/**
 *
 * @author PROXYINFO
 */
public class TestController {
    
    
         private Connection connection;
     private Test test;

    public TestController() {
          this.connection=DBConfig.getInstance().getConnection();
          this.test=new Test();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Test getTest() {
        return test;
    }
    

    public void setTest(Test test) {
        this.test = test;
    }
    
         public void ajouter(){

              
         try {
             String requete1 = "INSERT INTO tests (reference,libelle,module,difficulte,date_creation) VALUES (?,?,?,?,CURRENT_DATE)";
             PreparedStatement pst = this.connection.prepareStatement(requete1);
             
             pst.setInt(1,this.test.getReference());
             pst.setString(2, this.test.getLibelle());
             pst.setString(3, this.test.getModule());
             pst.setString(4, this.test.getDifficulte());
            
             pst.executeUpdate();
             
           
   
         } catch (SQLException ex) {
             Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
         
         public void affecter_questions()

         {
               
             for(Question q :this.test.getQuestions()) 
             {
                 try {
                     String requete2 = "INSERT INTO TestQuestions (test,question) VALUES (?,?)";
                     PreparedStatement pst2 = this.connection.prepareStatement(requete2);
                     pst2.setInt(1,this.test.getReference());
                     pst2.setInt(2,q.getReference());
                     
                     pst2.executeUpdate();
                 } catch (SQLException ex) {
                     Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         
      public ObservableList<Test> afficher()
    {
      
          try {
              String req="select * from tests  ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Test> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.test=new Test();
                  this.test.setDate_creation(res.getDate("date_creation"));
                  this.test.setLibelle(res.getString("libelle"));
                  this.test.setModule(res.getString("module"));
                  this.test.setDifficulte(res.getString("difficulte"));
                  this.test.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  data.add(this.test);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
      
      public ObservableList<Qcm> afficher_questions(Test t)
      {
          
      
          try {
              String req="select DISTINCT questions.reference, questions.ennonce from testquestions join questions where testquestions.question=questions.reference  and testquestions.test= (select reference from TESTS where libelle='"+t.getLibelle()+"')";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Qcm> data = FXCollections.observableArrayList();
              
              while (res.next()) {
                             Qcm q=new Qcm();    
                 
                  
 
                  q.setReference(res.getInt("reference"));
                  q.setEnnonce(res.getString("ennonce"));
                   String req2="select reponse,correcte from reponses where question="+q.getReference();
              PreparedStatement pre2 = (PreparedStatement) this.connection.prepareStatement(req2);
              ResultSet res2 = pre2.executeQuery();
              
              while (res2.next()) {
                  if(res2.getRow()==1){q.setAlternative1(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==2){q.setAlternative2(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==3){q.setAlternative3(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==4){q.setAlternative4(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  
                  
               
              }
                 
              data.add(q);
                  
              }
              
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    
      }
      
      
      
      
            public ObservableList<Qcm> afficher_questions(String libelle)
      {
          
      
          try {
              String req="select DISTINCT questions.reference, questions.ennonce from testquestions join questions where testquestions.question=questions.reference  and testquestions.test= (select reference from TESTS where libelle='"+libelle+"')";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Qcm> data = FXCollections.observableArrayList();
              
              while (res.next()) {
                             Qcm q=new Qcm();    
                 
                  
 
                  q.setReference(res.getInt("reference"));
                  q.setEnnonce(res.getString("ennonce"));
                   String req2="select reponse,correcte from reponses where question="+q.getReference();
              PreparedStatement pre2 = (PreparedStatement) this.connection.prepareStatement(req2);
              ResultSet res2 = pre2.executeQuery();
              
              while (res2.next()) {
                  if(res2.getRow()==1){q.setAlternative1(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==2){q.setAlternative2(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==3){q.setAlternative3(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  if(res2.getRow()==4){q.setAlternative4(res2.getString("reponse"));if(res2.getBoolean("correcte")==true){q.setReponse_correcte(res2.getString("reponse"));}}
                  
                  
               
              }
                 
              data.add(q);
              
                  
              }
              
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    
      }
      
      
              public ObservableList<Test>  filtrer_par_module(String module)
    {
         try {
                                 String req;
             
             if(module.equals("MIXTE")){
             req="select * from tests  ";
              }
             
             else{
                req="select * from tests where module='"+module+"'";
             }
             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Test> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.test.setLibelle(res.getString("libelle"));
                  this.test.setModule(res.getString("module"));
                  this.test.setDifficulte(res.getString("difficulte"));
                  this.test.setDate_creation(res.getDate("date_creation"));
                  this.test.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  
                  data.add(this.test);
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    } 
     
              
              public ObservableList<Test>  filtrer_par_difficulte(String difficulte)
    {
         try {
                    String req;
             
             if(difficulte.equals("MIXTE")){
                req="select * from tests ";
             
              }
             
             else{
                 req="select * from tests where difficulte='"+difficulte+"'";
             }

             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Test> data = FXCollections.observableArrayList();
              while (res.next()) {
                                   this.test.setLibelle(res.getString("libelle"));
                  this.test.setModule(res.getString("module"));
                  this.test.setDifficulte(res.getString("difficulte"));
                  this.test.setDate_creation(res.getDate("date_creation"));
                  this.test.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  
                  data.add(this.test);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              public ObservableList<Test>  filtrer_par_les_deux(String module,String difficulte)
    {
         try {
             String req;
             if(module.equals("MIXTE") ){
                 
                 if( difficulte.equals("MIXTE")){
                     req="select * from tests ";
                    
                 }
                 else{
                     req="select * from tests where difficulte='"+difficulte+"'";
                 }
                 
                 
             }
             else{
                 if(difficulte.equals("MIXTE")){
                   req="select * from tests where module='"+module+"'";
                 }
                 else{
                     req="select * from tests where module='"+module+"' and difficulte='"+difficulte+"'";
                 }
                 
             }
            
                
             
             PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Test> data = FXCollections.observableArrayList();
              while (res.next()) {
                                               this.test.setLibelle(res.getString("libelle"));
                  this.test.setModule(res.getString("module"));
                  this.test.setDifficulte(res.getString("difficulte"));
                  this.test.setDate_creation(res.getDate("date_creation"));
                  this.test.setDate_derniere_modification(res.getDate("date_derniere_modification"));
                  
                  data.add(this.test);
              
             }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
     
    
}
