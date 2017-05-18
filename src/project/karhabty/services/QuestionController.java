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
import project.karhabty.services.*;
import project.karhabty.technical.*;

/**
 *
 * @author PROXYINFO
 */
public class QuestionController {
     private Connection connection;
     private Question question;

    public QuestionController() {
        this.connection=DBConfig.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
   
     public void ajouter(){

              
         try {
             String requete1 = "INSERT INTO questions (reference,module,ennonce,difficulte) VALUES (?,?,?,?)";
             PreparedStatement pst = this.connection.prepareStatement(requete1);
             
             pst.setInt(1, this.question.getReference());
             pst.setString(2, this.question.getModule());
             pst.setString(3, this.question.getEnnonce());
             pst.setString(4, this.question.getDifficulte());
             
             pst.executeUpdate();
             for(Reponse r :this.question.getListeDesReponses()) 
             {
                String requete2 = "INSERT INTO reponses (reponse,correcte,question) VALUES (?,?,?)";
             PreparedStatement pst2 = this.connection.prepareStatement(requete2);
             pst2.setString(1,r.getReponse());
             pst2.setBoolean(2,r.getCorrecte());
             pst2.setInt(3, this.question.getReference());
             pst2.executeUpdate();    
             }
   
         } catch (SQLException ex) {
             Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     
     public ObservableList<Question> afficher()
     {
    {
      
          try {
              String req="select * from questions  ";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Question> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.question=new Question(res.getInt("reference"),res.getString("module"),res.getString("ennonce"),res.getString("difficulte"));
                  data.add(this.question);
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
     
     }
     
     public void supprimer(){
          try {
              String requette1="delete from reponses where question="+this.question.getReference();
              String requete2 ="delete from questions where reference="+this.question.getReference();
              PreparedStatement pst = this.connection.prepareStatement(requette1);
              PreparedStatement pst2 = this.connection.prepareStatement(requete2);
              pst.executeUpdate();
              pst2.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
    
     
          public ObservableList<Reponse> afficher_reponses(Question q)
     {
    {
      
          try {
              String req="select * from reponses where question="+"'"+q.getReference()+"'";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Reponse> data = FXCollections.observableArrayList();
              while (res.next()) {
                  
                  data.add(new Reponse(res.getString("reponse"),res.getBoolean("correcte")));
                
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
     
    
    
    
     }
          
          
              public ObservableList<Question>  rechercher(String s)
    {
         try {
             
              String req="select * from Questions where reference like'%"+s+"%' or module like'%"+s+"%' or difficulte like'%"+s+"%' or ennonce like'%"+s+"%'";
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Question> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.question=new Question(res.getInt("reference"),res.getString("module"),res.getString("ennonce"),res.getString("difficulte"));
                  data.add(this.question);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              
              public ObservableList<Question>  filtrer_par_module(String module)
    {
         try {
                                 String req;
             
             if(module.equals("MIXTE")){
              req="select * from Questions ";
              }
             
             else{
                 req="select * from Questions where module ='"+module+"'";
             }
             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Question> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.question=new Question(res.getInt("reference"),res.getString("module"),res.getString("ennonce"),res.getString("difficulte"));
                  data.add(this.question);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    } 
     
              
              public ObservableList<Question>  filtrer_par_difficulte(String difficulte)
    {
         try {
                    String req;
             
             if(difficulte.equals("MIXTE")){
              req="select * from Questions ";
             
              }
             
             else{
                 req="select * from Questions where difficulte ='"+difficulte+"'";
             }

             
              
              PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Question> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.question=new Question(res.getInt("reference"),res.getString("module"),res.getString("ennonce"),res.getString("difficulte"));
                  data.add(this.question);
                  
              }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              
              public ObservableList<Question>  filtrer_par_les_deux(String module,String difficulte)
    {
         try {
             String req;
             if(module.equals("MIXTE") ){
                 
                 if( difficulte.equals("MIXTE")){
                     req="select * from Questions";
                    
                 }
                 else{
                     req="select * from Questions where difficulte='"+difficulte+"'";
                 }
                 
                 
             }
             else{
                 if(difficulte.equals("MIXTE")){
                     req="select * from Questions where module='"+module+"'";
                 }
                 else{
                      req="select * from Questions where module='"+module+"' AND difficulte='"+difficulte+"'";
                 }
                 
             }
            
                
             
             PreparedStatement pre = (PreparedStatement) this.connection.prepareStatement(req);
              ResultSet res = pre.executeQuery();
              ObservableList<Question> data = FXCollections.observableArrayList();
              while (res.next()) {
                  this.question=new Question(res.getInt("reference"),res.getString("module"),res.getString("ennonce"),res.getString("difficulte"));
                  data.add(this.question);
              
             }
              return data;
          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
              
              public void modifier(){
                  
          try {
              String requete1 = "update Questions set module='"+this.question.getModule()+"',difficulte='"+this.question.getDifficulte()+"',ennonce='"+this.question.getEnnonce()+"' where reference="+this.question.getReference();
              String requete2 ="delete from Reponses where question='"+this.question.getReference()+"'";
              
              
              PreparedStatement pst = this.connection.prepareStatement(requete1);
                            pst.executeUpdate();
               pst = this.connection.prepareStatement(requete2);
                            pst.executeUpdate();
                            for(Reponse r :this.question.getListeDesReponses()) 
             {
                String requete3 = "INSERT INTO reponses (reponse,correcte,question) VALUES (?,?,?)";
             PreparedStatement pst2 = this.connection.prepareStatement(requete3);
             pst2.setString(1,r.getReponse());
             pst2.setBoolean(2,r.getCorrecte());
             pst2.setInt(3, this.question.getReference());
             pst2.executeUpdate();    
             }

          } catch (SQLException ex) {
              Logger.getLogger(Courscontroller.class.getName()).log(Level.SEVERE, null, ex);
          }
              }
              
              public boolean referenceCntrolledeSaisie(int ref){
        boolean b=false;
                  try {
             
             String req="Select count(*) as 'val' from Questions where reference='"+ref+"'";
             PreparedStatement pre = this.connection.prepareStatement(req);
             ResultSet res = pre.executeQuery();
             while(res.next()){
                
                 if(res.getInt("val")==0){
                     b=true;
                 }

             }
            
                              return b;
                 
             
             }
             
          catch (SQLException ex) {
             Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
         }
                            
                  return b ;
                  
              }
              
              
              
              
              
              
              
}
