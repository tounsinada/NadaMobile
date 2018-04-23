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
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

        /*java.util.Date date_Reserv = R.getDate_reservation();
        java.sql.Date sqlDate = new java.sql.Date(date_Reserv.getTime());*/

        try {
            String requete = "INSERT INTO reservation (id_even, id_client,nbre_ticket) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, (int) R.getId_even());
            pst.setInt(2, R.getId_client());
           
            pst.setInt(4, R.getNbre_ticket());

            pst.executeUpdate();
            System.out.println("Reservation effectuée dans crud");

        } catch (SQLException ex) {
            System.err.println("probleme d'ajout de reservation ");
        }
    }

    public ObservableList<Reservation> afficherReservation() {

        List<Reservation> listeReservation = new ArrayList<Reservation>();
        String req = "select * from reservation	";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                Reservation R = new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5));

                listeReservation.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Reservation> res = FXCollections.observableArrayList(listeReservation);
        return res;

    }

    public void DeleteReservation(int id) throws SQLException {
        String req = "Delete from reservation where id_reservation=" + id;
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.executeUpdate();
    }
    public ObservableList<Reservation> afficherReservationAvecNomEvenement() throws SQLException {
        List<Reservation> myList = new ArrayList<>();

        String requete2 = "SELECT * FROM reservation";
        PreparedStatement st = cnx.prepareStatement(requete2);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            String rq1 = "SELECT * FROM evenement where id_even=?";
            PreparedStatement pst1 = cnx.prepareStatement(rq1);
            pst1.setInt(1, rs.getInt("id_even"));
            ResultSet dx = pst1.executeQuery();

            while (dx.next()) {

                Reservation r = new Reservation();

                r.setNomEven(dx.getString("titre"));

                r.setId_even(rs.getInt(2));
                r.setId_reservation(rs.getInt(1));
                r.setId_client(rs.getInt(3));
                r.setNbre_ticket(rs.getInt(5));

                myList.add(r);
            }
        }

        ObservableList<Reservation> even = FXCollections.observableArrayList(myList);
        return even;

    }

    public ObservableList<Reservation> afficherReservationSeulEvenement(int idEven) throws SQLException {
         String requete = "SELECT * FROM reservation Where id_even= " + idEven;
        pst = cnx.prepareStatement(requete);
        rs = pst.executeQuery();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Reservation R = new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5));
            list.add(R);
        }
        System.out.println(list);
        return list;

    }

    public ObservableList<Reservation> displayFiltreReseration(int idEven) throws SQLException {
        String requete = "SELECT * FROM reservation Where id_even= " + idEven;
        pst = cnx.prepareStatement(requete);
        rs = pst.executeQuery();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Reservation R = new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5));
            list.add(R);
        }
        System.out.println(list);
        return list;
    }

    public ObservableList<Reservation> displayReserationParUser(int idUser) throws SQLException {
        String requete = "SELECT * FROM reservation Where id_client= " + idUser;
        pst = cnx.prepareStatement(requete);
        rs = pst.executeQuery();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Reservation R = new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5));
            list.add(R);
        }
        System.out.println(list);
        return list;
    }
}
