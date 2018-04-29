/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import edu.allforkids.services.ServiceEvenement;
import edu.allforkids.entities.Evenement;
import edu.allforkids.entities.Reservation;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AfficheEvenement {

    Form f;
    SpanLabel lb;
    private Resources theme;

    public AfficheEvenement() {
        theme = UIManager.initFirstTheme("/theme");

        f = new Form("Tous les évènements");
        lb = new SpanLabel("");
        f.add(lb);
        ServiceEvenement serviceEvenement = new ServiceEvenement();
        ArrayList<Evenement> list = serviceEvenement.getListEvenement();
        for (Evenement i : list) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lbTitre = new Label("Titre :");
            Label titre = new Label(i.getTitre());
            Container cTitre = new Container(new BoxLayout(BoxLayout.X_AXIS));
            cTitre.add(lbTitre);
            cTitre.add(titre);

            Label lbDescrip = new Label("Description :");
            SpanLabel description = new SpanLabel(i.getDescription());
            Container cDescrip = new Container(new BoxLayout(BoxLayout.X_AXIS));
            cDescrip.add(lbDescrip);
            cDescrip.add(description);

            Label lbEtat = new Label("Etat :");
            Label etat = new Label(i.getEtat());
            Container cEtat = new Container(new BoxLayout(BoxLayout.X_AXIS));
            cEtat.add(lbEtat);
            cEtat.add(etat);

            ImageViewer img = new ImageViewer();
            Image placeholder = Image.createImage(200, 200, 0xbfc92d);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image imgs = URLImage.createToStorage(encImage, i.getNom_image(), "http://localhost/PI4/web/images/" + i.getNom_image(), URLImage.RESIZE_SCALE);
            img.setImage(imgs);
            c1.add(img);
            c1.add(cTitre);
            c1.add(cEtat);
            c1.add(cDescrip);

            Button ReadMore = new Button("ReadMore");
            c1.add(ReadMore);
            ReadMore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form F2 = new Form(i.getTitre(), new BoxLayout(BoxLayout.Y_AXIS));
                    Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    ImageViewer img = new ImageViewer();
                    Image placeholder = Image.createImage(200, 200, 0xbfc92d);
                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                    Image imgs = URLImage.createToStorage(encImage, i.getNom_image(), "http://localhost/PI4/web/images/" + i.getNom_image(), URLImage.RESIZE_SCALE);
                    img.setImage(imgs);
                    c2.add(img);

                    Label lbDescrip = new Label("Description :");
                    SpanLabel description = new SpanLabel(i.getDescription());
                    Container cDescrip = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cDescrip.add(lbDescrip);
                    cDescrip.add(description);
                    c2.add(cDescrip);

                    Label lbEtat = new Label("Etat :");
                    Label etat = new Label(i.getEtat());
                    Container cEtat = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cEtat.add(lbEtat);
                    cEtat.add(etat);

                    Label lbLieu = new Label("Lieu :");
                    Label lieu = new Label(i.getLieu());
                    Container cLieu = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cLieu.add(lbLieu);
                    cLieu.add(lieu);

                    Label lbTarif = new Label("Tarif :");
                    Label tarif = new Label("" + i.getTarif());
                    Container cTarif = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cTarif.add(lbTarif);
                    cTarif.add(tarif);

                    Label lbTicket = new Label("Tickets disponible :");
                    Label ticketDispo = new Label("" + i.getTicket_disponible());
                    Container cTicketDidpo = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cTicketDidpo.add(lbTicket);
                    cTicketDidpo.add(ticketDispo);

                    Label lbDD = new Label("Date début :");
                    Label DD = new Label(i.getDate_debut());
                    Container cDD = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cDD.add(lbDD);
                    cDD.add(DD);

                    Label lbDF = new Label("Date Fin :");
                    Label DF = new Label(i.getDate_fin());
                    Container cDF = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cDF.add(lbDF);
                    cDF.add(DF);

                    c2.add(cEtat);
                    c2.add(cDD);
                    c2.add(cDF);
                    c2.add(cLieu);
                    c2.add(cTarif);
                    c2.add(cTicketDidpo);

                    Label lbNBTicket = new Label("Nombre ticket à reserver");
                    TextField tfNbTicket = new TextField();
                    Button btnReserver = new Button("Reserver");
                    c2.add(lbNBTicket);
                    c2.add(tfNbTicket);
                    c2.add(btnReserver);
                    F2.getToolbar().addCommandToLeftBar("back", theme.getImage("back-command.png"), b -> {
                        f.showBack();
                    });

                    btnReserver.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (Integer.parseInt(tfNbTicket.getText()) > 0) {
                                if (Integer.parseInt(tfNbTicket.getText()) < i.getTicket_disponible()) {
                                    if (Dialog.show("Confirmation", "Voulez_vous reserver cet evenemenent" + " " + i.getTitre(), "ok", "cancel")) {
                                        Login user = new Login();
                                        Reservation reservation = new Reservation();

                                        int decrement = i.getTicket_disponible() - Integer.parseInt(tfNbTicket.getText());
                                        i.setTicket_disponible(decrement);
                                        System.out.println(decrement);
                                    };

                                } else {

                                    Dialog.show("Alert", "Le nombre de tickets à reserver est supérieur au nombre de tickets disponibles", "ok", "cancel");

                                }
                            } else {

                                if (Dialog.show("Alert", "Vous ne pouvez pas entrer un nombre de tickets inferieur à 0", "ok", "cancel"));
                                tfNbTicket.setText("");

                            }

                        }
                    });

                    F2.add(c2);
                    F2.show();

                    //Rating
                    /* Container starSelect = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    for (int iter = 0; iter < 5; iter++) {
                        createStarButton(starSelect);
                    }
                    
                    void createStarButton(final Container parent) {
    final CheckBox cb = new CheckBox();
    cb.setToggle(cb.setIcon(unselected));
                }       */
                }

            });

            f.add(c1);
            f.show();

            /* f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });*/
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
