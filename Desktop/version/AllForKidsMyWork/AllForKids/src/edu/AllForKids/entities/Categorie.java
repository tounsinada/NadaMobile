/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.AllForKids.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Categorie(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        listEvenement = new ArrayList<Evenement>();
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

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nomCategorie=" + nomCategorie + '}';
    }
    
    
}
