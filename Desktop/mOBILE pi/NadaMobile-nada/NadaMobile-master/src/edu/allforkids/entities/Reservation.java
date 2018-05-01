/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.entities;


/**
 *
 * @author LENOVO
 */
public class Reservation {
     private int id_reservation;
    private int id_even;
    private int id_client;
    private int nbre_ticket;
    private String date_reservation;
   String nomEven;

    public Reservation() {
    }

    public Reservation( int id_even, int id_client, int nbre_ticket) {
        this.id_even = id_even;
        this.id_client = id_client;
        this.nbre_ticket = nbre_ticket;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getNbre_ticket() {
        return nbre_ticket;
    }

    public void setNbre_ticket(int nbre_ticket) {
        this.nbre_ticket = nbre_ticket;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getNomEven() {
        return nomEven;
    }

    public void setNomEven(String nomEven) {
        this.nomEven = nomEven;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", id_even=" + id_even + ", id_client=" + id_client + ", nbre_ticket=" + nbre_ticket + ", date_reservation=" + date_reservation + ", nomEven=" + nomEven + '}';
    }
   
   
}
