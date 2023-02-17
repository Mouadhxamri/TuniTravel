/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
/*
public class Connexion {
    static Connection cnx;
    static String url ="jdbc:mysql://localhost:3306/tunitravel";
    static String user="root";
    static String pwd="";
    static Statement ste;

    public static void main(String[] args) {
        try {
            try {
                cnx=DriverManager.getConnection(url, user, pwd);
                System.out.println("Connexion etablie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
          //  String sql="INSERT INTO `hotel`(`id`, `nom`, `description`, `nbrchambres`, `categorie`, `prix`) VALUES (1,'Mouradi,'adbch','5','5*','230')";
         //   ste=cnx.createStatement();
         //   ste.executeUpdate(sql); // L'ajout d'un Hotel
         //   System.out.println("Hotel Ajoutée");
        //   String sql2="select * from hotel";
        //   ResultSet result=ste.executeQuery(sql2);
        //   while(result.next()){
          //     int id =result.getInt("id");
            //   String nom=result.getString(2);
          //     String description= result.getString("description");
          //     int nbrchambres =result.getInt("nbrchambres");
         //      String categorie =result.getString("categorie");
         //      int prix =result.getInt("prix");
         ///      System.out.println("ID : "+id+"\nNom : "+nom+"\nDescription : "+description+"\nNbrchambres : "+nbrchambres+"\nCategorie : "+categorie+"\nPrix : "+prix);   
          // }
           
     //   }
         //   catch (SQLException ex) {
       //     System.out.println(ex.getMessage());
         //   System.out.println("fama chay!");
        }
    }
    }
    
}
*/