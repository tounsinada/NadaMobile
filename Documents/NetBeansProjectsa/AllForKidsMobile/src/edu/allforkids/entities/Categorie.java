/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.entities;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Categorie {
    private int id;
    private String nomCategorie;
    private List <Evenement> listEvenement ;

    public Categorie() {
    }

    public Categorie(int id, String nomCategorie, List<Evenement> listEvenement) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.listEvenement = listEvenement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public List<Evenement> getListEvenement() {
        return listEvenement;
    }

    public void setListEvenement(List<Evenement> listEvenement) {
        this.listEvenement = listEvenement;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + ", listEvenement=" + listEvenement + '}';
    }
    
    
}
