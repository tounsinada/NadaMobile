/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.allforkids.entities.Categorie;
import edu.allforkids.entities.Evenement;
import edu.allforkids.entities.Reservation;
import edu.allforkids.gui.AfficheEvenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class ServiceEvenement {

    /*///////////SERVICE EVENEMENt///////////////////////////////
    ///////////////&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$$$$$$/////////////////////////////////////*/
    public ArrayList<Evenement> getListEvenement() {
        ArrayList<Evenement> listEvenements = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/allEvent");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listEvenements = getListEvenement(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    System.out.println("//////" + list);

                    for (Map<String, Object> i : list) {
                        Evenement even = new Evenement();
                        float id = Float.parseFloat(i.get("id_even").toString());
                        even.setId_even((int) id);

                        even.setTitre(i.get("titre").toString());
                        even.setDescription(i.get("description").toString());

                        String dateDeb = (String) i.get("date_debut");
                        String DD = dateDeb.substring(0, 10);
                        even.setDate_debut(DD);

                        String dateFin = (String) i.get("date_fin");
                        String Df = dateFin.substring(0, 10);
                        even.setDate_fin(Df);
                        even.setLieu(i.get("lieu").toString());
                        even.setEtat(i.get("etat").toString());

                        float TicketDispo = Float.parseFloat(i.get("ticket_disponible").toString());
                        even.setTicket_disponible((int) TicketDispo);
                        System.out.println(i.get("nom_image").toString());
                        even.setNom_image(i.get("nom_image").toString());
                        even.setNomcateg(i.get("nomCategorie").toString());
                        float tarif = Float.parseFloat(i.get("Tarif").toString());
                        even.setTarif((int) tarif);
                        listEvenements.add(even);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }

    public void ajoutEvenement(Evenement Even) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/WSAjout/" + Even.getTitre() + "/" + Even.getNom_image() + "/" + Even.getLieu() + "/" + Even.getDate_debut() + "/" + Even.getDate_fin() + "/" + Even.getDescription() + "/" + +Even.getTicket_disponible() + "/" + Even.getTarif() + "/" + Even.getEtat() + "/" + Even.getId_categ();

        con.setUrl(Url);
        System.out.println("connexion");

        con.addResponseListener((e) -> {

            String str = new String(con.getResponseData());
            System.out.println(str);
        });

        NetworkManager.getInstance().addToQueueAndWait(con);//appel assynchrone ma nab3ith illa ma tjini reponse

    }

    public void UpdateEvent(Evenement ca) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PI4/web/app_dev.php/pi/UpdateEvent/" + ca.getId_even() + "?ticket_disponible=" + AfficheEvenement.decrement;
        con.setUrl(Url);
        System.out.println(Url);

        System.out.println("Modif test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    public void AnnulReserv(Reservation R) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PI4/web/app_dev.php/pi/DelReservation/" + R.getId_client() + "/" + R.getId_even();
        con.setUrl(Url);
        System.out.println(Url);

        System.out.println("annul reser");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    public ArrayList<Reservation> FindReserv(int iduser, int ideven) {
        ArrayList<Reservation> listresrvation = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/FinfReservation/" + iduser + "/" + ideven;
        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> r = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("rrrrrrrrrrrrrr" + r);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) r.get("root");
                    for (Map<String, Object> obj : list) {
                        Reservation reserv = new Reservation();

                        float idR = Float.parseFloat(obj.get("id_reservation").toString());
                        reserv.setId_reservation((int) idR);
                        float idE = Float.parseFloat(obj.get("id_even").toString());
                        reserv.setId_even((int) idE);

                        float idU = Float.parseFloat(obj.get("id_client").toString());
                        reserv.setId_client((int) idU);

                        float idT = Float.parseFloat(obj.get("nbre_ticket").toString());

                        reserv.setNbre_ticket((int) idT);
                        reserv.setDate_reservation((String) obj.get("date_reservation"));

                        listresrvation.add(reserv);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listresrvation;
    }

    public Reservation ReservationTrouver(int iduser, int ideven) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PI4/web/app_dev.php/pi/FinfReservation/" + iduser + "/" + ideven;
        con.setUrl(Url);
        System.out.println(Url);

        System.out.println("reser");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
        return null;
    }

    /*///////////SERVICE CATEGORIE///////////////////////////////
    ///////////////&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$$$$$$/////////////////////////////////////*/
    public ArrayList<Categorie> getAllCategorie() {
        ArrayList<Categorie> listCategorie = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/web/app_dev.php/pi/allcategorie");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser jsonp = new JSONParser();
                    Map<String, Object> categ = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) categ.get("root");

                    for (Map<String, Object> obj : list) {
                        Categorie cat = new Categorie();
                        float idD = Float.parseFloat(obj.get("id").toString());
                        String nomcategorie = obj.get("nomCategorie").toString();

                        cat.setId((int) idD);
                        cat.setNomCategorie(nomcategorie);

                        listCategorie.add(cat);

                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;

    }

    public Categorie getIDCATEG(String nom) {
        ConnectionRequest con = new ConnectionRequest();
        Categorie cat = new Categorie();

        String Url = "http://localhost/PI4/web/app_dev.php/pi/getidcateg/" + nom;

        con.setUrl(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    JSONParser jsonp = new JSONParser();
                    Map<String, Object> categ = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //  List<Map<String, Object>> list = (List<Map<String, Object>>) categ.get("root");
                    float id = Float.parseFloat(categ.get("id").toString());
                    cat.setId((int) id);

                } catch (IOException ex) {
                    System.err.println("erreur id categorie dans ajout");
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);//appel assynchrone ma nab3ith illa ma tjini reponse
        return cat;

    }

    /*///////////SERVICE RESERVATION///////////////////////////////
    ///////////////&&&&&&&&&&&&&&&$$$$$$$$$$$$$$$$$$$$$$$$$/////////////////////////////////////*/
    public void ajoutReservation(Reservation reserv) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/PI4/web/app_dev.php/pi/WSAjoutReservation/" + reserv.getId_even() + "/" + reserv.getId_client() + "/" + reserv.getDate_reservation() + "/" + reserv.getNbre_ticket();
        System.out.println(Url);
        con.setUrl(Url);
        System.out.println("connexion");

        con.addResponseListener((e) -> {

            String str = new String(con.getResponseData());
            System.out.println(str);
        });

        NetworkManager.getInstance().addToQueueAndWait(con);//appel assynchrone ma nab3ith illa ma tjini reponse

    }

    public void UpdateEtatEvent(Evenement ca) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PI4/web/app_dev.php/pi/UpdateEtat/" + ca.getId_even() + "?etat=" + "Complet";
        con.setUrl(Url);
        System.out.println(Url);

        System.out.println("Modif test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    
    
    
    
}
