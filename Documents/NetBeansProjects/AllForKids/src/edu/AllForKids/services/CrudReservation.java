/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.Reservation;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author LENOVO
 */
public class CrudReservation {

    Connection cnx = MyConnexion.getInstance().getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public CrudReservation() {
    }
    

    public void ajouterReservation(Reservation R) {
        
         
      java.util.Date date_Reserv = R.getDate_reservation();
        java.sql.Date sqlDate = new java.sql.Date(date_Reserv.getTime());
         
               
        try {
            String requete = "INSERT INTO reservation (id_even, id_client, date_reservation,nbre_ticket) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, (int) R.getId_even());
            pst.setInt(2, R.getId_client());
            pst.setDate(3, sqlDate);
            pst.setInt(4, R.getNbre_ticket());

            pst.executeUpdate();
            System.out.println("Reservation effectuée");

        } catch (SQLException ex) {
            System.err.println("probleme d'ajout de reservation ");
        }
    }

}
