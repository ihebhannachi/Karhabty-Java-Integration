/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;

import java.sql.SQLException;
import project.karhabty.entities.Entretien;

/**
 *
 * @author KAMOUN
 */
public interface InterfaceEntretienServices {
    public void addPieces(String voiture);
    public void DisplayEntretien(String v) throws SQLException;
    public void UpdateEntretien(Entretien e) throws SQLException;
    public void DisplayDureeVie(Entretien e)throws SQLException;
}
