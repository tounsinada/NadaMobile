/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Reservation;
import edu.AllForKids.services.CrudReservation;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationCurrentUserController implements Initializable {

   
    /**
     * Initializes the controller class.
     */
    
        private ObservableList<Reservation> data;
LoginController Log;
    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private Button BtnEvenent;
    @FXML
    private ScrollPane SrollPaneMain;
    @FXML
    private TableView<Reservation> ListeReservation1;
    @FXML
    private TableColumn<Reservation, Integer> IDReservation1;
    @FXML
    private TableColumn<Reservation, Integer> IDEvenement1;
    private TableColumn<Reservation, Integer> IDClient1;
    @FXML
    private TableColumn<Reservation, Integer> DateReserv1;
    @FXML
    private TableColumn<Reservation, Integer> NBTicket1;
    @FXML
    private AnchorPane anchopaneMain;
    @FXML
    private Label modifierProfil;
    @FXML
    private Label deconnection;
    List<Reservation> listReserv;
    int total=0;
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
          System.out.println("yyyyyyyyyyyyyyyy"+i.getNomEven());
       
       
        }}
    
    private void loadDataFromDatabase() throws SQLException {
        CrudReservation rss = new CrudReservation();
        List <Reservation> rvs = rss.displayReserationParUser(Log.CurrentUser.getId());
        for(Reservation rv : rvs )
      {
          data.add(rv);
      }
          
        ListeReservation1.setItems(data);
    }
     
     public void setsCllTable() { 
         
        IDReservation1.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        IDEvenement1.setCellValueFactory(new PropertyValueFactory<>("id_even"));
        NBTicket1.setCellValueFactory(new PropertyValueFactory<>("nbre_ticket"));
        //IDClient1.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    }


    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) {
        
        
            try {
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationCurrentUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    
    
    
    
    
    
    
    
    @FXML
    private void GetEvenement(ActionEvent event) {
        
       try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    @FXML
    private void Logout(MouseEvent event) {
    }

    @FXML
    private void ModifierProfil(MouseEvent event) {
    }
    
}
