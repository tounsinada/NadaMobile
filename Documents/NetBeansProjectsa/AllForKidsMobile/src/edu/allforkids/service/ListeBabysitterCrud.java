/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionListener;
import edu.allforkids.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ListeBabysitterCrud {
   
    public ArrayList<User> getList2() {
        
        ArrayList<User> listbaby = new ArrayList<User>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PI4/PI4/web/app_dev.php/pi/listebabymobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                Container c1= new Container();
                
                try {
                    Map<String, Object> babysitters = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(babysitters);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) babysitters.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        User user = new User(0,"","",0,"","","","",0,"",0);

                        float id = Float.parseFloat(obj.get("id").toString()); 
                        user.setId((int) id);
                        user.setEtat(obj.get("etat").toString());
                        user.setUsername(obj.get("username").toString());
                        user.setAdresse(obj.get("adresse").toString());
                        float age = Float.parseFloat(obj.get("age").toString());
                        user.setAge((int)age);
                        user.setEmail(obj.get("email").toString());
                        float nbr = Float.parseFloat(obj.get("nbrAnneeExp").toString());
                        user.setNbrAnneeExp((int) nbr);
                        float numtel = Float.parseFloat(obj.get("numTel").toString());
                        user.setNumTe((int) numtel);
                        user.setSexe(obj.get("sexe").toString());
                        user.setNom_image(obj.get("nomImage").toString());
                        user.setVille(obj.get("ville").toString());
                        
                        listbaby.add(user);

                    }
                    
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listbaby;
    }
    
}
