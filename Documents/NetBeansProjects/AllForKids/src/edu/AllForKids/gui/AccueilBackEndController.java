/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.gui.LoginController;
import edu.AllForKids.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AccueilBackEndController implements Initializable {

    @FXML
    private Label LblUserName;
    @FXML
    private ImageView UserPicture;
    @FXML
    private AnchorPane ContenuPane;
    @FXML
    private Label NomGestionnaire;
    
    
    AnchorPane GestionUser,GestionStore,GestionEvenement,GestionEspace,GestionBabySitter,GestionPediatre;
        Stage primaryStage ;
            public User CurrentUser ;


    /**
     * Initializes the controller class.
     */
    
        


   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {    
             //GestionUser = FXMLLoader.load(getClass().getResource(".fxml"));
           //  GestionStore = FXMLLoader.load(getClass().getResource(".fxml"));
             GestionEvenement = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            // GestionEspace = FXMLLoader.load(getClass().getResource(".fxml"));
            // GestionBabySitter = FXMLLoader.load(getClass().getResource(".fxml"));
            // GestionPediatre = FXMLLoader.load(getClass().getResource(".fxml"));
             // NomGestionnaire.setText("Gestionnaire des users");
              
             //Image image = new Image("file:"+ LoginController.CurrentUser.getNom_image());
              System.out.println(LoginController.CurrentUser.getNom_image());
             // UserPicture.setImage(image);
              
        } catch (IOException ex) {
             System.out.println("controller.AccueilController.initialize() "+ex);
        }    }    

    
    public void setLabelUserName(String username, int Id) {
        this.LblUserName.setText(username);
    }
    
    
    private void setNode(Node node) {
        ContenuPane.getChildren().clear();
        ContenuPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    @FXML
    private void GetGestionUser(ActionEvent event) {
        setNode(GestionUser);
        NomGestionnaire.setText("Gestionnaire des membres");
    }

    @FXML
    private void GetGestionStore(ActionEvent event) {
        setNode(GestionStore);
        NomGestionnaire.setText("Gestionnaire du Store");   
    }

    @FXML
    private void GetGestionEvenement(ActionEvent event) {
setNode(GestionEvenement);
        NomGestionnaire.setText("Gestionnaire des evenements");   
    }

    @FXML
    private void GetGestionEspace(ActionEvent event) {
        setNode(GestionEspace);
        NomGestionnaire.setText("Gestionnaire espace enfants");   
    }

    @FXML
    private void GetGestionBabySitter(ActionEvent event) {
        setNode(GestionBabySitter);
        NomGestionnaire.setText("Gestionnaire baby-sitter");   
    }

    @FXML
    private void GetGestionPediatre(ActionEvent event) {
        setNode(GestionPediatre);
        NomGestionnaire.setText("Gestionnaire des pediatres");   
    }

    @FXML
    private void EditProfile(MouseEvent event) {
    }

    @FXML
    private void disconnect(MouseEvent event) throws IOException {
        
       primaryStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
     private void initiliser(User u) {
        this.LblUserName.setText(u.getUsername());
        CurrentUser=u ;
        Image image = new Image(u.getNom_image()) ;
        UserPicture.setImage(image);
           
    }

    
    
}
