/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.RatingEntity;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class CrudRating {
    
    Connection cnx = MyConnexion.getInstance().getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public void ajouterRating(RatingEntity r) {
        try {
            String requete = "INSERT INTO rating (rating,idUser,idEvenement) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
          // pst.setInt(1,r.getId() );
            pst.setInt(1, r.getRating());
            pst.setInt(2, r.getIdUser());
            pst.setInt(3, r.getIdEvenement());
           
            pst.executeUpdate();
            System.out.println("Rating ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
}
