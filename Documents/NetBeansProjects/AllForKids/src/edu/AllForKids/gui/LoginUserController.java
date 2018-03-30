/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginUserController implements Initializable {

    @FXML
    private AnchorPane Pane;
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
    Stage primaryStage;

     public LoginUserController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnconnexion(ActionEvent event) {
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Registrer.fxml"));
        Scene scene = new Scene(root);
        Stage primarysStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
}
