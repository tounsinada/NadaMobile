/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.time.LocalDateTime;
import edu.AllForKids.entities.Categorie;
import edu.AllForKids.entities.Evenement;
import edu.AllForKids.services.CrudCategorie;
import edu.AllForKids.services.CrudEvenement;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEvenementController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextArea Description;
    @FXML
    private ImageView AfficheIMG;
    @FXML
    private TextField FilePath;
    @FXML
    private Button UploadImg;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField Lieu;

    @FXML
    private TextField Tarif;
    @FXML
    private TextField Ticketdispo;
    @FXML
    private ComboBox<String> Categorie;
    @FXML
    private Button AddEvent;
    @FXML
    private Button EditEvent;
    @FXML
    private Button supprimer;
    @FXML
    private TextField Chercher;
    @FXML
    private Button btnchercher;
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
    private Button filtre;
    @FXML
    private TableView<Evenement> eventList;
    @FXML
    private TableColumn<Evenement, Integer> EventID;
    @FXML
    private TableColumn<Evenement, String> TITRE;
    @FXML
    private TableColumn<Evenement, String> DESCRIPTION;
    @FXML
    private TableColumn<Evenement, Date> DATEDEBUT;
    @FXML
    private TableColumn<Evenement, Date> DATEFIN;
    @FXML
    private TableColumn<Evenement, String> LIEU;
    @FXML
    private TableColumn<Evenement, Integer> TARIF;
    @FXML
    private TableColumn<Evenement, Integer> TICKETDISPONIBLE;
    private TableColumn<Evenement, String> AFFICHEIMAGE;
    @FXML
    private TableColumn<Evenement, Integer> CATEGORIE;
    @FXML
    private WebView WebView;

    /**
     * Initializes the controller class.
     */
    CrudCategorie cat = new CrudCategorie();
    CrudEvenement CE = new CrudEvenement();
    Evenement E = null;
    List<Evenement> l;
    private int id_even = 0;
    @FXML
    private Button reset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*affichage des liste dn combobox*/
        List<String> c = cat.afficherCategorie();
        ObservableList Combo = FXCollections.observableArrayList(c);
        Categorie.getItems().clear();
        Categorie.setItems(Combo);

        UpdateList();
        /**
         * ***Affichage de la liste des even***
         */
        l = CE.afficherEvenement();
        ObservableList observableList = FXCollections.observableArrayList(l);
        eventList.setItems(observableList);

        eventList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement SelectedEvent = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());
                E = SelectedEvent;
                vider();
            }
        });
    }

    public void UpdateList() {

        EventID.setCellValueFactory(new PropertyValueFactory<>("Id_even"));
        TITRE.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        DESCRIPTION.setCellValueFactory(new PropertyValueFactory<>("Description"));
        DATEDEBUT.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
        DATEFIN.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        TARIF.setCellValueFactory(new PropertyValueFactory<>("Tarif"));
        LIEU.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        TICKETDISPONIBLE.setCellValueFactory(new PropertyValueFactory<>("Ticket_disponible"));
        CATEGORIE.setCellValueFactory(new PropertyValueFactory<>("Id_cat"));

    }
    @FXML
    private void UplaodImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        FilePath.setText(file.getAbsolutePath());

        Image img = new Image("file:" + file.getAbsolutePath());
        AfficheIMG.imageProperty().set(img);

    }

    @FXML
    private void LieuChanged2(ActionEvent event) {
    }

    @FXML
    private void AjouterEven(ActionEvent event) throws SQLException {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmer l'ajout");
        confirmation.setHeaderText("Confirmer");
        confirmation.setContentText("Voulez-vous ajouter cet evenement ?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == ButtonType.OK) {

            if ("".equals(Titre.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Le titre est un champ obligatoire !");
                alert.showAndWait();
            } else if ("".equals(Description.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention !!!");
                alert.setContentText("La description est un champ obligatoire !");
                alert.showAndWait();
            } else if ("".equals(FilePath.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("il faut entrer une image !");
                alert.showAndWait();
            } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur Date");
                alert.setHeaderText("Attention!!!!");
                alert.setContentText("la date de début de l'évenement doit etre superieur à la date courante !");
                alert.showAndWait();
            } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay() < DateDebut.getValue().toEpochDay()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur Date");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Verifie la date du fin de l'evenement !");
                alert.showAndWait();

            } else if ("".equals(Tarif.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Verifie le tarif de l'evenemenemt !");
                alert.showAndWait();
            } else if ("".equals(Ticketdispo.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Verifie le nombre de tickets disponibles  !");
                alert.showAndWait();
            } else if ((Categorie.getSelectionModel().getSelectedItem()) == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("Il faut choisir une categorie de l'evenement  !");
                alert.showAndWait();
            } else if (!"".equals(Tarif.getText()) && (Integer.parseInt(Tarif.getText())) <= 0) {
                System.out.println("prix negatif");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie du tarif");
                alert.setHeaderText("Attention!!!!");
                alert.setContentText("le prix ne doit pas etre inférieur à 0  !");
                alert.showAndWait();
            } else if (!"".equals(Ticketdispo.getText()) && (Integer.parseInt(Ticketdispo.getText())) <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie du nombre de tickets disponibles");
                alert.setHeaderText("Attention!!!");
                alert.setContentText("le nombre de tickets disponibles ne doit pas etre inférieur à 0  !");
                alert.showAndWait();
            } else {

                Evenement E;

                String cc = Categorie.getValue().toString();
                System.err.println(cc);
                int id_cat = cat.findCategorieByName(cc);

                System.err.println(id_cat);
                E = new Evenement(Titre.getText(), id_cat, FilePath.getText(), Date.valueOf(DateDebut.getValue().toString()),
            Date.valueOf(DateFin.getValue().toString()), Lieu.getText(), Description.getText(), 
                        Integer.parseInt(Ticketdispo.getText()), Integer.parseInt(Tarif.getText()));
                System.out.println(Categorie.getValue());
                String nomcateg=cat.findNomCategorieById(E.getId_cat());

                CE.ajouterEvenement(E);
                JOptionPane.showMessageDialog(null, "evenement ajouté avec succée");
                l = CE.afficherEvenement();
               
           
                ObservableList observableList = FXCollections.observableArrayList(l);
                eventList.setItems(observableList);
            }
        } else {
            System.out.println("fxffh");
        }
        //System.err.println("*****" + eventList);

    }

    @FXML
    private void ModifierEven(ActionEvent event) throws SQLException, UnsupportedEncodingException, MalformedURLException {

        if (eventList.getSelectionModel().getSelectedItem() != null)//getselected item retourne valeurdu item selectionné
        {
            E = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmer la modification");
            confirmation.setHeaderText("Confirmer");
            confirmation.setContentText("Vous-vous modifier l'evenement " + E.getTitre() + " ?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                if ("Enregistrer".equals(EditEvent.getText())) {

                    if ("".equals(Titre.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Le titre est un champ obligatoire !");
                        alert.showAndWait();
                    } else if ("".equals(Description.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("La description est un champ obligatoire !");
                        alert.showAndWait();
                    } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur Date");
                        alert.setHeaderText("Attention");
                        alert.setContentText("la date d'évenement doit etre superieur à la date courant !");
                        alert.showAndWait();
                    } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay() < DateDebut.getValue().toEpochDay()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Verifie la date du fin de l'evenement !");
                        alert.showAndWait();

                    } else if ("".equals(Tarif.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Verifie le tarif de l'evenemenemt !");
                        alert.showAndWait();
                    } else if ("".equals(FilePath.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Verifie path de l'image !");
                        alert.showAndWait();
                    } else if ("".equals(Ticketdispo.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Verifie le nombre de tickets disponibles!");
                        alert.showAndWait();
                    } else if ((Categorie.getSelectionModel().getSelectedIndex()) == -1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setHeaderText("Attention");
                        alert.setContentText("Verifie chois du categorie  !");
                        alert.showAndWait();
                    } else {
                        String cc = Categorie.getValue().toString();
                        int id_cat = cat.findCategorieByName(cc);
                        E = new Evenement(Titre.getText(), id_cat, FilePath.getText(), Date.valueOf(DateDebut.getValue().toString()), Date.valueOf(DateFin.getValue().toString()), Lieu.getText(), Description.getText(), Integer.parseInt(Ticketdispo.getText()), Integer.parseInt(Tarif.getText()));
                        System.out.println("nouveau Titre  : " + Titre.getText());
                        E.setId_even(id_even);
                        System.out.println("id even à modifier" + id_even);
                        CE.modifierEvenement(E, E.getId_even());
                        System.out.println("evenement modifié");

                        l = CE.afficherEvenement();
                        ObservableList observableList = FXCollections.observableArrayList(l);
                        eventList.setItems(observableList);
                        vider();
                        EditEvent.setText("Modifier");
                    }

                }

            } else {
                vider();
            }

            if ("Modifier".equals(EditEvent.getText())) {
                afficheEvenAModifier();
                AddEvent.setVisible(false);
                EditEvent.setText("Enregistrer");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez selectionner un evenement ");

        }

    }

    public void afficheEvenAModifier() {
        id_even = E.getId_even();
        Titre.setText(E.getTitre());
        Description.setText(E.getDescription());
        FilePath.setText(E.getNom_image());
        Image img = new Image("file:" + FilePath.getText());
        AfficheIMG.imageProperty().set(img);
        LocalDate ldd = E.getDate_debut().toLocalDate();
        DateDebut.setValue(ldd);
        LocalDate ldf = E.getDate_fin().toLocalDate();
        DateFin.setValue(ldf);

        Lieu.setText(E.getLieu());
        String tarif1 = String.valueOf(E.getTarif());
        Tarif.setText(tarif1);
        String ticket = String.valueOf(E.getTicket_disponible());
        Ticketdispo.setText(ticket);
//        Categorie.getSelectionModel().select(E.getCategorie().getNomCategorie());
        Categorie.getSelectionModel().select(E.getId_cat());

    }

    @FXML
    private void DeletEven(ActionEvent event) throws SQLException {
        if (eventList.getSelectionModel().getSelectedItem() != null) {
            E = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Confirmer");
            alert.setContentText("Vous-etes sur de supprimer l'evenement " + E.getTitre() + " ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CE.DeleteEvenement(E.getId_even());
            }

            l = CE.afficherEvenement();
            ObservableList observableList = FXCollections.observableArrayList(l);
            eventList.setItems(observableList);
            vider();
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez Selectionnez un evenement à supprimer");
        }

    }

    @FXML
    public void vider() {
        E = null;
        Titre.setText("");
        Description.setText("");
        FilePath.setText("");
        DateDebut.setValue(null);
        DateFin.setValue(null);
        Lieu.setText("");
        Tarif.setText("");
        Ticketdispo.setText("");
        Categorie.setValue("");
        Categorie.setPromptText("Categorie");
        AfficheIMG.imageProperty().set(null);
        EditEvent.setText("Modifier");
        AddEvent.setVisible(true);
    }

    @FXML
    private void Miseajour(ActionEvent event) throws SQLException {

        vider();
        eventList.setItems(CE.displayAllEvenement());
        E = null;
    }

    @FXML
    private void Search(ActionEvent event) {

        try {
            eventList.setItems(CE.Search(Chercher.getText()));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void Filtre(ActionEvent event) {

        int id_categ = 0;
        if (Rando.isSelected()) {
            id_categ = 4;
        }
        if (Cultiver.isSelected()) {
            id_categ = 1;
        }
        if (Distraire.isSelected()) {
            id_categ = 2;
        }
        if (Cinema.isSelected()) {
            id_categ = 3;
        }
        /*
        if (autre.isSelected() && Cinema.isSelected()) {
            id_categ = 5;
            id_categ = 3;
        }
         if (Rando.isSelected()&& Cultiver.isSelected()) {
            id_categ = 4;
                        id_categ = 1;

        }
         if (Rando.isSelected()&& Distraire.isSelected()) {
            id_categ = 4;
                        id_categ = 2;

        }*/

        try {
            eventList.setItems(CE.displayFiltreEvenement(id_categ));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void SlectTable(MouseEvent event) {
    }

}
