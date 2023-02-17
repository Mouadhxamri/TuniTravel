/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import entities.Hotel;
import services.HotelService;
import tools.MaConnexion;

public class Main {
     public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        //Personne p=new Personne(9999, "Mezri", "Aziz");
        HotelService ps = new HotelService();
       // ps.ajouterPersonne(p);
        System.out.println(ps.afficherHotel());
    }
}
