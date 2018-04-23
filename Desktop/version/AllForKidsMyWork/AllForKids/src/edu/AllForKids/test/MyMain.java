package edu.AllForKids.test;

import edu.AllForKids.entities.Categorie;
import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.Reservation;
import edu.AllForKids.entities.User;
import static edu.AllForKids.gui.EvenementFrontEndController.CurrentUser;
import edu.AllForKids.services.CrudCategorie;
import edu.AllForKids.services.CrudEvenement;
import edu.AllForKids.services.CrudReservation;
import edu.AllForKids.services.CrudUser;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import static org.controlsfx.control.WorldMapView.Country.CR;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seif BelHadjAli
 */
public class MyMain {
    public static void main(String[] args) throws SQLException, ParseException {
        //Categorie cat=new Categorie(1, "cammmm");
       // CrudCategorie crudCateg=new CrudCategorie();
       //crudCateg.ajouterCategorie(cat);
        //crudCateg.afficherCategorie();
      CrudEvenement CE=new  CrudEvenement();
     //  Evenement e=new Evenement(0, 0, titre, nom_image, lieu, date_debut, date_fin, description, 0, 0)
      // CE.afficherNomCateg();   
       // System.out.println("categ"+CE.afficherNomCateg(1)); 
       /* System.out.println("/////"+CE.displayAllEvenement());   */ 
       
       
       
        
                
              
                /* DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date date = new java.util.Date();
                CrudReservation CR=new CrudReservation();
         Reservation R = new Reservation(1,1, 2,1, date_format.parse(date_format.format(date)));
                System.out.println("**Res****" + R);
                CR.ajouterReservation(R);*/
              /*  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();
	System.out.println(dateFormat.format(date));*/
             /* CrudReservation CR=new CrudReservation();
             System.out.println(CR.findReservationByIdeven(1)); 
             System.out.println("liste"+CR.afficherReservation());
              */
            /*CrudReservation CR= new CrudReservation();
            CR.displayFiltreReseration(1);
            CR.displayReserationParUser(2);*/
              
              
                
        /*User utilisateur=new User("hh", "ggg", "uyy", "Parent", 0, "jjj", "hhhh");
        CrudUser crudutili=new CrudUser();
        crudutili.ajouter_utilisateur(utilisateur);*///
        
        
        
    }
    
}
