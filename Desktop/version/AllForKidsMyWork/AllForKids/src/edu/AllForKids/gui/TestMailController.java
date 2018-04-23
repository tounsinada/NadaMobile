/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TestMailController implements Initializable {

    @FXML
    private Label mailLabel;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mailLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> {
             MailSend m=new MailSend();
                        String subject="Password recovery";
                        int randomPIN = (int)(Math.random()*9000)+1000;
                        String pin = String.valueOf(randomPIN);
                        String message="your token is "+pin+" its valid only for 1 hour !!";
                        System.out.println(randomPIN);
                         //s.addToken(randomPIN,);
             m.sendMail("nada.tounsi@esprit.tn","nasnes21@gmail.com",subject,message);
                        TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("tokin input");
                            dialog.setHeaderText("please enter the token you get from email");
                            dialog.setContentText("Token:");

                            // Traditional way to get the response value.
                            Optional<String> result = dialog.showAndWait();
                            System.out.println("result :"+result.get()+", pin :"+pin.toString());
                           
                           
                
         });
            
        // TODO
    }    
    
}
