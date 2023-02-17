/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import entities.*;
import services.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;
import java.sql.Connection;

/**
 *
 * @author mahas
 */
public class PDFHotel{
     private Statement ste;
     private Connection con;
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        //Image img = Image.getInstance("photo.png");
        //Image img2 = Image.getInstance("logo.png");
        ReservationHotelService us=new ReservationHotelService();
        con = MaConnexion.getInstance().getCnx();
                ste = con.createStatement();
       List<ReservationHotel> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT date_debut,date_fin,nbrperso,nbrch, prix_res   from `reservation_hotel`;");
     while (rs.next()) {                
          /*     int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               float total=rs.getFloat(3);
               String etat=rs.getString(4);*/
          int nbrperso = rs.getInt(3);
          int nbrch = rs.getInt(4);
          double prix_res = rs.getDouble(5);
          java.sql.Date datedebut = rs.getDate(1);
          java.sql.Date datefin = rs.getDate(2);
          
          
          ReservationHotel e=new ReservationHotel(datedebut,datefin,nbrperso,nbrch,prix_res);   
               
        arr.add(e);}
     document.add(new Paragraph("-------------------------Rapport Hotels------------------------------------------------------------- "));
        
        for(ReservationHotel h:arr)
        {
        document.add(new Paragraph("Date debut :"+h.getDate_debut()));
        document.add(new Paragraph("Date fin :"+ h.getDate_fin()));
        document.add(new Paragraph("Nombres personnes :"+h.getNbrperso()));
        document.add(new Paragraph("Nombres de chambres  :"+h.getNbrch()));
        document.add(new Paragraph("Prix :"+h.getPrix_res()));
           //document.add(img);
         //document.add(img2);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                                                                        "));
        
        } 
        
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
