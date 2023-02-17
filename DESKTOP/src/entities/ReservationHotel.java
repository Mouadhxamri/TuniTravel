/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.Hotel;
import entities.Reservation;
import java.sql.Date;
import java.util.logging.Logger;

public class ReservationHotel extends Reservation{
   
  private int id_resH,id_hotel,nbrperso,nbrch;
  private String typech,nom_hotel;
private double prix_res;  
public ReservationHotel(int id_resH, int id_user, Date date_debut, Date date_fin, double prix_total,int id_hotel,int nbrperso ,int nbrch ,String typech ,double prix_res){
       this.id_resH = id_resH;
        this.id_hotel = id_hotel;
        this.prix_res = prix_res;
        this.prix_total = prix_total;
        this.nbrperso = nbrperso;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.nbrch = nbrch;
        this.typech = typech;
                        

}

public ReservationHotel(int id_res, int id_user, Date date_debut, Date date_fin, double prix_total,int id_hotel,String nom_hotel,int nbrperso ,int nbrch ,String typech ,double prix_res){
       //this.id_resH = id_resH;
        this.id_hotel = id_hotel;
        this.prix_res = prix_res;
        this.prix_total = prix_total;
        this.nbrperso = nbrperso;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.nbrch = nbrch;
        this.typech = typech;
        this.nom_hotel= nom_hotel;

}
public ReservationHotel(int id_user, Date date_debut, Date date_fin, double prix_total,int id_hotel,int nbrperso ,int nbrch ,String typech ,double prix_res){
       //this.id_resH = id_resH;
        this.id_hotel = id_hotel;
        this.prix_res = prix_res;
        this.prix_total = prix_total;
        this.nbrperso = nbrperso;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.nbrch = nbrch;
        this.typech = typech;
        

}
public ReservationHotel(Date date_debut, Date date_fin,int nbrperso ,int nbrch ,double prix_res){
       //this.id_resH = id_resH;
       // this.id_hotel = id_hotel;
        this.prix_res = prix_res;
        
        this.nbrperso = nbrperso;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
      //  this.id_user = id_user;
        this.nbrch = nbrch;
      //  this.typech = typech;
        

}
    public ReservationHotel() {
        super();
    }

    public int getId_resH() {
        return id_resH;
    }

    public void setId_resH(int id_resH) {
        this.id_resH = id_resH;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public double getPrix_res() {
        return prix_res;
    }

    public void setPrix_res(double prix_res) {
        this.prix_res = prix_res;
    }

    public int getNbrperso() {
        return nbrperso;
    }

    public void setNbrperso(int nbrperso) {
        this.nbrperso = nbrperso;
    }

    public int getNbrch() {
        return nbrch;
    }

    public void setNbrch(int nbrch) {
        this.nbrch = nbrch;
    }

    public String getTypech() {
        return typech;
    }

    public void setTypech(String typech) {
        this.typech = typech;
    }

    
}
