/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.*;    
import javax.mail.internet.*;
import edu.esprit.gui.Mailer;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SendMailController implements Initializable {

    @FXML
    private TextField objetfield;
    @FXML
    private TextField messagefield;
    @FXML
    private TextField mailtofield;
    @FXML
    private Button envoyerbtn;
 
   // private Button btnbtn;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerfct(ActionEvent event) {
        Mailer m=null;
        String mailto = mailtofield.getText();
        String objett = objetfield.getText();
        String textt = messagefield.getText();
        m.send("tunitraveltech@gmail.com","tunitravel2022",mailto,objett,textt);
    }

    
}
