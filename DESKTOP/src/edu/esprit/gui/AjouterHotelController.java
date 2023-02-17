/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import static com.itextpdf.text.pdf.BidiOrder.PDF;
import entities.Hotel;
import tools.MaConnexion;
import entities.ReservationHotel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeJava.type;
import services.HotelService;
import tools.MaConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Action;
import org.controlsfx.control.*;
import org.controlsfx.*;
//import org.controlsfx.control.NotificationAPI;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterHotelController implements Initializable {

    @FXML
    private Button ajouterbtn;
    @FXML
    private TextField reffield;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private DatePicker datedebpicker;
    @FXML
    private DatePicker datefinpicker;
    @FXML
    private TextField villefield;
    @FXML
    private Button imagee;
    @FXML
    private ImageView importeimage;
    private Hotel cc=null;
 String img="";
    List<String> type;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private TableView<Hotel> table1;
    @FXML
    private TableColumn<Hotel,String> tableref;
    @FXML
    private TableColumn<Hotel,String> tablenom;
    @FXML
    private TableColumn<Hotel,String> tabledescription;
    @FXML
    private TableColumn<Hotel,Date> tabledatedeb;
    @FXML
    private TableColumn<Hotel,Date> tabledatefin;
    @FXML
    private TableColumn<Hotel,String> tableimage;
    @FXML
    private TableColumn<Hotel,String> tableville;
    @FXML
    private TextField recherchehotel;
    @FXML
    private Button pdf;
    @FXML
    private TextField prixhotelfield;
    @FXML
    private TableColumn<Hotel,String> tableprixhotel;
    @FXML
    private Button pdfrapport;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage();
     
datedebpicker.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    }); 
datefinpicker.setDayCellFactory(picker -> new DateCell(){
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
LocalDate dated = datedebpicker.getValue();
            setDisable(empty || date.compareTo(dated) <  0);
        }
    }); 

        table1.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                cc = (Hotel)table1.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                int ref1= cc.getRef();
               String ref=Integer.toString(ref1); 
           reffield.setText(ref);
            nomfield.setText(cc.getNom());
                descriptionfield.setText(cc.getDescription());
                villefield.setText(cc.getVille());
                 double prixhh= cc.getPrixhotel();
           String prixhhh=Double.toString(prixhh);
           prixhotelfield.setText(prixhhh);
                importeimage.setImage(new Image("http://127.0.0.1/imagehotel/"+cc.getImage()));
               // java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       //java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       LocalDate d1=cc.getDatedeb().toLocalDate();
                LocalDate d2=cc.getDatefin().toLocalDate();
                datedebpicker.setValue(d1);
                datefinpicker.setValue(d2);
               
            }
          });
        
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
          
    }    

         
     
     
    @FXML
    private void ajouterhotel(ActionEvent event) {
        String ref = reffield.getText();
        int ref1=Integer.parseInt(ref);
        String nom = nomfield.getText();
        String description = descriptionfield.getText();
        String ville = villefield.getText();
        String prixhotel = prixhotelfield.getText();
         double prixhotel1=Double.parseDouble(prixhotel);
        
      //  LocalDate datedebut = datedebpicker.getValue();
       java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       if(reffield.getText().trim().isEmpty()){
       Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("Champs vide !");
        fail.showAndWait();    
         
     }  
    
       // Date datedebut1 = LocalDate.pa
       // Date datefin = datefinpicker.
      // Hotel h = new Hotel();
       Hotel p1=new Hotel(50,ref1,nom,description,datedebut,datefin,img,ville,prixhotel1);
       HotelService ps = new HotelService();
       //ps.chercher(ref1);
       
           
       if (ps.chercher(ref1)==false)
       {
       ps.ajouterHotel1(p1);
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterHotel.fxml"));
       
       try{  
        
       Parent root = loader.load();
        affichage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Ajout Hotel");

		// alert.setHeaderText("Results:");
		alert.setContentText("Hotel Ajouté avec succes!");

		alert.showAndWait();
      }catch(IOException ex){
        System.out.println(ex.getMessage());
      }
       }
       else
       {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Ajout Hotel");

		// alert.setHeaderText("Results:");
		alert.setContentText("ID Existant!");

		alert.showAndWait();
       }
       
    }

    @FXML
    private void importimage(ActionEvent event) {
        
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        
        if(fc != null)
        {   
            System.out.println(fc.getName());
            img=fc.getName();
           FileSystem fileSys = FileSystems.getDefault();
           Path srcPath= fc.toPath();
           Path destPath= fileSys.getPath("C:\\xampp\\htdocs\\imagehotel\\"+fc.getName());
            try {
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AjouterHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(srcPath.toString());
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            importeimage.setImage(i);
            
        }
        
    }

    @FXML
    private void modifierhotelfct(ActionEvent event) {
        String ref = reffield.getText();
        int ref1=Integer.parseInt(ref);
        String nom = nomfield.getText();
        String description = descriptionfield.getText();
        String ville = villefield.getText();
        String prixhotel = prixhotelfield.getText();
         double prixhotel1=Double.parseDouble(prixhotel);
         
       java.sql.Date datedebut = java.sql.Date.valueOf(datedebpicker.getValue());
       java.sql.Date datefin = java.sql.Date.valueOf(datefinpicker.getValue());
       
       Hotel p1=new Hotel(50,ref1,nom,description,datedebut,datefin,img,ville,prixhotel1);
       HotelService ps = new HotelService();
       ps.modifierHotel1(p1);
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modification Hotel");

		// alert.setHeaderText("Results:");
		alert.setContentText("Hotel modifié avec succes");

		alert.showAndWait();
    }

    @FXML
    private void supprimerhotelfct(ActionEvent event) throws SQLException  {
     /*  Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Hotel supprimé avec succes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(Action event)
                       {
                           System.out.println("clicked on");
                       }
               });
       notificationBuilder.darkStyle();*/
        HotelService ps = new HotelService();
        String ref = reffield.getText();
        int ref1=Integer.parseInt(ref);
    //    Hotel p1=new Hotel(50,ref1,nom,description,datedebut,datefin,img,ville);
 //   Hotel p1=new Hotel();   
       ps.deletehotel(ref1);
       //notificationBuilder.show();
       affichage();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppression Hotel");

		// alert.setHeaderText("Results:");
		alert.setContentText("Hotel supprimé avec succes!");

		alert.showAndWait();
    }

    @FXML
    private void affichage(){
        try {
       HotelService sp = new HotelService();
       List events=sp.readAll();
       ObservableList<Hotel> et=FXCollections.observableArrayList(events);
       //listview.setItems(et);
       
      table1.setItems(et);
       
       tableref.setCellValueFactory(new PropertyValueFactory<>("ref"));
       tablenom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       tabledescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
       tabledatedeb.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
       tabledatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
       tableimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tableville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        tableprixhotel.setCellValueFactory(new PropertyValueFactory<>("Prixhotel"));
       
       
        } catch (SQLException ex) {
                Logger.getLogger(AjouterHotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void rechercherhotel(ActionEvent event) throws SQLException {
     
        String search11 = recherchehotel.getText();
    
        String req="select * from hotel where nom = '"+search11+"' or ville = '"+search11+"'";
       List<Hotel> list = new ArrayList<>();
       Connection mc;
    Statement ste;

    
        mc=MaConnexion.getInstance().getCnx();
    
     ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            if(rs.next()){
              
               
               ImageView v = new ImageView();
                System.out.println(rs.getString(7));
                
                v.setImage(new Image("http://127.0.0.1/imagehotel/"+rs.getString(7)));
                v.setFitWidth(100);
                v.setFitHeight(100);
               
                //ystem.out.println(v.getImage().toString());
                Hotel p2=new Hotel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4), rs.getDate(5),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getDouble(9));
                //E131DHHJMNE code wifi whatelse?
                p2.setPhoto(v);
                list.add(p2);
                HotelService sp = new HotelService();
      // List events=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(list);
       table1.setItems(et);
       
       tableref.setCellValueFactory(new PropertyValueFactory<>("ref"));
       tablenom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       tabledescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
       tabledatedeb.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
       tabledatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
       tableimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
        tableville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        tableprixhotel.setCellValueFactory(new PropertyValueFactory<>("Prixhotel"));
    }   
            else {
                affichage();
            }
       
       
       
        
    }

    @FXML
    private void pdfextraire(ActionEvent event) {
        

    }

    @FXML
    private void pdfrapportextract(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        PDFHotel p = new PDFHotel();
        p.GeneratePdf("Liste Reservations en PDF");
    }
   
    
    
    
   
}
