/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Reservation;
import edu.AllForKids.services.CrudReservation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationSeulEvenController implements Initializable {

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
    private Button ListeEven;
    @FXML
    private TableView<Reservation> ListeReservation;
    @FXML
    private TableColumn<?, ?> IDReservation;
    @FXML
    private TableColumn<?, ?> IDClient;
    @FXML
    private TableColumn<?, ?> NBTicket;

    /**
     * Initializes the controller class.
     */
    
            private ObservableList<Reservation> data;
LoginController Log;
     String path1 = "";
    File file1;
    File source;
    File dest;
    Stage primarystage;
    private FileChooser fileChooser = new FileChooser();
        List<Reservation> listReserv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
               
            setsCllTable();
            data = FXCollections.observableArrayList();
            loadDataFromDatabase();
            
            CrudReservation rs = new CrudReservation();
            
           listReserv = rs.displayReserationParUser(Log.CurrentUser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(Reservation i : listReserv )
      {
          i.getId_even();
                 System.out.println("////id even dans reservcuruser////"+i.getId_even());

       
        }}
    
    private void loadDataFromDatabase() throws SQLException {
        CrudReservation rss = new CrudReservation();
        List <Reservation> rvs = rss.displayReserationParUser(Log.CurrentUser.getId());
        for(Reservation rv : rvs )
      {
          data.add(rv);
      }
          
        ListeReservation.setItems(data);
    }
     
     public void setsCllTable() { 
         
        IDReservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        //IDEvenemen.setCellValueFactory(new PropertyValueFactory<>("id_even"));
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
        }
    }
    
}
