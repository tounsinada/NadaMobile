/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.Reservation;
import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudEvenement;
import edu.AllForKids.services.CrudReservation;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EvenementFrontEndController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField Titre;
    @FXML
    private CheckBox Cultiver;
    @FXML
    private CheckBox Distraire;
    @FXML
    private CheckBox Cinema;
    @FXML
    private CheckBox Rando;
    @FXML
    private CheckBox autre;
    @FXML
    private ScrollPane LisTEvent;
    @FXML
    private Button ChercherTitre;
    public static User CurrentUser;

    CrudEvenement CE = new CrudEvenement();
        CrudReservation CR = new CrudReservation();
        Date date;
        LoginController log;

    Evenement E = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            LisTEvent.setContent(ListofAllEvent());

        } catch (SQLException ex) {

        }

    }

    public Node ListofAllEvent() throws SQLException {

        VBox root0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);

        ObservableList<Evenement> OB = FXCollections.observableArrayList();
        OB = CE.displayAllEvenement();

        for (int i = 0; i < OB.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
            root.setPadding(new Insets(5, 5, 5, 5));
            try {
                Evenement E = OB.get(i);

                //affiche image
                Image image = new Image("file:" + E.getNom_image());
                ImageView IMG = new ImageView(image);
                IMG.fitHeightProperty().set(200);
                IMG.fitWidthProperty().set(200);
                IMG.preserveRatioProperty().set(true);
                Separator sep = new Separator(Orientation.VERTICAL);
                VBox root2 = new VBox(6);
                root2.prefWidthProperty().set(1000);
                root2.prefHeightProperty().set(200);
                root2.setPadding(new Insets(4, 4, 4, 4));

                //les information de evene
                //*****titre
                Label Titre = new Label(E.getTitre());
                Titre.setFont(new Font("Arial", 30));
                Titre.setTextFill(Color.web("#17202A"));
                //*******description
                Label Description = new Label(E.getDescription());
                Description.backgroundProperty().set(Background.EMPTY);
                Description.setTextFill(Color.web("#17202A"));
                VBox root3 = new VBox(3);
                Separator sep2 = new Separator(Orientation.HORIZONTAL);
                //*****date
                Label Datedebut = new Label("Date debut: " + E.getDate_debut());
                Datedebut.setTextFill(Color.web("#17202A"));
                Label Datefin = new Label("Date fin : " + E.getDate_fin());
                Datefin.setTextFill(Color.web("#17202A"));
                Label Tarif = new Label("Tarif : " + E.getTarif());
                Tarif.setTextFill(Color.web("#17202A"));
                Label Lieu = new Label("Lieu :" + E.getLieu());
                Lieu.setTextFill(Color.web("#17202A"));
                Label categorie = new Label("categorie :" + E.getCategorie());
                categorie.setTextFill(Color.web("#17202A"));

                //read more
                HBox Hbtn = new HBox(10);
                Button button = new Button("Voir Plus d'information");
                button.setStyle("-fx-background-color:  #2471A3");
                button.setTextFill(Color.web("#FBFCFC"));
                button.setAccessibleText("" + E.getId_even());
                button.onActionProperty().set((event) -> {
                    try {
                        LisTEvent.setContent(AfficheEvent(button.getAccessibleText()));
                    } catch (SQLException ex) {

                    } catch (FileNotFoundException ex) {

                    }
                });

                root.getChildren().addAll(IMG, sep, root2, Description);
                root3.getChildren().addAll(Datedebut, Lieu, Tarif, categorie);
                Hbtn.getChildren().addAll(button);

                root2.getChildren().addAll(Titre, root3, Hbtn);

            } catch (Exception e) {
                System.out.println("exeption du try affiche all event");
            }

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new Insets(0, 0, 0, 0));

        }
        return root0;

    }

    public Node AfficheEvent(String id) throws SQLException, FileNotFoundException {

        E = CE.EvenementByID(Integer.parseInt(id));

        VBox V0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(6);
        V2.setAlignment(Pos.CENTER);

        Image image = new Image("file:" + E.getNom_image());
        ImageView IMG = new ImageView(image);
        //  IMG.preserveRatioProperty().set(true);

        //Titre
        HBox H1 = new HBox(10);
        V2.setAlignment(Pos.BASELINE_LEFT);

        Label Titre = new Label(E.getTitre());
        Titre.setFont(new Font("Ravie", 35));
        Titre.setAlignment(Pos.CENTER);
        TextArea Descrip = new TextArea(E.getDescription());
        Descrip.setWrapText(true);
        //Description
        Descrip.setFont(new Font("Arial", 14));
        Descrip.editableProperty().set(false);
        Descrip.setMaxHeight(150);
        //Detaille
        HBox H2 = new HBox(10);
        Label DateDebut = new Label("Date de début : " + E.getDate_debut());
        DateDebut.setFont(new Font("Arial", 14));
        Label DateFin = new Label("Date du fin: " + E.getDate_fin());
        DateFin.setFont(new Font("Arial", 14));
        Label Lieu = new Label("Lieu : " + E.getLieu());
        Lieu.setFont(new Font("Arial", 14));
        Lieu.setFont(new Font("Arial", 14));
        Label catgeorie = new Label("Categorie : " + E.NomCategorie(E.getId_cat()));
        catgeorie.setFont(new Font("Arial", 14));
        catgeorie.setFont(new Font("Arial", 14));

        Label TichetDispo = new Label("Nombre de tickets disponibles : " + E.getTicket_disponible());
        TichetDispo.setFont(new Font("Arial", 14));
        Label Tarif = new Label("Tarif : " + E.getTarif() + "DT");
        Tarif.setFont(new Font("Arial", 14));
        Label TichetAReserver = new Label("Nombre de tickets à reserver : ");
        TichetDispo.setFont(new Font("Arial", 14));
        TextField txtTicket = new TextField();

        VBox V1 = new VBox(10);

        Button btnRetour = new Button("< Retour ");
        btnRetour.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold");
        btnRetour.setTextFill(Color.web("#FDFEFE"));

        btnRetour.onActionProperty().set((event) -> {
            try {
                LisTEvent.setContent(ListofAllEvent());
            } catch (SQLException ex) {

            }
        });
        CrudReservation CR = new CrudReservation();
        Button btnReserver = new Button("Reserver");
        btnReserver.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold");
        btnReserver.setTextFill(Color.web("#FDFEFE"));

        btnReserver.onActionProperty().set((event) -> {
            

            
                
            try {
                DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                  System.out.println(date_format.parse(date_format.format(date)));

                System.out.println();
                Reservation R =new Reservation(E.getId_even(), log.CurrentUser.getId(), date_format.parse(date_format.format(date)), Integer.parseInt(txtTicket.getText()));
                
                
                
                CR.ajouterReservation(R);
                                JOptionPane.showMessageDialog(null, " Votre reservation est enregistrée");

                System.out.println("reservat"+"   "+R);
                System.out.println("reservation ajoutée");
            } catch (ParseException ex) {
                Logger.getLogger(EvenementFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            

            
        });

        H0.getChildren().setAll(btnRetour);
        V1.getChildren().addAll(H0, IMG, Titre);
        HBox H4 = new HBox(6);
        H4.getChildren().addAll(TichetAReserver, txtTicket, btnReserver);
        HBox H5 = new HBox(6);
        H5.getChildren().addAll(Descrip);

        V2.getChildren().addAll(catgeorie, DateDebut, DateFin, Tarif, TichetDispo);
        VBox V3 = new VBox(10);
        VBox V4 = new VBox(8);
        V3.getChildren().addAll(H5, V2, H4);

        H1.getChildren().addAll(V2);
        HBox H3 = new HBox(6);
        // V4.getChildren().addAll(Descrip,Catgeorie,DateDebut,DateFin,Tarif,TichetDispo);
        // H3.getChildren().addAll(V4);

        V3.getChildren().addAll(H1, H3);

        //HBox H2=new HBox(8);
        // H1.getChildren().setAll(V1);
        //VBox V3= new VBox(H0);
        V0.getChildren().addAll(V1, V2, V3);

        return V0;

        /* H1.getChildren().addAll(btnRetour);
        H1.setAlignment(Pos.CENTER_LEFT);
        Separator sephoriz = new Separator(Orientation.HORIZONTAL);
        IMG.fitWidthProperty().set(450);
        IMG.preserveRatioProperty().set(true);
        H0.getChildren().addAll(H1,IMG,Titre, sephoriz);
        H0.setAlignment(Pos.CENTER);
V1.getChildren().addAll( Descrip, H2, Lieu, Catgeorie, TichetDispo);
Separator sepvert=new Separator(Orientation.VERTICAL);
H2.getChildren().addAll(V1);
         */
    }

    @FXML
    private void SearchByNom(KeyEvent event) {
    }

    @FXML
    private void ChecherParNom(ActionEvent event) {
    }

    @FXML
    private void Filtrer(ActionEvent event) {
    }

    @FXML
    private void FiltrerParDate(ActionEvent event) {
    }
}
