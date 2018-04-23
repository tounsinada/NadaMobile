/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.RatingEntity;
import edu.AllForKids.entities.Reservation;
import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudEvenement;
import edu.AllForKids.services.CrudRating;
import edu.AllForKids.services.CrudReservation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

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
    CrudRating CRate = new CrudRating();
    RatingEntity Rate = new RatingEntity();

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
                if (E.getTicket_disponible() > 0) {
                    E.setEtat("Disponible");
                } else {
                    E.setEtat("Complet");
                    CE.UpdateEtat(E.getId_even(), "Complet");
                    System.out.println("eta even complet   " + E.getEtat());

                }

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

                Label TichetDispo = new Label("Nombre de tickets disponibles : " + E.getTicket_disponible());
                TichetDispo.setFont(new Font("Arial", 14));

                Label Lieu = new Label("Lieu :" + E.getLieu());
                Lieu.setTextFill(Color.web("#17202A"));

                Label categorie = new Label("categorie :" + E.NomCategorie(E.getId_cat()));
                categorie.setTextFill(Color.web("#17202A"));

                Label etat = new Label("Etat :" + E.getEtat());
                etat.setTextFill(Color.web("#17202A"));
                System.out.println("mmmmmmmmmmmmmm" + E.getEtat());

                //read more
                HBox Hbtn = new HBox(10);
                Button button = new Button("Voir Plus d'information");
                button.setStyle("-fx-background-color:  #2471A3");
                button.setTextFill(Color.web("#FBFCFC"));
                button.setVisible(true);
                button.setAccessibleText("" + E.getId_even());
                button.onActionProperty().set((event) -> {
                    try {
                        LisTEvent.setContent(AfficheEvent(button.getAccessibleText()));
                    } catch (SQLException ex) {

                    } catch (FileNotFoundException ex) {

                    }

                });
                if (E.getTicket_disponible() == 0) {
                    button.setVisible(false);

                }

                root.getChildren().addAll(IMG, sep, root2, Description);
                root3.getChildren().addAll(categorie, Datedebut, Lieu, Tarif, TichetDispo, etat);
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

        File file = new File("C:\\wamp64\\www\\PIN\\web\\images\\" + E.getNom_image());
        Image img = new Image(file.toURI().toString());
        ImageView IMG = new ImageView(img);

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

        Label TichetDispo = new Label("Nombre de tickets disponibles : " + E.getTicket_disponible());
        TichetDispo.setFont(new Font("Arial", 14));
        Label Tarif = new Label("Tarif : " + E.getTarif() + "DT");
        Tarif.setFont(new Font("Arial", 14));
        Label TichetAReserver = new Label("Nombre de tickets à reserver : ");
        TichetDispo.setFont(new Font("Arial", 14));
        TextField txtTicket = new TextField();
        txtTicket.setFont(new Font("Arial", 14));

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
        //bouton annuler reservation

        Button btnAnnulerReservation = new Button("Annuler");
        btnAnnulerReservation.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold");
        btnAnnulerReservation.setTextFill(Color.web("#FDFEFE"));
        btnAnnulerReservation.setVisible(false);

        CrudReservation CR = new CrudReservation();
        Button btnReserver = new Button("Reserver");
        btnReserver.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold");
        btnReserver.setTextFill(Color.web("#FDFEFE"));

        btnReserver.onActionProperty().set((event) -> {

            DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date date = new java.util.Date();

            if (!"".equals(txtTicket.getText())) {
                if (Integer.parseInt(txtTicket.getText()) <= E.getTicket_disponible() && Integer.parseInt(txtTicket.getText()) > 0) {

                    int ticketdispo = E.getTicket_disponible();
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Confirmer la reservation");
                    confirmation.setHeaderText("Confirmer");
                    confirmation.setContentText("Voulez-vous confirmer cette reservation ?");

                    Optional<ButtonType> result = confirmation.showAndWait();
                    if (result.get() == ButtonType.OK) {

                        String timedate = new SimpleDateFormat("yyyymmdd-hhmmss").format(Calendar.getInstance().getTime());
                        Reservation R = new Reservation(E.getId_even(), log.CurrentUser.getId(), Integer.parseInt(txtTicket.getText()));
                        CR.ajouterReservation(R);
                        JOptionPane.showMessageDialog(null, " Votre reservation est enregistrée");
                        System.out.println("reservation ajoutée");
                        ticketdispo = E.getTicket_disponible() - Integer.parseInt(txtTicket.getText());
                        System.out.println("ticket nouv dans e" + ticketdispo);
                        // System.out.println(ticketdispo);
                        E.setTicket_disponible(ticketdispo);
                        CrudEvenement CE = new CrudEvenement();
                        CE.decrementationTicket(E.getId_even(), ticketdispo);
                        TichetDispo.setText("Nombre de tickets disponibles : " + ticketdispo);
                        System.out.println("nouv e " + E);
                        btnReserver.setVisible(false);
                        btnAnnulerReservation.setVisible(true);
                        btnAnnulerReservation.onActionProperty().set((eventAction) -> {
                            System.out.println("ticket dispo apre reservation" + E.getTicket_disponible());

                            int dispo = E.getTicket_disponible();

                            Alert annuler = new Alert(Alert.AlertType.CONFIRMATION);
                            annuler.setTitle("Confirmer l'annulation");
                            annuler.setHeaderText("Confirmer");
                            annuler.setContentText("Voulez-vous annuler cette reservation ?");

                            Optional<ButtonType> result2 = annuler.showAndWait();
                            if (result2.get() == ButtonType.OK) {
                                dispo = E.getTicket_disponible() + Integer.parseInt(txtTicket.getText());
                                E.setTicket_disponible(dispo);

                                CE.IncrementationTicket(E.getId_even(), dispo);
                                System.out.println("reservation annulé" + dispo);

                                System.out.println("reservation annulé" + dispo);
                                TichetDispo.setText("Nombre de tickets disponibles : " + dispo);

                                btnAnnulerReservation.setVisible(false);
                            }

                        });
                        //System.out.println("///////" + E.getTicket_disponible());
                        if (ticketdispo == 0) {

                            E.setEtat("complet");
                            this.CE.ChangementEtat(E.getId_even(), "complet");
                            System.out.println("etat e" + E.getEtat());
                            btnReserver.setVisible(false);

                        }

                        // System.out.println("nombre valide");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, " Le nombre entré est invalide");
                }

            }

        });
        ////rating
        Rating rating = new Rating();
        Label rate = new Label("Rating");
        rate.setFont(new Font("Arial", 14));

        Label labelN = new Label();
        labelN.setFont(new Font("Bradley Hand ITC", 30));
        labelN.setStyle(" -fx-background-color:#4ECDC4 ");
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number r) {
                rate.setText("rating " + r.toString());

                System.out.println("ok aeb rate  " + r.intValue());

                Rate = new RatingEntity(r.intValue(), log.CurrentUser.getId(), E.getId_even());
                CRate.ajouterRating(Rate);

                labelN.setText("la note attribuée est" + "   " + r.intValue() + " " + "par le membre:" + "  " + log.CurrentUser.getUsername());
                rating.setVisible(false);

            }
        });

        H0.getChildren().setAll(btnRetour);
        V1.getChildren().addAll(H0, IMG, Titre);
        HBox H4 = new HBox(6);
        H4.getChildren().addAll(TichetAReserver, txtTicket, btnReserver);
        HBox H6 = new HBox(6);
        H6.getChildren().addAll(rate, rating, labelN);

        HBox H5 = new HBox(6);
        H5.getChildren().addAll(Descrip);

        V2.getChildren().addAll(catgeorie, DateDebut, DateFin, Tarif, TichetDispo, btnAnnulerReservation);
        VBox V3 = new VBox(10);
        VBox V4 = new VBox(8);
        V3.getChildren().addAll(H5, V2, H4, H6);

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

    }

    @FXML
    private void SearchByNom(KeyEvent event) {
        try {
            LisTEvent.setContent(ListofSearchEvent());
        } catch (SQLException ex) {

        }
    }

    public Node ListofSearchEvent() throws SQLException {
        VBox root0 = new VBox(10);
        ObservableList<Evenement> OB = FXCollections.observableArrayList();
        OB = CE.Search(Titre.getText());

        for (int i = 0; i < OB.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
            root.setPadding(new Insets(5, 5, 5, 5));
            try {
                Evenement E = OB.get(i);

                //affiche image
                File file = new File("C:\\wamp64\\www\\PIN\\web\\images\\" + E.getNom_image());
                Image img = new Image(file.toURI().toString());
                ImageView IMG = new ImageView(img);
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

                Label TichetDispo = new Label("Nombre de tickets disponibles : " + E.getTicket_disponible());
                TichetDispo.setFont(new Font("Arial", 14));

                Label Lieu = new Label("Lieu :" + E.getLieu());
                Lieu.setTextFill(Color.web("#17202A"));

                Label categorie = new Label("categorie :" + E.NomCategorie(E.getId_cat()));
                categorie.setTextFill(Color.web("#17202A"));

                Label etat = new Label("Etat :" + E.getEtat());
                etat.setTextFill(Color.web("#17202A"));

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

                if (E.getTicket_disponible() == 0) {
                    //E.setEtat("Complet");
                    button.setVisible(false);
                    CE.UpdateEtat(E.getId_even(), "Complet");
                    System.out.println("eta even complet   " + E.getEtat());

                }

                root.getChildren().addAll(IMG, sep, root2, Description);
                root3.getChildren().addAll(categorie, Datedebut, Lieu, Tarif, TichetDispo, etat);
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

    @FXML
    private void ChecherParNom(ActionEvent event) {
    }

    public Node ListofFiltreEvent(String type) throws SQLException {
        VBox root0 = new VBox(10);

        ObservableList<Evenement> OB = FXCollections.observableArrayList();
        OB = CE.displayFiltreEvenement(1);

        for (int i = 0; i < OB.size(); i++) {
            //OB = CE.displayAllEvenement();

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
            root.setPadding(new Insets(5, 5, 5, 5));
            try {
                Evenement E = OB.get(i);

                //affiche image
                   File file = new File("C:\\wamp64\\www\\PIN\\web\\images\\" + E.getNom_image());
                Image img = new Image(file.toURI().toString());
                ImageView IMG = new ImageView(img);
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

                Label TichetDispo = new Label("Nombre de tickets disponibles : " + E.getTicket_disponible());
                TichetDispo.setFont(new Font("Arial", 14));

                Label Lieu = new Label("Lieu :" + E.getLieu());
                Lieu.setTextFill(Color.web("#17202A"));

                Label categorie = new Label("categorie :" + E.NomCategorie(E.getId_cat()));
                categorie.setTextFill(Color.web("#17202A"));

                Label etat = new Label("Etat :" + E.getEtat());
                etat.setTextFill(Color.web("#17202A"));

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

                if (E.getTicket_disponible() == 0) {
                    //E.setEtat("Complet");
                    button.setVisible(false);
                    CE.UpdateEtat(E.getId_even(), "Complet");
                    System.out.println("eta even complet   " + E.getEtat());

                }

                root.getChildren().addAll(IMG, sep, root2, Description);
                root3.getChildren().addAll(categorie, Datedebut, Lieu, Tarif, TichetDispo, etat);
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

    @FXML
    private void Filtrer(ActionEvent event) {

        String categorie = "''";
        if (Rando.isSelected()) {
            categorie += " or categorie_id ='4'";
        }
        if (Cultiver.isSelected()) {
            categorie += "or categorie_id ='1'";
        }
        if (Cinema.isSelected()) {
            categorie += " or categorie_id ='3'";
        }
        if (Distraire.isSelected()) {
            categorie += " or categorie_id ='2'";
        }
        if (autre.isSelected()) {
            categorie += " or categorie_id ='5'";
        }

        try {
            LisTEvent.setContent(ListofFiltreEvent(categorie));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void MesReservation(ActionEvent event) {

        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ReservationCurrentUser.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvenementFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
