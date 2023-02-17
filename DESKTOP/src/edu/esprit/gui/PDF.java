/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import entities.ReservationHotel;
import entities.Hotel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class PDF {
    
    public void PDF(Hotel p) throws SQLException, FileNotFoundException, DocumentException, BadElementException, IOException {
        try {
            // System.out.println("Haouet------------------------------------->"+nom);

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\imagehotel\\" + randomNum + ".pdf"));
            document.open();
             Image img=Image.getInstance("http://127.0.0.1/imagehotel/"+p.getImage());
             img.setWidthPercentage(20);
           Paragraph adrr = new Paragraph(new Phrase("Référence  : "+p.getRef(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
           Paragraph adrr1 = new Paragraph(new Phrase("Nom  : "+p.getNom(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
             Paragraph par=new Paragraph(" votre Hotel  ", FontFactory.getFont(FontFactory.TIMES));
             par.setAlignment(Element.ALIGN_CENTER);
            document.add(par);
             

             document.add(img);

             document.add(adrr);
              document.add(adrr1);
            document.add(new Paragraph("date debut de l'hotel : "+p.getDatedeb(), FontFactory.getFont(FontFactory.TIMES)));
            document.add(new Paragraph("date fin de l l'hotel : "+p.getDatefin(), FontFactory.getFont(FontFactory.TIMES)));

          
            document.close();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
