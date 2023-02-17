/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import entities.Vol;
import java.io.IOException;
import services.VolService;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hp
 */



public class AjouterVolController implements Initializable {

    @FXML
    private Button bajout;
    @FXML
    private TextField txtref;
    @FXML
    private TextField txtdep;
    @FXML
    private TextField txtdest;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker dater;
    @FXML
    private TextField txtprix;
    @FXML
    private TableView<Vol> table1;
    @FXML
    private TableColumn<Vol, String> reftab;
    @FXML
    private TableColumn<Vol, String> departtab;
    @FXML
    private TableColumn<Vol, String> destinationtab;
    @FXML
    private TableColumn<Vol, Date> datedtab;
    @FXML
    private TableColumn<Vol, Date> datertab;
    @FXML
    private TableColumn<Vol, String> prixtab;
private Vol cc=null;
    @FXML
    private Button bmodif;
    @FXML
    private Button bsupp;
    @FXML
    private Label idv1;
    @FXML
    private TextField recherchervol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageVol();
        dated.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        dater.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
LocalDate datex = dated.getValue();
            setDisable(empty || date.compareTo(datex) < 0 );
        }
    });

                table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Vol)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                txtref.setText(cc.getRef());
            txtdep.setText(cc.getDepart());
                txtdest.setText(cc.getDestination());
               String ref=Integer.toString(cc.getId_vol()); 
           idv1.setText(ref);
             String prx=Double.toString(cc.getPrix_v()); 
           txtprix.setText(prx);
               // java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       //java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       LocalDate d1=cc.getDate_d().toLocalDate();
                LocalDate d2=cc.getDate_r().toLocalDate();
                dated.setValue(d1);
                dater.setValue(d2);
               
            }
          });
    }    

    @FXML
    private void addflight(ActionEvent event) {
        String ref = txtref.getText();
        String depart = txtdep.getText();
        String destination = txtdest.getText();
        java.sql.Date date_d = java.sql.Date.valueOf(dated.getValue());
        java.sql.Date date_r = java.sql.Date.valueOf(dater.getValue());
        String prix_v = txtprix.getText();
        double prixv1 = Double.parseDouble(prix_v);
        Vol v1=new Vol(1, ref, depart, destination,date_d, date_r, prixv1);
        VolService vs = new VolService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
        vs.ajouterVol(v1);
        affichageVol();
        
        FXMLLoader loader = new FXMLLoader (getClass().getResource("AjouterVol.fxml"));
        try {
        Parent root = loader.load();
        
        }catch (IOException ex)
        { System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affichageVol() {
        try {
       VolService sp = new VolService();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table1.setItems(et);
       
       reftab.setCellValueFactory(new PropertyValueFactory<>("ref"));
       departtab.setCellValueFactory(new PropertyValueFactory<>("depart"));
       destinationtab.setCellValueFactory(new PropertyValueFactory<>("destination"));
       datedtab.setCellValueFactory(new PropertyValueFactory<>("date_d"));
       datertab.setCellValueFactory(new PropertyValueFactory<>("date_r"));
       prixtab.setCellValueFactory(new PropertyValueFactory<>("prix_v"));
       
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterVolController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void editflight(ActionEvent event) {
 String ref = txtref.getText();
        String depart = txtdep.getText();
        String destination = txtdest.getText();
        java.sql.Date date_d = java.sql.Date.valueOf(dated.getValue());
        java.sql.Date date_r = java.sql.Date.valueOf(dater.getValue());
        String prix_v = txtprix.getText();
        double prixv1 = Double.parseDouble(prix_v);
        String idvol = idv1.getText();
        
        int x= Integer.parseInt(idvol);
        Vol v1=new Vol(x, ref, depart, destination,date_d, date_r, prixv1);
        VolService vs = new VolService();
        
       
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modification Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
                vs.modifierVol(v1);
                 affichageVol();
    }

    @FXML
    private void delflight(ActionEvent event) {
       String ref = txtref.getText();
        String depart = txtdep.getText();
        String destination = txtdest.getText();
        java.sql.Date date_d = java.sql.Date.valueOf(dated.getValue());
        java.sql.Date date_r = java.sql.Date.valueOf(dater.getValue());
        String prix_v = txtprix.getText();
        double prixv1 = Double.parseDouble(prix_v);
        String idvol = idv1.getText();
        
        int x= Integer.parseInt(idvol);
        Vol v1=new Vol(x, ref, depart, destination,date_d, date_r, prixv1);
        VolService vs = new VolService();
        vs.supprimer(v1);
     
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
                affichageVol();
    }

    @FXML
    private void affichage2(ActionEvent event) throws SQLException {
         
        String search11 = recherchervol.getText();
    
        String req="select * from vol where depart = '"+search11+"' or destination = '"+search11+"'";
       List<Vol> list = new ArrayList<>();
       Connection mc;
    Statement ste;

    
        mc=MaConnexion.getInstance().getCnx();
    
     ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if(rs.next()){
              
               
               
               
                //ystem.out.println(v.getImage().toString());
                Vol p2=new Vol(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5),rs.getDate(6),rs.getDouble(7));
                //E131DHHJMNE code wifi whatelse?
               
                list.add(p2);
                VolService sp = new VolService();
      // List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(list);
       table1.setItems(et);
       
       
       
      reftab.setCellValueFactory(new PropertyValueFactory<>("ref"));
       departtab.setCellValueFactory(new PropertyValueFactory<>("depart"));
       destinationtab.setCellValueFactory(new PropertyValueFactory<>("destination"));
       datedtab.setCellValueFactory(new PropertyValueFactory<>("date_d"));
       datertab.setCellValueFactory(new PropertyValueFactory<>("date_r"));
       prixtab.setCellValueFactory(new PropertyValueFactory<>("prix_v"));
    }   
            else {
                affichageVol();
            }
       
    }
     
    }
    

