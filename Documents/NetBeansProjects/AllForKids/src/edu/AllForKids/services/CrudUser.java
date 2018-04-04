/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class CrudUser {

    Connection cnx = MyConnexion.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public void ajouter_utilisateur(User u) {

        try {

            String query = "INSERT INTO user (username, email,password, roles,  Age,  Sexe, adresse) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRoles());
            pst.setInt(5, u.getAge());

            pst.setString(6, u.getSexe());

            pst.setString(7, u.getAdresse());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
        
         

    public User FindByEmail(String email) {
        try {
            String requete = "SELECT * FROM user where email='" + email + "'";
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            
            while (rs.next()) {
                
                User m = new User(rs.getInt("id"), 
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("sexe"),
                        rs.getString("roles"),
                        rs.getString("password"));
                return m;
            }
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public  User Authentification(String email , String password) throws SQLException{
        String requete=" SELECT * FROM user where email='"+email+"' AND password='"+password+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
   User m = new User(rs.getString("email"), rs.getString("password"));         
        return m ;
        }
        return null ;
    
    }
    
    
}
