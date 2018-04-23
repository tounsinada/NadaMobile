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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
            String query = "INSERT INTO user (username, email,password, roles,  Age,  Sexe, adresse,nom_image,enabled) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRoles());
            pst.setInt(5, u.getAge());
            pst.setString(6, u.getSexe());
            pst.setString(7, u.getAdresse());
            pst.setString(8, u.getNom_image());
                        pst.setInt(9, u.getEnabled());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

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

    public User Authentification(String email, String password) throws SQLException {
        String requete = " SELECT * FROM user where email='" + email + "' AND password='" + password + "'";
        ste = cnx.createStatement();
        rs = ste.executeQuery(requete);

        while (rs.next()) {

            User m = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("enabled"),
                    rs.getString("password"), rs.getString("roles"), rs.getString("nom_image"));

            return m;
        }
        return null;

    }

    public List<User> displayAllUsers() throws SQLException {
        String requete = "SELECT * FROM user";
        ste = cnx.createStatement();
        rs = ste.executeQuery(requete);
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User m = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("enabled"),
                    rs.getString("password"), rs.getString("roles"), rs.getString("nom_image"));
            list.add(m);
        }
        return list;
    }

    public void BanUser(User u) throws SQLException {
        String requete = "Update user set enabled=2 where id=" + u.getId();
        pst = cnx.prepareStatement(requete);
        pst.executeUpdate();
    }

    public void UnBanUser(User u) throws SQLException {
        String requete = "Update user set enabled=1 where id=" + u.getId();
        pst = cnx.prepareStatement(requete);
        pst.executeUpdate();
    }
    
      public void RegisterUser(int id) throws SQLException {
        String requete = "Update user set enabled=1 where id=" + id;
        pst = cnx.prepareStatement(requete);
        pst.executeUpdate();
    }

    public List<User> FindUserByNameOrLastname(String mot) throws SQLException {
        String requete = "SELECT * FROM user where (username LIKE '%" + mot + "%' OR email LIKE '%" + mot + "%')";
        ste = cnx.createStatement();
        rs = ste.executeQuery(requete);
        List<User> list = new ArrayList<>();
        while (rs.next()) {

            User u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("enabled"),
                    rs.getString("password"), rs.getString("roles"), rs.getString("nom_image"));
            list.add(u);
        }
        return list;
    }

    public List<User> displayUsersByRolels(String role) throws SQLException {
        String requete = "SELECT * FROM user where roles= " + role + " ";
        pst = cnx.prepareCall(requete);
        rs = pst.executeQuery(requete);
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getInt("enabled"),
                    rs.getString("password"), rs.getString("roles"), rs.getString("nom_image"));
            list.add(u);
        }
        return list;
    }

    public void addToken(int token, String email) throws SQLException {
        //
        String req = "UPDATE `user` SET `confirmation_token`=" + token + ",`password_requested_at`=SYSDATE() WHERE email=\"" + email + "\"";

        try {
            int i = ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isValidToken(String get, String text) {
        String token = null;
        String date = null;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String req = "SELECT * FROM `user` WHERE email =\'" + text + "\'";
        try {
            rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {
                    token = rs.getString(10);
                    date = rs.getString(11);
                    System.out.println(rs.toString());

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (date == null || token == null) {
            System.out.println("null vale" + date + " token" + token);
            return false;
        } else {
            String jj, mm, yy, dd, hh, m;
            //vale2018-03-21 19:32:38.05877
            yy = date.substring(2, 4);
            mm = date.substring(5, 7);
            dd = date.substring(8, 10);
            hh = date.substring(11, 13);
            m = date.substring(14, 16);
            System.out.println("year" + yy + "month" + mm + "day" + dd + "hour" + hh + "minut" + m);

            String jj1, mm1, yy1, dd1, hh1, m1, hh2;
            //vale2018-03-21 19:32:38.05877
            yy1 = timeStamp.substring(2, 4);
            mm1 = timeStamp.substring(5, 7);
            dd1 = timeStamp.substring(8, 10);
            hh1 = timeStamp.substring(11, 13);
            m1 = timeStamp.substring(14, 16);
            hh2 = hh + 1;
            System.out.println("year" + yy1 + "month" + mm1 + "day" + dd1 + "hour" + hh1 + "minut" + m1);

            System.out.println("vale " + date + token);
            if (get.equals(token) && yy.equals(yy1) && mm.equals(mm1) && dd.equals(dd1) && (hh.equals(hh1) || hh2.equals(hh1))) {
                return true;
            }
        }
        return false;
    }

}
