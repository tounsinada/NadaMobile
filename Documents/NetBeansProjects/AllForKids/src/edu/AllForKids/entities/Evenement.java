/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.AllForKids.entities;

import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import javax.sql.DataSource;


/**
 *
 * @author LENOVO
 */
public class Evenement {
        Connection cnx = MyConnexion.getInstance().getConnection();

    private int id_even;
    private String titre;
    private Categorie Categorie;
    private String nom_image;
    private Date date_debut;
        private Date date_fin;
        private int id_cat;
    private String lieu;

    private String description;
    private int ticket_disponible	;
    private int Tarif;

    public Evenement() {
    }

    public Evenement( String titre, Categorie Categorie, String nom_image, Date date_debut, Date date_fin, String lieu, String description, int ticket_disponible, int Tarif) {
        this.titre = titre;
        this.Categorie= Categorie;
        this.nom_image = nom_image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
        this.description = description;
        this.ticket_disponible = ticket_disponible;
        this.Tarif = Tarif;
    }
    
        public Evenement( String titre, int id_cat, String nom_image, Date date_debut, Date date_fin, String lieu, String description, int ticket_disponible, int Tarif) {
        this.titre = titre;
        this.id_cat= id_cat;
        this.nom_image = nom_image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
        this.description = description;
        this.ticket_disponible = ticket_disponible;
        this.Tarif = Tarif;
    }

    public Evenement(int id_even, int id_cat, String titre, String nom_image, String lieu, Date date_debut, Date date_fin, String description, int ticket_disponible, int Tarif) {
        this.id_even = id_even;
        this.titre = titre;
        this.nom_image = nom_image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_cat = id_cat;
        this.lieu = lieu;
        this.description = description;
        this.ticket_disponible = ticket_disponible;
        this.Tarif = Tarif;
    }
        

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
        
        

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Categorie getCategorie() {
        return Categorie;
    }

  

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicket_disponible() {
        return ticket_disponible;
    }

    public void setTicket_disponible(int ticket_disponible) {
        this.ticket_disponible = ticket_disponible;
    }

    public int getTarif() {
        return Tarif;
    }

    public void setTarif(int Tarif) {
        this.Tarif = Tarif;
    }

    @Override
    public String toString() {
        return "evenement{"+ ", titre=" + titre + ", Categorie_id=" + Categorie+ ", nom_image=" + nom_image + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", lieu=" + lieu + ", description=" + description + ", ticket_disponible=" + ticket_disponible + ", Tarif=" + Tarif + '}';
    }

   

    
    
   
    
    
}
