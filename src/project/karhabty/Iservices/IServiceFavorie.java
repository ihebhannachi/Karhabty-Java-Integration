/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import project.karhabty.entities.Favorie;

/**
 *
 * @author PC NET
 */
public interface IServiceFavorie  {
    public void AddFavorie(Favorie f);
    public void Display() throws SQLException;
    public void FavorieToList() throws SQLException;
    public void AllFaovorieToList(int id) throws SQLException;
    public void DeleteFavorie(int id);
    public void GetAnnoncePrixDescription(int id) throws SQLException; 
    public boolean ExisteAnnonce(int id); 
    public int NumberAnnonce(); 
    public ObservableList<Favorie> getObs(); 
}

