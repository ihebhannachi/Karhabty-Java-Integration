/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import project.karhabty.entities.Annonce;
import javax.mail.MessagingException;
/**
 *
 * @author PC NET
 */
public interface IServiceAnnonce {
   public void AddAnnonce(Annonce a) throws FileNotFoundException, SQLException, MessagingException; 
   public void OderByAnnonce(String order) throws SQLException;
   public void AdminOrderByAnnonce(String order)throws SQLException;
   public ResultSet ExportData()throws SQLException;
   public void UpdateAnnonce(Annonce a,int id)throws FileNotFoundException, SQLException;
   public void DisplayMyAnnonce()throws SQLException;
   public void AdminDisplayAnnonce()throws SQLException;
   public void DeleteMyAnnonce(int id);
   public ImageView SelectImage(int id)throws SQLException;
   public void SearchAnnonce(int id)throws SQLException; 
   public void DisplayAllAnnonce()throws SQLException; 
   public void OrderByAllAnnonce(String order)throws SQLException; 
   public String GetMail(int id)throws SQLException; 
   public ObservableList<Annonce> getObs(); 
}

