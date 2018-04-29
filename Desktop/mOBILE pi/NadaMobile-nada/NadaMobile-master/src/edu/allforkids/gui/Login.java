/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author LENOVO
 */
public class Login {

    Form f = new Form("Ajout Even", new BoxLayout(BoxLayout.Y_AXIS));

    public Login() {
        TextField login = new TextField();
        TextField mdp = new TextField();
        mdp.setHint("password");
        mdp.setConstraint(TextField.PASSWORD);
        Label l = new Label();

        Button btn = new Button("Se Connecter");

        f.add(login);
        f.add(mdp);
        f.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                System.out.println("actionPerformed()");
                String log = login.getText();
                String pass = mdp.getText();

                if (log.equals("nada") && pass.equals("123")) {

                    AfficheEvenement even = new AfficheEvenement();
                    even.getF().show();
                    System.out.println("login et mdp valide");
                } else {
                    Dialog.show("Probléme", "Mot de passe ou login faux", "ok", "cancel");
                    System.out.println(" mdp login faux");
                }

            }
            
        });

    }

    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
    
}
