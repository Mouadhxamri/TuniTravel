/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import java.sql.Date;
/**
 *
 * @author hp
 */
public class Vol {
    private int id_vol; 
    private double prix_v;
    private String depart,destination,ref;
    private Date date_d,date_r;
 

   public Vol () {
       
   }

    public Vol(int id_vol,String ref,String depart, String destination, java.sql.Date date_d, java.sql.Date date_r, double prix_v) {
        this.id_vol = id_vol;
        this.ref = ref;
        this.depart = depart;
        this.destination = destination;
        this.date_d = date_d;
        this.date_r = date_r;
        this.prix_v= prix_v;
    }

    
    

    
   
    @Override
    public String toString() {
        return "Vol{" + "id_vol=" + id_vol + ", ref=" + ref  + ", depart=" + depart + ", destination=" + destination + ", date_d =" + date_d + ", date_r =" + date_r  + ", prix_v =" + prix_v  + '}';
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public double getPrix_v() {
        return prix_v;
    }

    public void setPrix_v(double prix_v) {
        this.prix_v = prix_v;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_r() {
        return date_r;
    }

    public void setDate_r(Date date_r) {
        this.date_r = date_r;
    }

    
   

    
}
