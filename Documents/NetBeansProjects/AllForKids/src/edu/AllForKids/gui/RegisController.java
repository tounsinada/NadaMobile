/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class RegisController implements Initializable {

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
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private ImageView img;
    @FXML
    private PasswordField RepeterPass;
    @FXML
    private Button Reset;
    private final Stage primarystage;
    @FXML
    private Button Choose;
    @FXML
    private ToggleGroup gender;

    public RegisController(Stage primarystage) {
        this.primarystage = primarystage;
    }

    /**
     * Initializes the controller class.
     */
    int file = 0;
    File pDir;
    File pfile;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnconnexion(ActionEvent event) {
    }

    @FXML
    private void Register(ActionEvent event) {

        if (!Nom.getText().equals("")) {
            Nom.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Nom.setPromptText("Inserez votre nom ici !!!");
        }
        if (!tfmail.getText().equals("")) {
            tfmail.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            tfmail.setPromptText("Inserez votre mail ici !!!");
        }
        if (!Pass.getText().equals("")) {
            Pass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            Pass.setPromptText("Inserez votre password ici !!!");
        }
        if (!RepeterPass.getText().equals("")) {
            RepeterPass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            RepeterPass.setPromptText("Inserez votre password ici !!!");
        }
         /*if (!RepeterPass.getText().equals(Pass.getText())) {
            RepeterPass.setStyle("-fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ;");
            RepeterPass.setPromptText("Inserez votre password est erroné!!!");
        }*/
       
        if (Nom.equals("")  || tfmail.equals("") || Pass.equals("") || RepeterPass.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez remplir les champs manquants");
            a.showAndWait();
            if (Nom.getText().equals("")) {
                Nom.setStyle("-fx-border-color : red ;");
            }
            
            if (tfmail.getText().equals("")) {
                tfmail.setStyle("-fx-border-color : red ;");
            }
            if (Pass.getText().equals("")) {
                Pass.setStyle("-fx-border-color : red ;");
            }
            if (RepeterPass.getText().equals("")) {
                RepeterPass.setStyle("-fx-border-color : red ;");
            }

        } /*else {
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText().toLowerCase();
            CrudUser crudutilisateur = new CrudUser();
            if (crudutilisateur.FindByEmail(tfmail.getText()) == null) {
                User u = new User(0, TxtNom.getText(), TxtPrenom.getText(), java.sql.Date.valueOf(DateNaissance.getValue()), toogleGroupValue, TxtEmail.getText(), TxtPass.getText());
                try {
                    s.InsertMembre(m);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("adresse email non reconuu");
                    a.showAndWait();
                }
            } 
        */else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("adresse email existe deja connectez vous");
                a.showAndWait();
            }
            //Signin(null);
        }

    

    @FXML
    private void Choisir(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image..");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            //ch.setText("image sélectionnée");
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            img.setImage(image);
        }
    }

    @FXML
    private void Reset(ActionEvent event) {
        femme.setSelected(false);
        homme.setSelected(true);
        tfmail.clear();
        tfpass.clear();
        Nom.clear();
        RepeterPass.clear();

    }

}
