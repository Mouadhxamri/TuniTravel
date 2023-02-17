/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button adminbtn;
    @FXML
    private Button clientbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void loadadmininterface(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
                 
                   

            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));	
		Parent root = loader.load();	
                   
                  
                    primaryStage.setTitle("Interface Admin");
                    primaryStage.setScene(new Scene(root,1280,720));
                    primaryStage.show();
              
        
      
        
    }
    

    @FXML
    private void loadclientinterface(ActionEvent event) throws IOException {
         Stage primaryStage =new Stage();
                 
                   

            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeClient.fxml"));	
		Parent root = loader.load();	
                   
                  
                    primaryStage.setTitle("Interface Client");
                    primaryStage.setScene(new Scene(root,1280,720));
                    primaryStage.show();
        
    }
    
}
