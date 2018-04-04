/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.services.CrudEvenement;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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

    CrudEvenement CE = new CrudEvenement();
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

                root.getChildren().addAll(IMG, sep, root2, Description);
                root2.getChildren().addAll(Titre, root3);
                root3.getChildren().addAll(Datedebut, Lieu, Tarif, categorie);

            } catch (Exception e) {
                System.out.println("exeption du try affiche all event");
            }

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new Insets(0, 0, 0, 0));

        }
        return root0;

    }

    public Node AfficheEvent(Integer id) throws SQLException, FileNotFoundException {

        E = CE.EvenementByID(id);

        VBox V0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);

        Image image = new Image("file:" + E.getNom_image());
        ImageView IMG = new ImageView(image);
        IMG.preserveRatioProperty().set(true);

        HBox H1 = new HBox(8);
        Label Titre = new Label(E.getTitre());
        Titre.setFont(new Font("Arial", 30));

        HBox H2 = new HBox(10);
        Label Datedebut = new Label("Date début : " + E.getDate_debut());
        return V0;

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
