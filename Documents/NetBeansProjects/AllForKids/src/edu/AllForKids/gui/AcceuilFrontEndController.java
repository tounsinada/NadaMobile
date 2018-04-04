/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AcceuilFrontEndController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private Button BtnAcceuil;
    @FXML
    private Button BtnStore;
    @FXML
    private Button BtnBabySitter;
    @FXML
    private Button BtnEvenent;
    @FXML
    private Button BtnEspace;
    @FXML
    private Button BtnPediatre;
    @FXML
    private ScrollPane SrollPaneMain;

    /**
     * Initializes the controller class.
     */
    Stage primaryStage;
    AnchorPane GestionUser, GestionStore, GestionEvenement, GestionEspace, GestionBabySitter, GestionPediatre, AcceuilClient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            GestionEvenement = FXMLLoader.load(getClass().getResource("EvenementFrontEnd.fxml"));
            //GestionEspace = FXMLLoader.load(getClass().getResource(".fxml"));
            // GestionBabySitter = FXMLLoader.load(getClass().getResource(".fxml"));
            //GestionPediatre = FXMLLoader.load(getClass().getResource(".fxml"));
            // GestionStore = FXMLLoader.load(getClass().getResource(".fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            Parent root = loader.load();

          LoginController  controllerLog = loader.getController();
          // System.out.println("hhhhh"+controllerLog.getCurrentUser().getUsername());
            //UserName.setText(controllerLog.CurrentUser.getUsername());
            
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //System.out.println(controllerLog.getCurrentUser().getUsername());
                    // UserName.setText(controllerLog.getCurrentUser().getUsername());
                    //UserImage.setImage(new Image("file:" + controllerLog.getCurrentUser().getNom_image()));

                }
            }, 0, 1000);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilFrontEndController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) throws IOException {
         primaryStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
         
    }

    private void setNode(Node node) {

        SrollPaneMain.setContent(node);

        
    }

    @FXML
    private void GetAcceuil(ActionEvent event) {

        setNode(AcceuilClient);
        BtnAcceuil.setStyle("-fx-background-color:  #1F3A93");
        BtnAcceuil.setTextFill(Color.web("#FBFCFC"));
        //
        BtnStore.setStyle("-fx-background-color:  #FFFFFF");
        BtnStore.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvenent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvenent.setTextFill(Color.web("#2471A3"));
        BtnBabySitter.setStyle("-fx-background-color:  #FFFFFF");
        BtnBabySitter.setTextFill(Color.web("#2471A3"));
        BtnPediatre.setStyle("-fx-background-color:  #FFFFFF");
        BtnPediatre.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetStore(ActionEvent event) {
        setNode( GestionStore);
        BtnStore.setStyle("-fx-background-color:  #2574A9");
        BtnStore.setTextFill(Color.web("#FBFCFC"));
        //
        BtnAcceuil.setStyle("-fx-background-color:  #FFFFFF");
        BtnAcceuil.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvenent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvenent.setTextFill(Color.web("#2471A3"));
        BtnBabySitter.setStyle("-fx-background-color:  #FFFFFF");
        BtnBabySitter.setTextFill(Color.web("#2471A3"));
        BtnPediatre.setStyle("-fx-background-color:  #FFFFFF");
        BtnPediatre.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetBabySitter(ActionEvent event) {
        setNode(GestionBabySitter);
        BtnBabySitter.setStyle("-fx-background-color:  #086A87");
        BtnBabySitter.setTextFill(Color.web("#FBFCFC"));
        //
        BtnStore.setStyle("-fx-background-color:  #FFFFFF");
        BtnStore.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvenent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvenent.setTextFill(Color.web("#2471A3"));
        BtnAcceuil.setStyle("-fx-background-color:  #FFFFFF");
        BtnAcceuil.setTextFill(Color.web("#2471A3"));
        BtnPediatre.setStyle("-fx-background-color:  #FFFFFF");
        BtnPediatre.setTextFill(Color.web("#2471A3"));

    }

    @FXML
    private void GetEvenement(ActionEvent event) {

        setNode(GestionEvenement);
        BtnEvenent.setStyle("-fx-background-color:  #2574A9");
        BtnEvenent.setTextFill(Color.web("#FBFCFC"));
        //
        BtnStore.setStyle("-fx-background-color:  #FFFFFF");
        BtnStore.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnAcceuil.setStyle("-fx-background-color:  #FFFFFF");
        BtnAcceuil.setTextFill(Color.web("#2471A3"));
        BtnBabySitter.setStyle("-fx-background-color:  #FFFFFF");
        BtnBabySitter.setTextFill(Color.web("#2471A3"));
        BtnPediatre.setStyle("-fx-background-color:  #FFFFFF");
        BtnPediatre.setTextFill(Color.web("#2471A3"));
    }

    @FXML
    private void GetEspace(ActionEvent event) {
        setNode(GestionEspace);
        BtnEspace.setStyle("-fx-background-color:  #1F3A93");
        BtnEspace.setTextFill(Color.web("#FBFCFC"));
        //
        BtnStore.setStyle("-fx-background-color:  #FFFFFF");
        BtnStore.setTextFill(Color.web("#2471A3"));
        BtnAcceuil.setStyle("-fx-background-color:  #FFFFFF");
        BtnAcceuil.setTextFill(Color.web("#2471A3"));
        BtnEvenent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvenent.setTextFill(Color.web("#2471A3"));
        BtnBabySitter.setStyle("-fx-background-color:  #FFFFFF");
        BtnBabySitter.setTextFill(Color.web("#2471A3"));
        BtnPediatre.setStyle("-fx-background-color:  #FFFFFF");
        BtnPediatre.setTextFill(Color.web("#2471A3"));

    }

    @FXML
    private void GetPediatre(ActionEvent event) {
        setNode(GestionPediatre);
        BtnPediatre.setStyle("-fx-background-color:  #086A87");
        BtnPediatre.setTextFill(Color.web("#FBFCFC"));
        //
        BtnStore.setStyle("-fx-background-color:  #FFFFFF");
        BtnStore.setTextFill(Color.web("#2471A3"));
        BtnEspace.setStyle("-fx-background-color:  #FFFFFF");
        BtnEspace.setTextFill(Color.web("#2471A3"));
        BtnEvenent.setStyle("-fx-background-color:  #FFFFFF");
        BtnEvenent.setTextFill(Color.web("#2471A3"));
        BtnBabySitter.setStyle("-fx-background-color:  #FFFFFF");
        BtnBabySitter.setTextFill(Color.web("#2471A3"));
        BtnAcceuil.setStyle("-fx-background-color:  #FFFFFF");
        BtnAcceuil.setTextFill(Color.web("#2471A3"));

    }

    public void setLabelUserName(String username, int Id) {
        this.UserName.setText(username);
    }

}
