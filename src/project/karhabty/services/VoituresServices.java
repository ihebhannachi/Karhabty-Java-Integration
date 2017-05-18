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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import projet.karhabty.entities.Mail;
import project.karhabty.entities.Voiture;
import project.karhabty.Iservices.InterfaceVoitureServices;
import project.karhabty.technical.DBConfig;
import project.karhabty.technical.Session;

/**
 *
 * @author KAMOUN
 */
public class VoituresServices implements InterfaceVoitureServices {
    final ObservableList optionMarque;
    private final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-mm-yyyy");
    final ObservableList optionModel;
    private static final List<Voiture> ListAnnonce = new ArrayList<>();
    private final Connection con; 
    private int id; 
    private int idModele;
    private ObservableList<Voiture> data;
    public VoituresServices(){
         optionMarque  = FXCollections.observableArrayList();
        optionModel = FXCollections.observableArrayList();
        this.con=DBConfig.getInstance().getConnection();
        
    }
    
    public void SendMail()
    {
       // Mail mail=new Mail();
        //mail.sendMail("abdelmalek.ouelbani@esprit.tn", "subject", "message");
    }
    
    public void addCar(Voiture v) {
       String req="INSERT INTO `voitures`(`matricule`, `marque`, `modele`, `dateMisCirculation`, `puissanceFiscale`, `chevauxDyn`, `carburant`, `transmission`, `kilometrage`, `proprietaire`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setString(1, v.getMatricule());
            ps.setInt(2, v.getMarque());
            ps.setInt(3, v.getModele());
            ps.setDate(4,java.sql.Date.valueOf(v.getDateMiseCirculation()));
            ps.setInt(5, v.getPuissanceFiscale());
            ps.setInt(6, v.getPuissanceDynamique());
            ps.setString(7, v.getCarburant());
            ps.setInt(9, v.getKilometrage());
            ps.setString(8, v.getTransmission());
            ps.setInt(10, v.getProprietaire());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoituresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @throws SQLException
     */
    @Override
     public void GetMarques() throws SQLException{
        String sql = "SELECT * FROM marques"; 
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql); 
        ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            this.optionMarque.add(res.getString(2)); 
        } 
    }
    public void GetModelByIds() throws SQLException {
    String sql = "SELECT * FROM modeles WHERE IdMarque=?";
    PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
    pre.setInt(1, this.id);
    ResultSet res = pre.executeQuery(); 
    while (res.next()) {
           this.optionModel.add(res.getString(3)); 
        }
    }
    
    public void GetModels(String marque) throws SQLException {
    String sql = "SELECT * FROM marques WHERE nom=?";
    PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
    pre.setString(1, marque);
    ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            this.id = res.getInt(1); 
        }
}
    
    public void GetModeleP(String modele) throws SQLException {
    String sql = "SELECT * FROM modeles WHERE nom=?";
    PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(sql);
    pre.setString(1, modele);
    ResultSet res = pre.executeQuery(); 
        while (res.next()) {
            this.idModele = res.getInt(1); 
        }
}
    public ObservableList GetList() {
        return this.optionMarque; 
    }
    public ObservableList GetListModel() {
        return this.optionModel; 
    }
    public int getIdMarque () {
        return this.id; 
    }
    
    public int getIdModele(){
        return idModele;
    }
    
    public void displayCars()throws SQLException
    {   
        data = FXCollections.observableArrayList();
        String req="select DISTINCT a.matricule,b.nom as \"marque\",c.nom as \"modele\",a.dateMisCirculation,a.puissanceFiscale,a.chevauxDyn,a.carburant,a.transmission,a.kilometrage,a.proprietaire FROM voitures a left join marques b on a.marque=b.idMarque left join modeles c on a.Modele=c.idModele and a.Marque=c.idMarque WHERE Proprietaire=?";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(req);
        pre.setInt(1, Session.getId());/**
        ********
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * */
        ResultSet res = pre.executeQuery(); 
        String zone="Europe";
        while (res.next()) {
            data.add(new Voiture(res.getString("Matricule"), res.getString("Marque"), res.getString("Modele"), res.getDate("DateMisCirculation").toLocalDate(), res.getInt("PuissanceFiscale"), res.getInt("ChevauxDyn"), res.getString("Carburant"), res.getString("Transmission"), res.getInt("Kilometrage")));      
            //System.out.println(res.getString("Modele")+"    "+res.getString("Marque")+"   "+res.getString("DateMisCirculation"));test
        }
    }
    
    public void searchCars(Voiture v)throws SQLException
    {
        data = FXCollections.observableArrayList();
        String req="select DISTINCT a.matricule,b.nom as \"marque\",c.nom as \"modele\",a.dateMisCirculation,a.puissanceFiscale,a.chevauxDyn,a.carburant,a.transmission,a.kilometrage,a.proprietaire FROM voitures a left join marques b on a.marque=b.idMarque left join modeles c on a.Modele=c.idModele and a.Marque=c.idMarque where (a.matricule like '%"+v.getMatricule()+"%' or b.nom like '%"+v.getMarqueS()+"%' or c.nom like '%"+v.getModeleS()+"%' or a.puissanceFiscale LIKE '%"+v.getPuissanceFiscale()+"%' or a.chevauxDyn like '%"+v.getPuissanceDynamique()+"%' or a.carburant like '%"+v.getCarburant()+"%' or a.transmission like '%"+v.getTransmission()+"%' or a.kilometrage like '%"+v.getKilometrage()+"%') and Proprietaire=?";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(req);
     
        pre.setInt(1, v.getProprietaire());
        ResultSet res = pre.executeQuery(); 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        while (res.next()) {
            data.add(new Voiture(res.getString("Matricule"), res.getString("Marque"), res.getString("Modele"), res.getDate("DateMisCirculation").toLocalDate(), res.getInt("PuissanceFiscale"), res.getInt("ChevauxDyn"), res.getString("Carburant"), res.getString("Transmission"), res.getInt("Kilometrage")));      
           //System.out.println(res.getString("Matricule")+" "+ res.getString("Marque")+" "+ res.getString("Modele")+" "+ res.getDate("DateMisCirculation").toLocalDate()+" "+res.getInt("PuissanceFiscale")+" "+ res.getInt("ChevauxDyn")+" "+ res.getString("Carburant")+" "+ res.getString("Transmission")+" "+ res.getInt("Kilometrage"));
        }


    }
    public ObservableList<Voiture> getObs() {
        return this.data;
    }
    
    public void deleteCar(Voiture v)throws SQLException
    {
        String req="DELETE FROM `voitures` WHERE `matricule`=?";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(req);
        pre.setString(1, v.getMatricule());
         pre.executeUpdate();
          
    }
    
    public void updateCar(Voiture v)throws SQLException
    {
        String req="UPDATE `voitures` SET `dateMisCirculation`=?,`puissanceFiscale`=?,`chevauxDyn`=?,`carburant`=?,`transmission`=?,`kilometrage`=? WHERE `matricule`=?";
        PreparedStatement pre = (PreparedStatement) this.con.prepareStatement(req);
       
        pre.setDate(1, java.sql.Date.valueOf(v.getDateMiseCirculation()));
        pre.setInt(2, v.getPuissanceFiscale());
        pre.setInt(3, v.getPuissanceDynamique());
        pre.setString(4, v.getCarburant());
        pre.setString(5, v.getTransmission());
        pre.setInt(6, v.getKilometrage());
        pre.setString(7, v.getMatricule());

         pre.executeUpdate();
          
    }
}
