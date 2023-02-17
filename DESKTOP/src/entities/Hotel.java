/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.text.SimpleDateFormat;  
//import java.util.Date;
import java.sql.Date;
import javafx.scene.image.ImageView;

public class Hotel {
    private int id_hotel,ref;
    private String nom,description,image,ville;
    private Date datedeb,datefin;
    private ImageView photo;
    private double prixhotel;
    public Hotel() {
    }

    public Hotel(int id_hotel, int ref, String nom, String description, Date datedeb, Date datefin, String image, String ville, double prixhotel) {
        this.id_hotel = id_hotel;
        this.ref = ref;
        this.nom = nom;
        this.description = description;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.image = image;
        this.ville = ville;
        this.prixhotel = prixhotel;
        
    }

    public Hotel(int id_hotel, int ref, String nom, String description, String image, String ville, Date datedeb, Date datefin, ImageView photo, double prixhotel) {
        this.id_hotel = id_hotel;
        this.ref = ref;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.ville = ville;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.photo = photo;
        this.prixhotel = prixhotel;
    }

  
   
   

   

    @Override
    public String toString() {
        return "Hotel{" + "id_hotel=" + id_hotel + ", ref=" + ref + ", nom=" + nom + ", description =" + description + ", datedeb =" + datedeb  + ", datefin =" + datefin  +", image =" + image  +", ville =" + ville +", prixhotel=" + prixhotel +'}';
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public double getPrixhotel() {
        return prixhotel;
    }

    public void setPrixhotel(double prixhotel) {
        this.prixhotel = prixhotel;
    }


    
}
