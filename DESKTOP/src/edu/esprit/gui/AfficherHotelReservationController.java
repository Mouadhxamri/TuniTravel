/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import edu.esprit.gui.PDFHotel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import tools.MaConnexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import edu.esprit.gui.SendMailController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import entities.Hotel;
import entities.ReservationHotel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.cell.PropertyValueFactory;
import services.HotelService;
import services.ReservationHotelService;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SortEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.mail.*;    
import javax.mail.internet.*;
import edu.esprit.gui.Mailer;
import java.io.FileNotFoundException;
import javafx.scene.control.DateCell;
import services.ReservationHotelService;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherHotelReservationController implements Initializable {

    @FXML
    private DatePicker datearriv;
    @FXML
    private DatePicker datefinres;
    @FXML
    private Spinner<Integer> nbrpersres;
    @FXML
    private Spinner<Integer> nbrchres;
    @FXML
    private Label prixvalue;
int pr=0,pr2=0;
    private MenuButton typechres;
    private MenuItem demipension;
    private MenuItem pensioncomplete;
    private Hotel cc=null;
    
  String logement;
    @FXML
    private ComboBox<String> logecombo;
    String dp="Demi Pension";
    String pc="Pension Complète";
    String ai="All Inclusive";
    String vide="";
    int nbrchaj;
    @FXML
    private Label prixtotalvalue;
    int pt=0,pt2=0;
    double finalprice;
    double finalpricech,finalpricepers;
    @FXML
    private TableView<ReservationHotel> table2;
    @FXML
    private TableColumn<Hotel,String> tablenom;
    @FXML
    private TableColumn<Hotel,String> tabledescription;
    @FXML
    private TableColumn<Hotel,String> tableimage;
    @FXML
    private TableColumn<Hotel,String> tableville;
    @FXML
    private Button confirmerbtn;
    @FXML
    private Label labelnomhotel;
    @FXML
    private Label labenvillehotel;
    @FXML
    private Label labelrefhotel;
    @FXML
    private ComboBox<String> logecombo1;
    @FXML
    private Label nnn;
    boolean aa=true;
    @FXML
    private ComboBox<String> logecombo2;
    @FXML
    private ComboBox<String> combotyres;
     private ReservationHotel rh=null;
      String chsingle="Chambre1 Single";
    String chdouble="Chambre1 Double";
    String chtriple="Chambre1 Triple";
    
    String chsingle1="Chambre2 Single";
    String chdouble1="Chambre2 Double";
    String chtriple1="Chambre2 Triple";
    
    String chsingle2="Chambre3 Single";
    String chdouble2="Chambre3 Double";
    String chtriple2="Chambre3 Triple";
    @FXML
    private ComboBox<String> combotyres1;
    @FXML
    private ComboBox<String> combotyres2;
    @FXML
    private TableView<Hotel> table1;
    @FXML
    private Tab datefinres2;
    @FXML
    private TableColumn<ReservationHotel, String> hoteltab2;
    @FXML
    private TableColumn<ReservationHotel, Date> datedebtab2;
    @FXML
    private TableColumn<ReservationHotel, Date> datefintab2;
    @FXML
    private TableColumn<ReservationHotel, String> nombreperstab2;
    @FXML
    private TableColumn<ReservationHotel, String> nombrechtab2;
    @FXML
    private TableColumn<ReservationHotel, String> typechtab2;
    @FXML
    private TableColumn<ReservationHotel, String> prixtab2;
    @FXML
    private DatePicker datearriv2;
    @FXML
    private ComboBox<String> combotyrestab2;
    @FXML
    private ComboBox<String> logecombotab2;
    @FXML
    private ComboBox<String> logecombo1tab2;
    @FXML
    private ComboBox<String> logecombo2tab2;
    @FXML
    private ComboBox<String> combotyres1tab2;
    @FXML
    private ComboBox<String> combotyres2tab2;
    @FXML
    private Spinner<Integer> nbrpersres2;
    @FXML
    private Spinner<Integer> nbrchres2;
    @FXML
    private DatePicker datefin2;
    @FXML
    private Label labelnomhotel2;
    @FXML
    private Button modifierresbtn;
    @FXML
    private Label labelrefhotel2;
    @FXML
    private Label prixvalue2;
    @FXML
    private Label prixtotalvalue2;
    @FXML
    private Label labelreshotel2;
    @FXML
    private Button annulerresbtn;
    @FXML
    private Button mailbtn;
    @FXML
    private TableColumn<?, ?> tableprix1;
    @FXML
    private Label pricetotal;
    //private TableColumn<?, ?> id_resHtab2;
    /**
     * Initializes the controller class.
     */
    double xxx;
    @FXML
    private Button pdf;
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
       affichageres();
      affichage2();
     
     // flash();
 /*   String refxx=  pricetotal.getText();
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            pricetotal.setText(refprp);*/
      datearriv.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    }); 
datefinres.setDayCellFactory(picker -> new DateCell(){
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
LocalDate dated = datearriv.getValue();
            setDisable(empty || date.compareTo(dated) <  0);
        }
    }); 
datearriv2.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    }); 
datefin2.setDayCellFactory(picker -> new DateCell(){
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
LocalDate dated = datearriv2.getValue();
            setDisable(empty || date.compareTo(dated) <  0);
        }
    }); 

        table2.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                rh = (ReservationHotel)table2.getSelectionModel().getSelectedItem();
                System.out.println(rh);
                int nb= rh.getId_hotel();
              
              String ref=Integer.toString(nb); 
           labelrefhotel2.setText(ref);
           double resprice= rh.getPrix_res();
            String respr=Double.toString(resprice);
           prixvalue2.setText(respr);
          int nb1= rh.getId_resH();
              String ref1=Integer.toString(nb1); 
           labelreshotel2.setText(ref1);
         //   labelnomhotel.setText(cc.getNom());
           //     descriptionfield.setText(cc.getDescription());
          //      labenvillehotel.setText(cc.getVille());
              //  importeimage.setImage(new Image("http://127.0.0.1/imagehotel/"+cc.getImage()));
             // labelnomhotel2
                LocalDate d1=rh.getDate_debut().toLocalDate();
                LocalDate d2=rh.getDate_fin().toLocalDate();
                datearriv2.setValue(d1);
                datefin2.setValue(d2);
                
               
            }
          });
       table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Hotel)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                int refres= cc.getRef();
               String ref=Integer.toString(refres); 
           labelrefhotel.setText(ref);
            labelnomhotel.setText(cc.getNom());
            double refprice= cc.getPrixhotel();
            String refpr=Double.toString(refprice);
            pricetotal.setText(refpr);
           //     descriptionfield.setText(cc.getDescription());
                labenvillehotel.setText(cc.getVille());
              //  importeimage.setImage(new Image("http://127.0.0.1/imagehotel/"+cc.getImage()));
               // java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       //java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
      
                
               
            }
          });
       
       

            
       
logecombo.getItems().addAll(dp,pc,ai);
logecombo1.getItems().addAll(dp,pc,ai);
logecombo2.getItems().addAll(dp,pc,ai);
combotyres.getItems().addAll(chsingle,chdouble,chtriple);
combotyres1.getItems().addAll(chsingle1,chdouble1,chtriple1);
combotyres2.getItems().addAll(chsingle2,chdouble2,chtriple2);
combotyres1.setVisible(false);
combotyres2.setVisible(false);
//typechres.getItems().addAll(choice1, choice2, choice3);
logecombotab2.getItems().addAll(dp,pc,ai);
logecombo1tab2.getItems().addAll(dp,pc,ai);
logecombo2tab2.getItems().addAll(dp,pc,ai);
combotyrestab2.getItems().addAll(chsingle,chdouble,chtriple);
combotyres1tab2.getItems().addAll(chsingle1,chdouble1,chtriple1);
combotyres2tab2.getItems().addAll(chsingle2,chdouble2,chtriple2);       
combotyres1tab2.setVisible(false);
combotyres2tab2.setVisible(false); 
logecombo1tab2.setVisible(false); 
logecombo2tab2.setVisible(false); 



SpinnerValueFactory<Integer> nbrchvalue2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,3);
nbrchvalue2.setValue(1);
    nbrchres2.setValueFactory(nbrchvalue2);   
// nbrchres.setValueFactory(nbrpersvalue); 
   SpinnerValueFactory<Integer> nbrpersvalue2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,3);
    nbrpersvalue2.setValue(1);
    nbrpersres2.setValueFactory(nbrpersvalue2);
   // prixvalue2.setText("0");
   // control();
  nbrpersres2.valueProperty().addListener(new ChangeListener<Integer>()
            {
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                   if(newValue==2) //2 personnes
                   {
                       if (nbrchres2.getValue()==3) 
                       {
                           finalpricech=0;
                           finalpricepers=0;
                           Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
                         //  System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo1tab2.setVisible(false);
               combotyres1tab2.setVisible(false);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
            
                       }
                       else if (nbrchres2.getValue()==2) // 2chambres 2 personnes
                       {
                           finalpricech=40;
                           finalpricepers=30;
                           logecombo1tab2.setVisible(true);
               combotyres1tab2.setVisible(true);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
                       }
                       else{ 
finalpricech=20;
                           finalpricepers=30;
// 1 chambre 2personnes
               /*        pr2=60;
                   pt2=pt2+pr2;
         String ref=Integer.toString(pr2);
        prixvalue2.setText(ref);
                    String ref2=Integer.toString(pt2);
        prixtotalvalue2.setText(ref2);*/
                       }
                       }
                   else if(newValue==3) //nbr personnes  3 personnes
          { 
                       if (nbrchres2.getValue()==3) //3 chambres 3 personnes
                       {
                           finalpricech=60;
                           finalpricepers=75;
              logecombo2tab2.setVisible(true);
               combotyres2tab2.setVisible(true);
                 logecombo1tab2.setVisible(true);
               combotyres1tab2.setVisible(true);
              String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
                       }
                       else{ 
finalpricech=40;
                           finalpricepers=50;
// 2 chambres 3 personnes
                 /*      pr2=80;
                       pt2=pt2+pr2;
         String ref=Integer.toString(pr2);
        prixvalue2.setText(ref);
         String ref2=Integer.toString(pt2);
        prixtotalvalue2.setText(ref2);*/
                       }
                           }        else   // 1personne
          {               
              finalpricech=0;
                           finalpricepers=0;
              logecombo2tab2.setVisible(false);
              logecombo1tab2.setVisible(false);
              combotyres1tab2.setVisible(false);
              combotyres2tab2.setVisible(false);
 String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
             /*          pr2=40;
          pt2=pt2+pr;
         String ref=Integer.toString(pr2);
        prixvalue2.setText(ref);
         String ref2=Integer.toString(pt2);
        prixtotalvalue2.setText(ref2);*/
                           } 
          }

       
                
                
            }
  
  
  );




        
 SpinnerValueFactory<Integer> nbrchvalue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,3);
nbrchvalue.setValue(1);
    nbrchres.setValueFactory(nbrchvalue);   
// nbrchres.setValueFactory(nbrpersvalue); 
   SpinnerValueFactory<Integer> nbrpersvalue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,3);
    nbrpersvalue.setValue(1);
    nbrpersres.setValueFactory(nbrpersvalue);
    prixvalue.setText("0");
   // control();
  nbrpersres.valueProperty().addListener(new ChangeListener<Integer>()
            {
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                   if(newValue==2) //2personnes
                   {
                       if (nbrchres.getValue()==3) //2personnes 3chambres
                       {
                           finalpricech=0;
                           finalpricepers=0;
                           Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
                         //  System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo2.setVisible(false);
               combotyres2.setVisible(false);
            
                       }
                       else if (nbrchres.getValue()==2) //2personnes 2 chambres
                       {
                           
                         finalpricech=40;
                           finalpricepers=30;
                           logecombo1.setVisible(true);
               combotyres1.setVisible(true);
               
                       }
                       else{ //2 personnes 1 chambre
                         finalpricech=20;
                         finalpricepers=30;
                   /*    pr=60;
                   pt=pt+pr;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
                    String ref2=Integer.toString(pt);
        prixtotalvalue.setText(ref2);*/
                       }
                       }
                   else if(newValue==3) //nbr personnes  3personnes
          { 
                       if (nbrchres.getValue()==3) //3personnes 3chambres
                       {
                           finalpricech=60;
                           finalpricepers=75;
              logecombo2.setVisible(true);
               combotyres2.setVisible(true);
                 logecombo1.setVisible(true);
               combotyres1.setVisible(true);
                       }
                       else if (nbrchres.getValue()==2){ //3pers 2chamb
                      finalpricech=40;
                           finalpricepers=50;
                           /* pr=80;
                       pt=pt+pr;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
         String ref2=Integer.toString(pt);
        prixtotalvalue.setText(ref2);*/
                       }
                       else{
                           finalpricech=0;
                           finalpricepers=0;
                       }
                           }        else   // 1personne
          {            
                       finalpricech=20;
                           finalpricepers=25;
                       logecombo2.setVisible(false);
              logecombo1.setVisible(false);
              combotyres1.setVisible(false);
              combotyres2.setVisible(false);

                 /*      pr=40;
          pt=pt+pr;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
         String ref2=Integer.toString(pt);
        prixtotalvalue.setText(ref2);*/
                           } 
          }

       
                
                
            }
  
   
  );
  
  
  
  
  
  
   nbrchres.valueProperty().addListener(new ChangeListener<Integer>()
            {
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                   if(newValue==2) //nbr chambres=2
                   {
                       if (nbrpersres.getValue()==1) 
           {
                finalpricech=0;
                           finalpricepers=0;
               Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
              // System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo1.setVisible(false);
               combotyres1.setVisible(false);
               combotyres2.setVisible(false);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            nnn.setText(refprp);
           }
           else{
                            finalpricech=40;
                           finalpricepers=30;
                       combotyres1.setVisible(true);
                       combotyres2.setVisible(false);
                       logecombo2.setVisible(false);
                       logecombo1.setVisible(true);
                        System.out.println("2222222222");
                        String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            nnn.setText(refprp);
                       }     
           }
                   else if(newValue==3) //nbr chambres
          { 
                        
                           
              if (nbrpersres.getValue()==1 || nbrpersres.getValue()==2)
           {
                           finalpricech=0;
                           finalpricepers=0;
               Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
             //  System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo1.setVisible(false);
               combotyres1.setVisible(false);
               combotyres2.setVisible(false);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            nnn.setText(refprp);
           }
           else{
                   
                           finalpricepers=75;
              combotyres1.setVisible(true);
              combotyres2.setVisible(true);
              logecombo1.setVisible(true);
              logecombo2.setVisible(true);
                       System.out.println("3333333333");
                       String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            nnn.setText(refprp);
              }                
           }        else // ken nbr chambres = 1
          {
                        finalpricech=20;
                           finalpricepers=25;
              combotyres1.setVisible(false);
              combotyres2.setVisible(false);
              logecombo1.setVisible(false);
              logecombo2.setVisible(false);
              //logecombo1.hide();
            //  logecombo.disableProperty();
               System.out.println("11111111111111");
              String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            nnn.setText(refprp);
                           } 
          }
            }
   
   
   
   );   
          
   
   nbrchres2.valueProperty().addListener(new ChangeListener<Integer>()
            {
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                   if(newValue==2) //nbr chambres=2
                   {
                       if (nbrpersres2.getValue()==1)
           {
                finalpricech=0;
                           finalpricepers=0;
               Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
              // System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo1tab2.setVisible(false);
               combotyres1tab2.setVisible(false);
               combotyres2tab2.setVisible(false);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
           }
           else{
                       combotyres1tab2.setVisible(true);
                       combotyres2tab2.setVisible(false);
                       logecombo2tab2.setVisible(false);
                       logecombo1tab2.setVisible(true);
                        System.out.println("2222222222");
                        String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
                       }     
           }
                   else if(newValue==3) //nbr chambres
          { 
              if (nbrpersres2.getValue()==1 || nbrpersres2.getValue()==2)
           {
               Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");

		// alert.setHeaderText("Results:");
		alert.setContentText("Veuillez choisir un nombre de chambres valide!");

		alert.showAndWait();
             //  System.out.println("Veuillez choisir un nombre de chambres valide!");
               logecombo1tab2.setVisible(false);
               combotyres1tab2.setVisible(false);
               combotyres2tab2.setVisible(false);
               String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
           }
           else{
              combotyres1tab2.setVisible(true);
              combotyres2tab2.setVisible(true);
              logecombo1tab2.setVisible(true);
              logecombo2tab2.setVisible(true);
                       System.out.println("3333333333");
              String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
              }                
           }        else // ken nbr chambres = 1
          {
              combotyres1tab2.setVisible(false);
              combotyres2tab2.setVisible(false);
              logecombo1tab2.setVisible(false);
              logecombo2tab2.setVisible(false);
              //logecombo1.hide();
            //  logecombo.disableProperty();
               System.out.println("11111111111111");
              String refxx=  pricetotal.getText();
               System.out.println("refxx");
               System.out.println(refxx);
               
      xxx= Double.parseDouble(refxx);
      finalprice=finalpricech+finalpricepers+xxx;
      System.out.println("final price:");
      System.out.println(finalprice);
       // String refxx=Integer.toString(pt2);
        prixtotalvalue2.setText(refxx);
         String refprp=Double.toString(finalprice);
            prixvalue2.setText(refprp);
                           } 
          }
            });   
          
   
   
    }
   

    @FXML
    private void confirmerreservationfct(ActionEvent event) {
       
        
int nbrpersaj=nbrpersres.getValue();
nbrchaj=nbrchres.getValue();
String logem=( combotyres.getValue()+  " Logement "  +logecombo.getValue() + "\n" 
       + combotyres1.getValue()+  "    "  +logecombo1.getValue() + "\n" 
       + combotyres2.getValue()+  "    "  +logecombo2.getValue() );

        String nomhotel;
        //double prixtotalres= 21.6;
        //idhotel
        //nbrperso
        //nbrch
        //typech
        //prix_res
      java.sql.Date datedebut = java.sql.Date.valueOf(datearriv.getValue());
      java.sql.Date datefin = java.sql.Date.valueOf(datefinres.getValue());
       
        ReservationHotelService rs = new ReservationHotelService();
    String idhotelres= labelrefhotel.getText();
     int idhotelf=Integer.parseInt(idhotelres);
     ReservationHotel r= new ReservationHotel(6,46,datedebut,datefin,pt,idhotelf,nbrpersaj,nbrchaj,logem,finalprice);
        
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    rs.ajouterReservationHotel(r);
    affichage2();
    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
		alert1.setTitle("Reservation Hotel");

		// alert.setHeaderText("Results:");
		alert1.setContentText("Réservation effectué avec succes!");

		alert1.showAndWait();
} else {
    // ... user chose CANCEL or closed the dialog
}
        
        
 
    }
 /*private void control(){
     
     Integer x=nbrpersres.getValue();
     do{
    if (x==2)
    {
        pr=pr+50;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
    }
    if (x==3)
    {
    pr=pr+70;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
    }
    if (x==1) 
    {
     pr=pr+10;
         String ref=Integer.toString(pr);
        prixvalue.setText(ref);
    }  
     }while ((x<1)&&(x>3));
 }*/

    @FXML
    private void affichageres() {
        try {
            
       HotelService sp = new HotelService();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table1.setItems(et);
       
    //   tableref.setCellValueFactory(new PropertyValueFactory<>("ref"));
       tablenom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       tabledescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
       tableimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tableville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
         tableprix1.setCellValueFactory(new PropertyValueFactory<>("Prixhotel"));
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
     
    }

    @FXML
    private void affichage2() {
        try {
       ReservationHotelService sp = new ReservationHotelService();
       List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table2.setItems(et);
       
    //   tableref.setCellValueFactory(new PropertyValueFactory<>("ref"));
    //id_resHtab2.setCellValueFactory(new PropertyValueFactory<>("id_resH"));   
    hoteltab2.setCellValueFactory(new PropertyValueFactory<>("id_hotel"));
       datedebtab2.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       datefintab2.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nombreperstab2.setCellValueFactory(new PropertyValueFactory<>("nbrperso"));
        nombrechtab2.setCellValueFactory(new PropertyValueFactory<>("nbrch"));
        typechtab2.setCellValueFactory(new PropertyValueFactory<>("typech"));
        prixtab2.setCellValueFactory(new PropertyValueFactory<>("prix_res"));
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AfficherHotelReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void modifierres(ActionEvent event) {
        int nbrpersaj=nbrpersres2.getValue();
nbrchaj=nbrchres2.getValue();
String logem=( combotyrestab2.getValue()+  " Logement "  +logecombotab2.getValue() + "\n" 
       + combotyres1tab2.getValue()+  "    "  +logecombo1tab2.getValue() + "\n" 
       + combotyres2tab2.getValue()+  "    "  +logecombo2tab2.getValue() );
java.sql.Date datedebut = java.sql.Date.valueOf(datearriv2.getValue());
      java.sql.Date datefin = java.sql.Date.valueOf(datefin2.getValue());
       
        ReservationHotelService rs = new ReservationHotelService();
    String idhotelres= labelrefhotel2.getText();
     int idhotelf=Integer.parseInt(idhotelres);
     
     String idhotelres2= labelreshotel2.getText();
     int idhotelf2=Integer.parseInt(idhotelres2);
     ReservationHotel r= new ReservationHotel(46,datedebut,datefin,pt,idhotelf,nbrpersaj,nbrchaj,logem,finalprice);
       rs.modifierReservationHotel(r,idhotelf2);
     affichage2();
      /*
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modification Hotel");

		// alert.setHeaderText("Results:");
		alert.setContentText("Hotel modifié avec succes");

		alert.showAndWait();
        
        */
    }

    @FXML
    private void annulerres(ActionEvent event) {
      ReservationHotelService rs = new ReservationHotelService();
        String idhotelres2= labelreshotel2.getText();
     int idhotelf2=Integer.parseInt(idhotelres2);
       rs.deletereshotel(idhotelf2);
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Annuler reservation");

		// alert.setHeaderText("Results:");
		alert.setContentText("Reservation annulé!");

		alert.showAndWait();
     affichage2();  
        
    }

    @FXML
    private void mailfct(ActionEvent event) {
        Stage primaryStage = null;
        Parent root;
      try{  
     root = FXMLLoader.load(getClass().getResource("MesReservationsHotel.fxml"));
       //  root = FXMLLoader.load(getClass().getResource("MesReservationsHotel.fxml"));
       // root = FXMLLoader.load(getClass().getResource("AjouterHotel.fxml"));
        Scene scene = new Scene(root);
      //  primaryStage.setTitle("Gestion des Hotels");
        primaryStage.setScene(scene);
        primaryStage.show();
        
      }catch(IOException ex){
        System.out.println(ex.getMessage());
      }
        
    }

    @FXML
    private void pdfextract(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException{
   PDFHotel p = new PDFHotel();
        p.GeneratePdf("Liste reservations en PDF");
    }
     
}
