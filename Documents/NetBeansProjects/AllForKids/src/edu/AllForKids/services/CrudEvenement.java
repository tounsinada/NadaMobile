package edu.AllForKids.services;

import edu.AllForKids.entities.Categorie;
import edu.AllForKids.entities.Evenement;
import edu.AllForKids.utils.MyConnexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Amine
 */
public class CrudEvenement {

    Connection cnx = MyConnexion.getInstance().getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public void ajouterEvenement(Evenement E) {
        try {
            String requete = "INSERT INTO evenement (Categorie_id,titre, nom_image,lieu,date_debut,date_fin,description,ticket_disponible,Tarif) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, E.getId_cat());
            pst.setString(2, E.getTitre());
            pst.setString(3, E.getNom_image());
            pst.setString(4, E.getLieu());
            pst.setDate(5, (Date) E.getDate_debut());
            pst.setDate(6, (Date) E.getDate_fin());
            pst.setString(7, E.getDescription());
            pst.setInt(8, E.getTicket_disponible());
            pst.setInt(9, E.getTarif());

            pst.executeUpdate();
            System.out.println("Evenement ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Evenement> afficherEvenement() {
    
        List<Evenement> listeven = new ArrayList<Evenement>();
        String req = "select * from evenement";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getInt(10));

                listeven.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeven;

    }

    public void modifierEvenement(Evenement c, int id) {
        try {
            String requete = "UPDATE  evenement SET categorie_id=?,titre=?,nom_image=?,lieu=?,date_debut=?,date_fin=?,"
                    + "description=?,ticket_disponible=?,tarif=? WHERE id_even='"+id+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_cat());
            pst.setString(2, c.getTitre());
            pst.setString(3, c.getNom_image());
            pst.setString(4, c.getLieu());
            pst.setString(7, c.getDescription());
            pst.setDate(5, (Date) c.getDate_debut());
            pst.setDate(6, (Date) c.getDate_fin());
            pst.setInt(8, c.getTicket_disponible());

            pst.setInt(9, c.getTarif());

            pst.executeUpdate();
            System.out.println("Evenement modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void DeleteEvenement(int id) throws SQLException {
        String req = "Delete from evenement where id_even=" + id;
        Statement ste = cnx.createStatement();
        ste.executeUpdate(req);
    }

    public ObservableList<Evenement> displayAllEvenement() throws SQLException {

        String requete = "SELECT * FROM evenement " + " ORDER BY date_debut DESC";
        Statement ste = cnx.createStatement();
        rs = ste.executeQuery(requete);
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evenement e = new Evenement();
            e.setId_even(rs.getInt(1));
            e.setId_cat(rs.getInt(2));
            e.setNom_image(rs.getString(4));
            e.setLieu(rs.getString(5));
            e.setTitre(rs.getString(3));
            e.setDate_debut(rs.getDate(6));
            e.setDate_fin(rs.getDate(7));
            e.setDescription(rs.getString(8));
            e.setTicket_disponible(rs.getInt(9));
            e.setTarif(rs.getInt(10));

            list.add(e);
        }

        return list;
    }

    public void supprimerEvenement(Evenement e) {
        try {
            String requete = "DELETE FROM evenement WHERE id_even=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, e.getId_even());
            pst.executeUpdate();
            System.out.println("Evenement supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public ObservableList<Evenement> displayFiltreEvenement(int idcateg) throws SQLException{
        String requete="SELECT * FROM evenement Where categorie_id = " +idcateg+" ORDER BY date_debut DESC" ;
        pst = cnx.prepareStatement(requete);
         rs = pst.executeQuery();
        ObservableList<Evenement> list =FXCollections.observableArrayList(); 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDate(6),rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
        list.add(E) ;
        }
        System.out.println(list);
        return list ;
    }
     
 public ObservableList<Evenement> Search(String MotAChercher) throws SQLException{
        String requete="SELECT * FROM evenement Where  titre LIKE '%"+MotAChercher +"%' ORDER BY date_debut DESC" ;
        pst=cnx.prepareStatement(requete);
        rs=pst.executeQuery();
        ObservableList<Evenement> list =FXCollections.observableArrayList(); 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDate(6),rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
        list.add(E) ;
        }
        System.out.println(list);
        return list ;
    } 
    
    
 public Evenement EvenementByID(int id) throws SQLException{
       String requete="SELECT * FROM evenement Where id_even= " + id +" ORDER BY date_debut DESC" ;
        pst=cnx.prepareStatement(requete);
        rs=pst.executeQuery(requete);
       Evenement E =new Evenement() ;
        while(rs.next()){                   
        E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getDate(6),rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
        
        }

        return E ;
    }
 
 
    
}
