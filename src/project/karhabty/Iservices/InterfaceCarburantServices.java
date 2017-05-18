/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;

import java.sql.SQLException;
import project.karhabty.entities.Carburant;

/**
 *
 * @author KAMOUN
 */
public interface InterfaceCarburantServices  {
    public void AddFuelSheet(Carburant c); 
    public void DisplayFuelSheets(String c) throws SQLException;
}
