/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompagny.Service.ServiceEvenement;
import com.mycompany.Entite.Evenement;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.ComboBox;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tfTitre, tfDescription, tfTarif, tfPlaceDispo;
    TextField tetat;
    Button btnajout, btnaff;
    ComboBox Catgorie;

    public HomeForm() {
        f = new Form("home");
        tfTitre = new TextField();
        tfDescription = new TextField();
        tfTarif = new TextField();
        tfPlaceDispo = new TextField();
        Catgorie = new ComboBox();
        Catgorie.addItem("anniversaire");
        Catgorie.addItem("randonnée");

        Picker dateDebut = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);

        Picker dateFin = new Picker();
        dateFin.setType(Display.PICKER_TYPE_DATE);

        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
        f.add(tfDescription);
        f.add(tfPlaceDispo);
        f.add(tfTarif);
        f.add(Catgorie);
        f.add(tfTitre);
        f.add(dateFin);
        f.add(dateDebut);
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceEvenement ser = new ServiceEvenement();
            // Task t = new Evenement(0, titre, Categorie, nom_image, date_debut, date_fin, 0, lieu, etat, description, 0, 0, nomcateg)
            // ser.ajoutTask(t);

        });
        btnaff.addActionListener((e) -> {
            AfficheEvenement a = new AfficheEvenement();
            a.getF().show();
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

    public TextField getTetat() {
        return tetat;
    }

    public void setTetat(TextField tetat) {
        this.tetat = tetat;
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
        return Catgorie;
    }

    public void setCatgorie(ComboBox Catgorie) {
        this.Catgorie = Catgorie;
    }

  

}
