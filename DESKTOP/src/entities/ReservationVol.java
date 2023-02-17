/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.Vol;
import entities.Reservation;
import java.sql.Date;
import java.util.logging.Logger;

public class ReservationVol extends Reservation{
   
  private int id_resV,id_vol,nbrpsg;
  private String classe,aerocomp;
private double prix_v;  
public ReservationVol(int id_resV, int id_user, Date date_debut, Date date_fin, double prix_total,int id_vol,int nbrpsg ,String aerocomp ,String classe ){
      
     this.id_resV = id_resV;
        this.id_vol = id_vol;
       // this.prix_v = prix_v;
        this.prix_total = prix_total;
        this.nbrpsg = nbrpsg;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.classe = classe;
        this.aerocomp = aerocomp;
                        

}

   public ReservationVol(int id_user, Date date_debut, Date date_fin, double prix_total,int id_vol,int nbrpsg ,String aerocomp ,String classe ){
      
    
        this.id_vol = id_vol;
        this.prix_total = prix_total;
        this.nbrpsg = nbrpsg;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.classe = classe;
        this.aerocomp = aerocomp;
                        

}

    public ReservationVol() {
        
    }



   

    public int getId_resV() {
        return id_resV;
    }

    public void setId_resV(int id_resV) {
        this.id_resV = id_resV;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getNbrpsg() {
        return nbrpsg;
    }

    public void setNbrpsg(int nbrpsg) {
        this.nbrpsg = nbrpsg;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAerocomp() {
        return aerocomp;
    }

    public void setAerocomp(String aerocomp) {
        this.aerocomp = aerocomp;
    }

    public double getPrix_v() {
        return prix_v;
    }

    public void setPrix_v(double prix_v) {
        this.prix_v = prix_v;
    }



    
}
