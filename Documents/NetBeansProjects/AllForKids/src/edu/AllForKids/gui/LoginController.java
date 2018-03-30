/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginController implements Initializable {

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
    private TextField Prenom;
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
    private AnchorPane Pane;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the controller class.
     */
    File imgfile;
    int file = 0;
    Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnconnexion(ActionEvent event) {
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {

        /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("Registrer.fxml")) ; 
       loader.setController(new RegistrerController(primaryStage));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        //Stage primaryStage= new Stage();
        //Node source =(Node) event.getSource();
        // Stage primarysStage= (Stage)source.getScene().getWindow();
        /*Parent root = FXMLLoader.load(getClass().getResource("Registrer.fxml"));
        Scene scene = new Scene(root);
        Stage primarysStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.show();*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/Registrer1.fxml"));
        Stage primarystage = null;
        loader.setController(new RegController(primarystage));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primarystage.setTitle("Connexion");
        primarystage.setScene(scene);
        primarystage.show();

    }

    @FXML
    private void Choisir(MouseEvent event) throws MalformedURLException {
        /* FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image..");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        imgfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
 /*if (imgfile != null) {
            file = 1;
            //ch.setText("image sélectionnée");
            Image image = new Image(imgfile.toURI().toURL().toExternalForm());
            img.setImage(image);
            
        }
         */
    }

    @FXML
    private void Registrer(MouseEvent event) throws SQLException, IOException {

        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.close();
        // Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registrer.fxml")));
        // stage.setScene(scene);
        // stage.show();
        //Stage primaryStage= new Stage();
        // Parent root = FXMLLoader.load(getClass().getResource("Registrer.fxml"));
        //Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        // primaryStage.show();
        //   AnchorPane p= FXMLLoader.load(getClass().getResource("/gui/Registrer.fxml"));
        // Pane.getChildren().setAll(p);
        /*FXMLLoader loader= new FXMLLoader(getClass().getResource("Registrer.fxml"));
            Parent root = loader.load();
            RegistrerController registerController= loader.getController();*/
        
        
        Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Reg.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        
        
    }

}
