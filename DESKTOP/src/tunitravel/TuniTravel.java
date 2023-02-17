
package tunitravel;

import services.HotelService;
import services.ReservationHotelService;
import tools.MaConnexion;

import entities.Hotel;
import entities.ReservationHotel;
import java.sql.Date;
import java.util.List;
//import java.text.SimpleDateFormat;  

public class TuniTravel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        MaConnexion m = MaConnexion.getInstance();
        HotelService ps = new HotelService();
        
        
        ////HOTEL
        // Hotel p1=new Hotel(50,60,"Helmi m","mmmm",new Date(2022-12-01),new Date(2022-12-07),"hhhh","Sousse");
        // Hotel p=new Hotel(50,60,"Helmi movenpick","mmmm",new Date(2022-12-01),new Date(2022-12-07),"hhhh","Sousse");

     //ps.ajouterHotel(p);

     //ps.modifierHotel(p1);
     // ps.supprimer(p1);
     // ps.getHotelByRef(60);
     // System.out.println(ps.afficherHotel());
       
       //////////RESERVATION
     ReservationHotelService rs = new ReservationHotelService();
     
     ReservationHotel r= new ReservationHotel(45,new Date(2022-11-01),new Date(2022-12-07),700.2,50,6,1,"single",140.1);
   
    
     // rs.ajouterReservationHotel(r);
     rs.modifierReservationHotel(r,17);
    //  rs.supprimerReservationHotel(r);
     // rs.getHotelByRef(60);
   //   System.out.println(rs.afficherReservationHotel());
    }
   
    
}
