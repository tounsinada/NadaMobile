/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class EventMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        
            //Parent root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
            //root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
            //root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
