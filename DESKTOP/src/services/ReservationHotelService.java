/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Hotel;
import entities.Reservation;
import entities.ReservationHotel;
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
/**
 *
 * @author HP
 */
public class ReservationHotelService {
    Connection mc;
    PreparedStatement ste;

    public ReservationHotelService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterReservationHotel(ReservationHotel h){
        String sql ="INSERT INTO `reservation_hotel` (`id_user`, `date_debut`, `date_fin`, `prix_total`, `id_hotel`, `nbrperso`, `nbrch`, `typech`, `prix_res`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, h.getId_user());
            ste.setDate(2, h.getDate_debut());
            ste.setDate(3, h.getDate_fin());
            ste.setDouble(4, h.getPrix_total());
            ste.setInt(5, h.getId_hotel());
            ste.setInt(6, h.getNbrperso());
            ste.setInt(7, h.getNbrch());
            ste.setString(8, h.getTypech());
            ste.setDouble(9, h.getPrix_res());
            ste.executeUpdate();
            System.out.println("Reservation Effectuée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void deletereshotel(int id_resH) {
    
           String sql = "delete from reservation_hotel where id_resH = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1,id_resH);
            ste.executeUpdate();
            System.out.println("Reservation Hotel supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
    
    }
       
    public void supprimerReservationHotel(ReservationHotel h, int id_resH) {

        
            String sql = "delete from reservation_hotel where id_resH = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id_resH);
            ste.executeUpdate();
            System.out.println("Reservation Hotel supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifierReservationHotel(ReservationHotel h, int id_resH) {
        String req = "update reservation_hotel set date_debut = ? , date_fin = ? , prix_total= ? , id_hotel= ? , nbrperso= ? , nbrch= ? , typech= ? , prix_res= ? where id_resH = ?";

        try {
            ste = mc.prepareStatement(req);
             ste.setInt(9, id_resH);
           // ste.setInt(9, h.getId_user());
            ste.setDate(1, h.getDate_debut());
            ste.setDate(2, h.getDate_fin());
            ste.setDouble(3, h.getPrix_total());
            ste.setInt(4, h.getId_hotel());
            ste.setInt(5, h.getNbrperso());
            ste.setInt(6, h.getNbrch());
            ste.setString(7, h.getTypech());
            ste.setDouble(8, h.getPrix_res());
            ste.executeUpdate();
System.out.println("Reservation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public Hotel getHotelByRef(int id_hotel) {
        String request = "select nom from hotel ;";
        Hotel h = new Hotel();
        try {
            Statement stm = mc.createStatement();
            ResultSet res = stm.executeQuery(request);
            if (res.next()) {
                
                h.setNom(res.getString(1));
                
              //  System.out.println("Ref : "+ h.getRef() +"\nNom : "+ h.getNom() +"\nDescription : "+ h.getDescription() +"\nDatedeb : "+ h.getDatedeb() +"\nDatefin : "+ h.getDatefin() +"\nImage : "+ h.getImage()+ "\nVille :"+ h.getVille()+ "");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return h;
    }
    public List<ReservationHotel> afficherReservationHotel(){
        List<ReservationHotel> hotels = new ArrayList<>();
        String sql="select * from reservation_hotel";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                ReservationHotel h = new ReservationHotel();
                h.setId_resH(rs.getInt("id_resH"));
                h.setDate_debut(rs.getDate("date_debut"));
            h.setDate_fin(rs.getDate("date_fin"));
            h.setPrix_total(rs.getDouble("prix_total"));
            h.setId_hotel(rs.getInt("id_hotel"));
            h.setNbrperso(rs.getInt("nbrperso"));
            h.setNbrch(rs.getInt("nbrch"));
            h.setTypech(rs.getString("typech"));
            h.setPrix_res(rs.getDouble("prix_res"));
             
                hotels.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return hotels;
    } 
    
     public List<ReservationHotel> readAll() throws SQLException {
       String req="select * from reservation_hotel;";
       List<ReservationHotel> list = new ArrayList<>();
         Statement ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
                
               
                //ystem.out.println(v.getImage().toString());
                ReservationHotel p2=new ReservationHotel(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4), rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getDouble(10));
                //E131DHHJMNE code wifi whatelse?
               System.out.println(p2);
                list.add(p2);
            
    
}
        return list;
    }
}
