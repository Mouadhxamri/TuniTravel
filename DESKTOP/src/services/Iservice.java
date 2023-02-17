/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author asus
 */
public interface Iservice<T> {
      void ajouter(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean chercher(int id) throws SQLException;
    boolean chercher_ajout(T t)throws SQLException;
    boolean update(T t,int id) throws SQLException;
    List<T> readAll() throws SQLException;
    
}

