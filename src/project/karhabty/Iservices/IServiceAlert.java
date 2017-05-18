/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;
import java.sql.SQLException;
import java.util.List;
import project.karhabty.entities.Alert;
/**
 *
 * @author PC NET
 */
public interface IServiceAlert {
   public void AddAlert(Alert a); 
   public void AlertToList() throws SQLException;
   public List<Alert> GetList(String marque,String modele);
   public void DisplayList();
   public boolean AlertExiste(String marque,String modele); 
   public void DisplayGettedList(List<Alert> list); 
}
