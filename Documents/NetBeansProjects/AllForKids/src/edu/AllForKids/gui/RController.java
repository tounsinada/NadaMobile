/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class RController implements Initializable {

    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Button btnconnexion;
    @FXML
    private Text BtnForgetPassword;
    @FXML
    private Button btnsignup;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Pass;
    @FXML
    private ImageView img;
    @FXML
    private PasswordField RepeterPass;
    @FXML
    private RadioButton homme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femme;
    @FXML
    private Button Reset;
    @FXML
    private Button Choose;
    Stage primarystage;
    @FXML
    private ComboBox<String> Role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homme.setSelected(true);
        ObservableList Combo = FXCollections.observableArrayList("Parent", "BabySitter", "Pediatre", "Prestataire");
        Role.setItems(Combo);
        System.out.println("jjjjj");
    }

    @FXML
    private void btnconnexion(ActionEvent event) throws IOException {
        primarystage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primarystage.setScene(scene);
            primarystage.show();
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {

        if (!Nom.getText().equals("")) {
            Nom.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Nom.setPromptText("Inserez votre nom ici !!!");
        }
        if (!Email.getText().equals("")) {
            Email.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Email.setPromptText("Inserez votre mail ici !!!");
        }

        if (!Pass.getText().equals("")) {
            Pass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Pass.setPromptText("Inserez votre password ici !!!");
        }
        if (!RepeterPass.getText().equals("")) {
            RepeterPass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            RepeterPass.setPromptText("Repeter votre password ici !!!");

        }

        if (Nom.getText().equals("") || Email.getText().equals("") || Pass.getText().equals("")
                || RepeterPass.getText().equals("") || Role.getSelectionModel().getSelectedIndex() == -1) {

            // (img.getProperties().isEmpty())
            System.out.println("1");

            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez remplir les champs manquants");
            a.showAndWait();
            if (Nom.getText().equals("")) {
                Nom.setStyle("-fx-border-color : red ;");
            }

            if (Role.getSelectionModel().getSelectedIndex() == -1) {
                Role.setStyle("-fx-border-color : red ;");
            }
            if (Email.getText().equals("")) {
                Email.setStyle("-fx-border-color : red ;");
            }
            if (Pass.getText().equals("")) {
                Pass.setStyle("-fx-border-color : red ;");
            }
            if (RepeterPass.getText().equals("")) {
                RepeterPass.setStyle("-fx-border-color : red ;");
            }

        } else {
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUser crudutilisateur = new CrudUser();
            if (crudutilisateur.FindByEmail(Email.getText()) == null) {
                User u = new User(0, Nom.getText(), Email.getText(), Pass.getText(),
                        Role.getSelectionModel().getSelectedItem(), toogleGroupValue);

                if (!RepeterPass.getText().equals(Pass.getText())) {

                    do {
                        RepeterPass.setStyle("-fx-border-color : red ;");
                        JOptionPane.showMessageDialog(null, " le password répété est erroné!!!");
                    } while (RepeterPass.getText().equals(Pass.getText()));

                } else {

                    crudutilisateur.ajouter_utilisateur(u);
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("nouveau compte est créé");
                    a.showAndWait();
                }

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("adresse email existe deja connectez vous");
                a.showAndWait();
            }
        }

    }

    @FXML
    private void Reset(ActionEvent event) {
        femme.setSelected(false);
        homme.setSelected(true);
        Email.clear();
        Pass.clear();
        Nom.clear();
        RepeterPass.clear();
        img.imageProperty().set(null);

    }

    @FXML
    private void Choisir(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        Image image = new Image("file:" + file.getAbsolutePath());
        img.imageProperty().set(image);
    }

}
