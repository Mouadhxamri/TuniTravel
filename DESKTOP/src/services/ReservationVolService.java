/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Vol;
import entities.Reservation;
import entities.ReservationVol;
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
/**
 *
 * @author HP
 */
public class ReservationVolService {
    Connection mc;
    PreparedStatement ste;

    public ReservationVolService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterReservationVol(ReservationVol v){
        String sql ="INSERT INTO `reservation_vol` (`id_user`, `date_debut`, `date_fin`, `prix_total`, `id_vol`, `nbrpsg`, `aerocomp`, `classe`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, v.getId_user());
            ste.setDate(2, v.getDate_debut());
            ste.setDate(3, v.getDate_fin());
            ste.setDouble(4, v.getPrix_total());
            ste.setInt(5, v.getId_vol());
            ste.setInt(6, v.getNbrpsg());
            ste.setString(7, v.getAerocomp());
            ste.setString(8, v.getClasse());
            ste.executeUpdate();
            System.out.println("Reservation Effectuée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void supprimerReservationVol(int id_resV) {

        
            String sql = "delete from reservation_vol where id_resV = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id_resV);
            ste.executeUpdate();
            System.out.println("ReservationVol supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifierReservationVol(ReservationVol v, int id_resV) {
        String req = "update reservation_vol set date_debut = ? , date_fin = ? , prix_total= ? , id_vol= ? , nbrpsg= ? , aerocomp= ? , classe= ?  where id_resV = ?" ;

        try {
            ste = mc.prepareStatement(req);
            ste.setInt(8, id_resV);
            ste.setDate(1, v.getDate_debut());
            ste.setDate(2, v.getDate_fin());
            ste.setDouble(3, v.getPrix_total());
            ste.setInt(4, v.getId_vol());
            ste.setInt(5, v.getNbrpsg());
            ste.setString(6, v.getAerocomp());
            ste.setString(7, v.getClasse());

            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public List<ReservationVol> afficherReservationVol(){
        List<ReservationVol> vols = new ArrayList<>();
        String sql="select * from reservation_vol";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                ReservationVol v = new ReservationVol();
                v.setId_resV(rs.getInt("id_resV"));
                v.setDate_debut(rs.getDate("date_debut"));
            v.setDate_fin(rs.getDate("date_fin"));
            v.setPrix_total(rs.getDouble("prix_total"));
            v.setId_vol(rs.getInt("id_vol"));
            v.setNbrpsg(rs.getInt("nbrpsg"));
            v.setAerocomp(rs.getString("aerocomp"));
            v.setClasse(rs.getString("classe"));
            v.setPrix_v(rs.getDouble("prix_v"));
             
                vols.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return vols;
    } 
      public List<ReservationVol> readAll() throws SQLException {
       String req="select * from reservation_vol  ";
        List<ReservationVol> list = new ArrayList<>();
         Statement ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
                
               
                //ystem.out.println(v.getImage().toString());
                ReservationVol p2=new ReservationVol(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4), rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9));
                //E131DHHJMNE code wifi whatelse?
               
                list.add(p2);
            
    
}
        return list;
    }
}
