/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionUserController implements Initializable {

    @FXML
    private TextField TxtNom;
    private TextField TxtGender;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtRole;
    @FXML
    private VBox CoachLayout;
    @FXML
    private TableView<User> TablViewList;
    @FXML
    private TableColumn<?, ?> UserNameColumn;
    @FXML
    private TableColumn<?, ?> EmailColumn;
    @FXML
    private TableColumn<?, ?> EtatColumn;
    @FXML
    private TableColumn<?, ?> RoleColumn;
    @FXML
    private TextField TxtSearch;
    @FXML
    private CheckBox BabySitter;
    @FXML
    private CheckBox Parent;
    @FXML
    private CheckBox Pediatre;

    ObservableList<User> ListUser;

    CrudUser CU = new CrudUser();
    User U = null;
    List<User> l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UpdateList();

        try {
            l = CU.displayAllUsers();
            ObservableList observableList = FXCollections.observableArrayList(l);
            TablViewList.setItems(observableList);

            TablViewList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    User SelectedEvent = TablViewList.getItems().get(TablViewList.getSelectionModel().getSelectedIndex());
                    U = SelectedEvent;
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void UpdateList() {
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        EtatColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));

        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("roles"));
        TablViewList.setItems(ListUser);
    }

    @FXML
    private void BanUser(ActionEvent event) throws SQLException {
        if (TablViewList.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Selectionnez un utilisateur");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("vous etes sur d'activer cet utilisateur");
            a.showAndWait();

            CU.BanUser(TablViewList.getSelectionModel().getSelectedItem());
            ObservableList data1 = FXCollections.observableArrayList(CU.displayAllUsers());
            TablViewList.setItems(data1);
        }

    }

    @FXML
    private void UnbannUser(ActionEvent event) throws SQLException {
        if (TablViewList.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Selectionnez un utilisateur");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("vous etes sur de désactiver cet utilisateur");
            a.showAndWait();

            CU.UnBanUser(TablViewList.getSelectionModel().getSelectedItem());

            ObservableList data1 = FXCollections.observableArrayList(CU.displayAllUsers());

            TablViewList.setItems(data1);

        }

    }

    

    @FXML
    private void SelectData(MouseEvent event) {
        User user = TablViewList.getSelectionModel().getSelectedItem() ;
        TxtNom.setText(user.getUsername());
        TxtGender.setText(user.getSexe());
        TxtEmail.setText(user.getEmail());
        TxtRole.setText(user.getRoles());
        
    }

    @FXML
    private void Filtre(KeyEvent event) throws SQLException {

        if (TxtSearch.getText() == null) {
            initialiser();
        } else {
            List<User> chercherlist = null;
            chercherlist = CU.FindUserByNameOrLastname(TxtSearch.getText());
            ObservableList Obchercher = FXCollections.observableArrayList(chercherlist);

            TablViewList.setItems(Obchercher);
        }
    }

    private void initialiser() {
        TablViewList.setItems(ListUser);

    }

    private void init() {
        TablViewList.setItems(ListUser);

    }

    @FXML
    private void CheckFiltre(ActionEvent event) {

        try {
            String role = "''";

            if (Parent.isSelected()) {
                role += " OR roles LIKE 'Parent'";
            }
            if (BabySitter.isSelected()) {
                role += " OR roles  LIKE 'BabySitter'";
            }
            if (Pediatre.isSelected()) {
                role += " OR roles LIKE 'Pediatre'";
            }

            CrudUser CU = new CrudUser();
            ObservableList chercherparrole = FXCollections.observableArrayList(CU.displayUsersByRolels(role));

            TablViewList.setItems(chercherparrole);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
