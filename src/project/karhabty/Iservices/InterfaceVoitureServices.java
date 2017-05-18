/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import project.karhabty.entities.Voiture;

/**
 *
 * @author KAMOUN
 */
public interface InterfaceVoitureServices {
    public void addCar(Voiture v);
    public void GetMarques()throws SQLException;
    public void GetModelByIds()throws SQLException;
    public void GetModels(String marque)throws SQLException;
    public ObservableList GetList();
    public ObservableList GetListModel();
    public int getIdMarque ();
    public void GetModeleP(String modele) throws SQLException;
    public int getIdModele();
    public void displayCars()throws SQLException;
    public ObservableList<Voiture> getObs();
    public void deleteCar(Voiture v)throws SQLException;
    public void updateCar(Voiture v)throws SQLException;
    public void searchCars(Voiture v)throws SQLException;
    public void SendMail();
}
