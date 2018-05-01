/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import edu.allforkids.entities.Categorie;
import edu.allforkids.entities.Evenement;
import edu.allforkids.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AjoutEvenement {

    TextField tfTitre, tfDescription, tfTarif, tfLieu, tfPlaceDispo;
    Button btnajout, btnaff;
    ComboBox ComboCatgorie;
    Form f = new Form("Ajout Evenement");

    public AjoutEvenement() {

        Label lbCategorie = new Label("Categorie:");
        ComboCatgorie = new ComboBox();

        ServiceEvenement se = new ServiceEvenement();
        ArrayList<Categorie> myList = new ArrayList<>();
        myList = se.getAllCategorie();

        for (Categorie item : myList) {
            ComboCatgorie.addItem(item.getNomCategorie());
        }

        Container cCateg = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cCateg.add(lbCategorie);
        cCateg.add(ComboCatgorie);

        tfDescription = new TextField();
        Label lbDescrip = new Label("Description :");
        Container cDescrip = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cDescrip.add(lbDescrip);
        cDescrip.add(tfDescription);

        tfTitre = new TextField();
        Label lbTitre = new Label("Titre :");
        Container cTitre = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cTitre.add(lbTitre);
        cTitre.add(tfTitre);

        tfLieu = new TextField();
        Label lbLieu = new Label("Lieu :");
        Container cLieu = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cLieu.add(lbLieu);
        cLieu.add(tfLieu);

        /* tfTarif = new TextField();
        Label lbTarif = new Label("Tarif :");
        Container cTarif = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cTarif.add(lbTarif);
        cTarif.add(tfTarif);*/
        tfPlaceDispo = new TextField();
        Label lbTicket = new Label("Tickets disponible :");
        Container cTicketDidpo = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cTicketDidpo.add(lbTicket);
        cTicketDidpo.add(tfPlaceDispo);

        Picker dateDebut = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        Label lbDD = new Label("Date début :");
        Container cDD = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cDD.add(lbDD);
        cDD.add(dateDebut);

        Picker dateFin = new Picker();
        dateFin.setType(Display.PICKER_TYPE_DATE);
        Label lbDF = new Label("Date Fin :");
        Container cDF = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cDF.add(lbDF);
        cDF.add(dateFin);

        btnajout = new Button("ajouter");
        f.add(cTitre);
        String stringdateD = new SimpleDateFormat("yyyy-MM-dd").format(dateDebut.getDate());
        String stringdateF = new SimpleDateFormat("yyyy-MM-dd").format(dateFin.getDate());

        f.add(cDescrip);
        f.add(cTicketDidpo);
        f.add(cLieu);
        f.add(cCateg);
        f.add(cDD);
        f.add(cDF);
        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            ServiceEvenement service = new ServiceEvenement();
            //int tarif= Integer.parseInt(tfTarif.getText());
            int ticket = Integer.parseInt(tfPlaceDispo.getText());
            Categorie categ = service.getIDCATEG(ComboCatgorie.getSelectedItem().toString());

            Evenement E = new Evenement(0, tfTitre.getText(), "image", stringdateD, stringdateF, tfLieu.getText(),
                    "Disponible", tfDescription.getText(), 1, ticket, categ.getId());
            service.ajoutEvenement(E);
            System.out.println(ComboCatgorie.getSelectedItem());
            System.out.println("even ajouté succée");
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTfTitre() {
        return tfTitre;
    }

    public void setTfTitre(TextField tfTitre) {
        this.tfTitre = tfTitre;
    }

    public TextField getTfDescription() {
        return tfDescription;
    }

    public void setTfDescription(TextField tfDescription) {
        this.tfDescription = tfDescription;
    }

    public TextField getTfTarif() {
        return tfTarif;
    }

    public void setTfTarif(TextField tfTarif) {
        this.tfTarif = tfTarif;
    }

    public TextField getTfPlaceDispo() {
        return tfPlaceDispo;
    }

    public void setTfPlaceDispo(TextField tfPlaceDispo) {
        this.tfPlaceDispo = tfPlaceDispo;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

    public ComboBox getCatgorie() {
        return ComboCatgorie;
    }

    public void setCatgorie(ComboBox Catgorie) {
        this.ComboCatgorie = Catgorie;
    }

}
