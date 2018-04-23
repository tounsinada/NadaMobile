/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.Reservation;
import edu.AllForKids.services.CrudReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField Lieu;
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
    private TableColumn<Reservation, Integer> IDReservation;
    @FXML
    private TableColumn<Evenement, String> IDEvenement;
    @FXML
    private TableColumn<Reservation, Integer> NBTicket;
    @FXML
    private Button ListeEven;
    @FXML
    private TableView<Reservation> ListeReservation;
    
    /**
     * Initializes the controller class.
     */
   
    private ObservableList<Reservation> data;
    @FXML
    private TableColumn<Reservation, Integer> IDClient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            setsCllTable();
            data = FXCollections.observableArrayList();
            loadDataFromDatabase();
            
            
            
            CrudReservation rs = new CrudReservation();
            
            List<Reservation> list = rs.afficherReservationAvecNomEvenement();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDataFromDatabase() throws SQLException {
        CrudReservation rss = new CrudReservation();
        List <Reservation> rvs = rss.afficherReservationAvecNomEvenement();
        for(Reservation rv : rvs )
      {
          data.add(rv);
      }
          
        ListeReservation.setItems(data);
    }
     
     public void setsCllTable() { 
         
        IDReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        IDEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEven"));
        NBTicket.setCellValueFactory(new PropertyValueFactory<>("nbre_ticket"));
        IDClient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
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
    private void AfficherEvenement(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
