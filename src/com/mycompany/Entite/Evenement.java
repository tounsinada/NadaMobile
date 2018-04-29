/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;





/**
 *
 * @author LENOVO
 */
public class Evenement {
    
    private int id_even;
    private String titre;
   // private Categorie Categorie;
    private String nom_image;
    private String date_debut;
        private String date_fin;
       // private int id_cat;
    private String lieu;
        private String etat;

    private String description;
    private int ticket_disponible	;
    private int Tarif;
    String nomcateg;
   // CrudCategorie C;

    public Evenement() {
    }

    public Evenement(int id_even, String titre,  String nom_image, String date_debut, String date_fin,  String lieu, String etat, String description, int ticket_disponible, int Tarif, String nomcateg) {
        this.id_even = id_even;
        this.titre = titre;
        this.nom_image = nom_image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
        this.etat = etat;
        this.description = description;
        this.ticket_disponible = ticket_disponible;
        this.Tarif = Tarif;
        this.nomcateg = nomcateg;
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

   

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

   

    
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public String getNomcateg() {
        return nomcateg;
    }

    public void setNomcateg(String nomcateg) {
        this.nomcateg = nomcateg;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_even=" + id_even + ", titre=" + titre + ", nom_image=" + nom_image + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", lieu=" + lieu + ", etat=" + etat + ", description=" + description + ", ticket_disponible=" + ticket_disponible + ", Tarif=" + Tarif + ", nomcateg=" + nomcateg + '}';
    }
    
    
}
