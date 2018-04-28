/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceEvenement {

    /* public void ajoutEvenement(Evenement E) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http:http://localhost/PI4/web/app_dev.php/pi/allEvent/" +E.getTitre() + "/" + E.getEtat();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel assynchrone ma nab3ith illa ma tjini reponse
        //envoie requete puis attend
    }
     */
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

//                        even.setTitre(i.get("Categorie").toString());
                        even.setTitre(i.get("titre").toString());
                        even.setDescription(i.get("description").toString());

                        String dateDeb = (String) i.get("date_debut");
                        String DD = dateDeb.substring(0, 10);
                        even.setDate_debut(DD);

                        /*  String dateFin= (String) i.get("date_fin");
                        String Df = dateFin.substring(0, 10);
                        even.setDate_fin(Df);*/
                        even.setLieu(i.get("lieu").toString());
                        even.setEtat(i.get("etat").toString());

                        float TicketDispo = Float.parseFloat(i.get("ticket_disponible").toString());
                        even.setTicket_disponible((int) TicketDispo);
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

}
