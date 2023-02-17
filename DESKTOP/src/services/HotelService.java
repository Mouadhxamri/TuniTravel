/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hotel;
import java.io.FileOutputStream;
import tools.MaConnexion;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class HotelService {
    Connection mc;
    PreparedStatement ste;

    public HotelService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterHotel(Hotel h){
        String sql ="insert into hotel(ref,nom,description,datedeb,datefin,image,ville) Values(?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, h.getRef());
            ste.setString(2, h.getNom());
            ste.setString(3, h.getDescription());
            ste.setDate(4, h.getDatedeb());
            ste.setDate(5, h.getDatefin());
            ste.setString(6, h.getImage());
            ste.setString(7, h.getVille());
            ste.executeUpdate();
            System.out.println("Hotel Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void ajouterHotel1(Hotel h){
        String sql ="insert into hotel(ref,nom,description,datedeb,datefin,image,ville,prixhotel) Values(?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, h.getRef());
            ste.setString(2, h.getNom());
            ste.setString(3, h.getDescription());
            ste.setDate(4, h.getDatedeb());
            ste.setDate(5, h.getDatefin());
            ste.setString(6, h.getImage());
            ste.setString(7, h.getVille());
            ste.setDouble(8, h.getPrixhotel());
            ste.executeUpdate();
            System.out.println("Hotel Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void supprimer(Hotel h) {

        
            String sql = "delete from hotel where ref = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, h.getRef());
            ste.executeUpdate();
            System.out.println("Hotel supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifierHotel(Hotel h) {
        String req = "update hotel set nom = ? , description = ? , datedeb= ? , datefin= ? , image= ? , ville= ? where ref = ?";

        try {
            ste = mc.prepareStatement(req);
            ste.setInt(7, h.getRef());
            ste.setString(1, h.getNom());
            ste.setString(2, h.getDescription());
            ste.setDate(3, (java.sql.Date) h.getDatedeb());
            ste.setDate(4, (java.sql.Date) h.getDatefin());
            ste.setString(5, h.getImage());
            ste.setString(6, h.getVille());
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   public void modifierHotel1(Hotel h) {
        String req = "update hotel set nom = ? , description = ? , datedeb= ? , datefin= ? , image= ? , ville= ? , prixhotel= ? where ref = ?";

        try {
            ste = mc.prepareStatement(req);
            ste.setInt(8, h.getRef());
            ste.setString(1, h.getNom());
            ste.setString(2, h.getDescription());
            ste.setDate(3, (java.sql.Date) h.getDatedeb());
            ste.setDate(4, (java.sql.Date) h.getDatefin());
            ste.setString(5, h.getImage());
            ste.setString(6, h.getVille());
            ste.setDouble(7, h.getPrixhotel());
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List<Hotel> afficherHotel(){
        List<Hotel> hotels = new ArrayList<>();
        String sql="select * from hotel";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Hotel h = new Hotel();
                h.setId_hotel(rs.getInt("id_hotel"));
                h.setRef(rs.getInt("Ref"));
                h.setNom(rs.getString("nom"));
                h.setDescription(rs.getString("description"));
                h.setDatedeb(rs.getDate("datedeb"));
                h.setDatefin(rs.getDate("datefin"));
                h.setVille(rs.getString("Ville"));
                h.setVille(rs.getString("prixhotel"));
                hotels.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return hotels;
    }    
    public Hotel getHotelByRef(int ref) {
        String request = "select id,ref,nom,description,datedeb,datefin,image,ville,prixhotel from hotel where ref=" + ref;
        Hotel h = new Hotel();
        try {
            Statement stm = mc.createStatement();
            ResultSet res = stm.executeQuery(request);
            if (res.next()) {
                h.setId_hotel(res.getInt(1));
                h.setRef(res.getInt(2));
                h.setNom(res.getString(3));
                h.setDescription(res.getString(4));
                h.setDatedeb(res.getDate(5));
                h.setDatefin(res.getDate(6));
                h.setImage(res.getString(7));
                h.setVille(res.getString(8));
                h.setPrixhotel(res.getDouble(9));
                System.out.println("Ref : "+ h.getRef() +"\nNom : "+ h.getNom() +"\nDescription : "+ h.getDescription() +"\nDatedeb : "+ h.getDatedeb() +"\nDatefin : "+ h.getDatefin() +"\nImage : "+ h.getImage()+ "\nVille :"+ h.getVille()+ "");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return h;
    }
    public void deletehotel(int ref) {
    
           String sql = "delete from hotel where ref = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1,ref);
            ste.executeUpdate();
            System.out.println("Hotel supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
    
    }
        
       

    
    public boolean chercher(int ref)  {
        String req="select * from hotel";
        List<Integer> list = new ArrayList<>();
        
        try {
            Statement stm = mc.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(ref);
    }
     public List<Hotel> readAll() throws SQLException {
       String req="select * from hotel  ";
        List<Hotel> list = new ArrayList<>();
         Statement ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
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
            
    
}
        return list;
    }
}
