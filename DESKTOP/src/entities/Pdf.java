/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList; 
import Connexion.Datasource;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class Pdf {
        private Connection con;
        private Statement ste;
        private final Connection connexion;
    public Pdf()  {
       connexion = Datasource.getInstance().getCnx();
          
    
}
    public void add(String file,String N,String N1,String N2) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                    
                
                
                
          
                
                
                
                
                
                
                
                
                
                
                              
                                table_cell=new PdfPCell(new Phrase("Description"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Type"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N1));
                                my_report_table.addCell(table_cell);
                                    table_cell=new PdfPCell(new Phrase("Date"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N2));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Etat"));
                                my_report_table.addCell(table_cell);
                               
                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }
     
}
