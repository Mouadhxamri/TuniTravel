/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import entities.ReservationVol;
import entities.Vol;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ReservationVolService;
import services.VolService;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ReservationVolController implements Initializable {

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
    @FXML
    private TextField resdep;
    @FXML
    private TextField resdest;
    @FXML
    private DatePicker datedres;
    @FXML
    private DatePicker daterres;
    @FXML
    private TextField nbrpsgres;
    @FXML
    private Button resbutton;
    @FXML
    private TextField recherchervol;
    private Vol cc=null;
    private ReservationVol vv=null;
    @FXML
    private ComboBox<String> classeres;
    
    String fc="First Class";
    String bc="Business Class";
    String ec="Economy Class";
    @FXML
    private Label idv;
    @FXML
    private Button bsupp;
    @FXML
    private TableView<ReservationVol> table2;
    @FXML
    private TableColumn<ReservationVol, ?> classetab;
    @FXML
    private TableColumn<ReservationVol, ?> nbrpsgtab;
    @FXML
    private TableColumn<ReservationVol, ?> prixttab;
    @FXML
    private Label idrv;
    @FXML
    private DatePicker datedresV;
    @FXML
    private DatePicker daterresV;
    @FXML
    private TextField nbrpsgresV;
    @FXML
    private ComboBox<String> classeresV;
    @FXML
    private TableColumn<?, ?> datedrvtab;
    @FXML
    private TableColumn<?, ?> daterrvtab;
    @FXML
    private Button modifbtn;
    @FXML
    private Label labelT;
    double prixV ;
    double plfc ;
    double prixT = prixV + plfc ;;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                affichageVol();
                affichageRVol();
                classeres.getItems().addAll(fc,bc,ec);
                classeresV.getItems().addAll(fc,bc,ec);
      
              
         datedres.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        daterres.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
LocalDate datex = datedres.getValue();

            setDisable(empty || date.compareTo(datex) < 0 );
        }
    });
        table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Vol)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
            resdep.setText(cc.getDepart());
            String Pstr = Double.toString(cc.getPrix_v());
             labelT.setText(Pstr);
           
                resdest.setText(cc.getDestination());
               
             String ref=Integer.toString(cc.getId_vol()); 
           idv.setText(ref);
               // java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       //java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       LocalDate d1=cc.getDate_d().toLocalDate();
                LocalDate d2=cc.getDate_r().toLocalDate();
                datedres.setValue(d1);
                daterres.setValue(d2);
                double prixV = Double.parseDouble(Pstr) ; 
                     classeres.valueProperty().addListener(new ChangeListener<String>()
        {
            
                          
                
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                
                if (newValue==fc) 
             { plfc = 700 ; 
             prixT = prixV + plfc ;
             }else if (newValue==bc)
                 { plfc = 400 ; 
                 prixT = prixV + plfc ;
             }else if (newValue==ec)
                 {
                 prixT = prixV  ;
             }
            
                
                
            }

        }  );   
                     
            }
            
          });
        
        table2.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                vv = (ReservationVol)table2.getSelectionModel().getSelectedItem();
                System.out.println(vv);
                
            classeresV.setValue(vv.getClasse());
                String psg = Integer.toString(vv.getNbrpsg());
                 nbrpsgresV.setText(psg);
               
             String reff=Integer.toString(vv.getId_resV()); 
           idrv.setText(reff);
               // java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       //java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       LocalDate d3=vv.getDate_debut().toLocalDate();
                LocalDate d4=vv.getDate_fin().toLocalDate();
                datedresV.setValue(d3);
                daterresV.setValue(d4);
                                     classeresV.valueProperty().addListener(new ChangeListener<String>()
        {
            
                          
                
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                
                if (newValue==fc) 
             { plfc = 700 ; 
             prixT = prixV + plfc ;
             }else if (newValue==bc)
                 { plfc = 400 ; 
                 prixT = prixV + plfc ;
             }else if (newValue==ec)
                 {
                 prixT = prixV  ;
             }
            
                
                
            }

        }  ); 
               
            }
          }
        );

      

        
    }    

    

    @FXML
    private void reserverV(ActionEvent event) {
       
        String depart = resdep.getText();
        String destination = resdest.getText();
        java.sql.Date date_d = java.sql.Date.valueOf(datedres.getValue());
        java.sql.Date date_r = java.sql.Date.valueOf(daterres.getValue());
        String nbrpsg = nbrpsgres.getText();
        int x1= Integer.parseInt(nbrpsg);
        String classe = classeres.getValue();
        
        String idvol = idv.getText();
        
        int x= Integer.parseInt(idvol);
         ReservationVolService rs = new ReservationVolService();
         ReservationVol r= new ReservationVol(6,99,date_d,date_r,prixT,x,x1,"Tunisair",classe);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout Réservation Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
         rs.ajouterReservationVol(r);
         affichageRVol();
        
        
        FXMLLoader loader = new FXMLLoader (getClass().getResource("ReservationVol.fxml"));
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
     private void recherchervol(ActionEvent event) throws SQLException {
     
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

    @FXML
    private void editflight(ActionEvent event) {

        java.sql.Date date_d = java.sql.Date.valueOf(datedresV.getValue());
        java.sql.Date date_r = java.sql.Date.valueOf(daterresV.getValue());
        String nbrpsg = nbrpsgresV.getText();
        int x1= Integer.parseInt(nbrpsg);
        String classe = classeresV.getValue();
        String idvol = idv.getText();
        
        int x= Integer.parseInt(idvol);
        String idR = idrv.getText();
        
        int x2= Integer.parseInt(idR);
         ReservationVolService rs = new ReservationVolService();
         ReservationVol r= new ReservationVol(99,date_d,date_r,prixT,x,x1,"Tunisair",classe);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modification Réservation Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
         rs.modifierReservationVol(r,x2);
         affichageRVol();
    }

    @FXML
    private void delflight(ActionEvent event) {
       
        
        String idR = idrv.getText();
        
        int x2= Integer.parseInt(idR);
         ReservationVolService rs = new ReservationVolService();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression Réservation Vol");

		// alert.setHeaderText("Results:");
		alert.setContentText("Voulez vous continuer ?");

		alert.showAndWait();
         rs.supprimerReservationVol(x2);
         affichageRVol();
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

    @FXML
    private void affichageRVol() {
         try {
       ReservationVolService sp = new ReservationVolService();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table2.setItems(et);
       
       classetab.setCellValueFactory(new PropertyValueFactory<>("classe"));
       nbrpsgtab.setCellValueFactory(new PropertyValueFactory<>("nbrpsg"));
       datedrvtab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       daterrvtab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       prixttab.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
       
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterVolController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     
    
}
