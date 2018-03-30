/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.services.CrudEvenement;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEventController implements Initializable {

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
    private TextField Duree;
    @FXML
    private TextField Tarif;
    @FXML
    private TextField Ticketdispo;
    @FXML
    private ComboBox<?> Categorie;
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
    private TableView<?> eventList;
    @FXML
    private TableColumn<?, ?> EventID;
    @FXML
    private TableColumn<?, ?> UserID;
    @FXML
    private TableColumn<?, ?> TITRE;
    @FXML
    private TableColumn<?, ?> DESCRIPTION;
    @FXML
    private TableColumn<?, ?> DATEDEBUT;
    @FXML
    private TableColumn<?, ?> DATEFIN;
    @FXML
    private TableColumn<?, ?> LIEU;
    @FXML
    private TableColumn<?, ?> TARIF;
    @FXML
    private TableColumn<?, ?> TICKETDISPONIBLE;
    @FXML
    private TableColumn<?, ?> AFFICHE;
    @FXML
    private TableColumn<?, ?> CATEGORIE;
    @FXML
    private WebView WebView;
    /**
     * Initializes the controller class.
     */
        CrudEvenement CE = new CrudEvenement() ; 

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UplaodImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            FilePath.setText(file.getAbsolutePath());
            
            Image img = new Image("file:"+file.getAbsolutePath());
            AfficheIMG.imageProperty().set(img); 
    }

    @FXML
    private void LieuChanged2(ActionEvent event) {
    }

    @FXML
    private void AjouterEven(ActionEvent event) throws SQLException  {
        
           if ("".equals(Titre.getText()) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Le titre est un champ obligatoire !");
            alert.showAndWait();
        } else if ("".equals(Description.getText()) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("La description est un champ obligatoire !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Date");
            alert.setHeaderText("Attention");
            alert.setContentText("la date de début de l'évenement doit etre superieur à la date courante !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay()<DateDebut.getValue().toEpochDay()  ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la date du fin de l'evenement !");
            alert.showAndWait();
        }else if ( "".equals(Duree.getText()) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la durée de l'evenemenemt !");
            alert.showAndWait();  
        } else if ( "".equals(Tarif.getText()) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie le tarif de l'evenemenemt !");
            alert.showAndWait();  
        }
        else if ( "".equals(Ticketdispo.getText()) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie le nombre de tickets disponibles  !");
            alert.showAndWait();  
        }
        else if ((Categorie.getSelectionModel().getSelectedIndex())== 0 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Il faut choisir une categorie de l'evenement  !");
            alert.showAndWait();  
        }
        else {
          /*  Evenement E;
            E = new Evenement (Titre.getText(), (String) Categorie.getValue(),FilePath.getText(), Date.valueOf(DateDebut.getValue().toString()), Date.valueOf(DateFin.getValue().toString()), Lieu.getText(), Description.getText(), Integer.parseInt(Ticketdispo.getText()),Integer.parseInt(Tarif.getText()));
            System.out.println(E);
            // E.setID_UTILISATEUR(LoginGUIController.CurrentUser.getId());
            CE.ajouterEvenement(E);
            //eventList.setItems(CE.displayAllEvenement());
            // vider () ;*/
         }
    }

    @FXML
    private void ModifierEven(ActionEvent event) {
    }

    @FXML
    private void DeletEven(ActionEvent event) {
    }

    @FXML
    private void Miseajour(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Filtre(ActionEvent event) {
    }

    @FXML
    private void SlectTable(MouseEvent event) {
    }
    
}
