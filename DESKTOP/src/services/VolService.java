/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.Vol;
import tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hp
 */
public class VolService {
     Connection mc;
    PreparedStatement ste;

    public VolService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterVol(Vol v){
        String sql ="insert into vol(ref,depart,destination,date_d,date_r,prix_v) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, v.getRef());
            ste.setString(2, v.getDepart());
            ste.setString(3, v.getDestination());
            ste.setDate(4, v.getDate_d());
            ste.setDate(5, v.getDate_r());
            ste.setDouble(6, v.getPrix_v());
            ste.executeUpdate();
            System.out.println("Vol Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void supprimer(Vol v) {

        
            String sql = "delete from vol where ref = ?";
try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, v.getRef());
            ste.executeUpdate();
            System.out.println("Vol supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifierVol(Vol v) {
        String req = "update vol set depart = ? , destination = ? , date_d= ? , date_r= ? , prix_v= ?  where ref = ?";

        try {
            ste = mc.prepareStatement(req);
            ste.setString(6, v.getRef());
            ste.setString(1, v.getDepart());
            ste.setString(2, v.getDestination());
            ste.setDate(3, v.getDate_d());
            ste.setDate(4, v.getDate_r());
            ste.setDouble(5, (int) v.getPrix_v());
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    public List<Vol> afficherVol(){
        List<Vol> vols = new ArrayList<>();
        String sql="select * from vol";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Vol v = new Vol();
                v.setId_vol(rs.getInt("id_vol"));
                v.setRef(rs.getString("ref"));
                v.setDepart(rs.getString("depart"));
                v.setDestination(rs.getString("destination"));
                v.setDate_d(rs.getDate("date_d"));
                v.setDate_r(rs.getDate("date_r"));
                v.setPrix_v(rs.getDouble("prix_v"));
                vols.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return vols;
    }   
         public List<Vol> readAll() throws SQLException {
       String req="select * from vol  ";
        List<Vol> list = new ArrayList<>();
         Statement ste=mc.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               
               
                
               
                //ystem.out.println(v.getImage().toString());
                Vol p2=new Vol(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5),rs.getDate(6),rs.getDouble(7));
                //E131DHHJMNE code wifi whatelse?
               
                list.add(p2);
            
    
}
        return list;
    }
}
