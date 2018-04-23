/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Categorie;
import edu.AllForKids.entities.Evenement;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class CrudCategorie {

    Connection cnx = MyConnexion.getInstance().getConnection();

    public void ajouterCategorie(Categorie c) {
        try {
            String requete = "INSERT INTO categorie (nomCategorie) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, c.getNomCategorie());

            pst.executeUpdate();
            System.out.println("Categorie ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierCategorie(Categorie c, int id) {
        try {
            String requete = "UPDATE Categorie SET nomCategorie=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, id);
            pst.setString(1, c.getNomCategorie());
            pst.executeUpdate();
            System.out.println("update categorie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<String> afficherCategorie() {
        List<String> listeCategorie = new ArrayList<String>();
       
        try {

            String req = "SELECT * FROM categorie";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                 String e = new String();
                
                e=rs.getString(2);
                
                

                listeCategorie.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listeCategorie;
    }

    
    public int findCategorieByName(String cat) throws SQLException
    {
         String req = "SELECT * FROM categorie WHERE nomCategorie=?";
         
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, cat);
        
            ResultSet rs = st.executeQuery();
          while(rs.next())
           
              return rs.getInt("id");
        return -1;
    }
    public String findNomCategorieById(int IdCat) throws SQLException
    {
         String req = "SELECT * FROM categorie WHERE id=?";
         
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, IdCat);
        
            ResultSet rs = st.executeQuery();
          while(rs.next())
           
              return rs.getString("nomCategorie");
        return null;
    }
    
    
}
