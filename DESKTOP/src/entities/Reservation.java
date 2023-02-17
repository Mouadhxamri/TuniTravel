/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.Hotel;
import entities.*;
import java.sql.Date;

public class Reservation {
  
    int id_res;
    int id_user;
    Date date_debut;
    Date date_fin;
    double prix_total;

    public Reservation(int id_user, Date date_debut, Date date_fin, double prix_total) {
        this.id_user = id_user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix_total = prix_total;
    }

    public Reservation() {
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }
  
}
