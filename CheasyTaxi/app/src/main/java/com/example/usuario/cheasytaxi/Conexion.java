package com.example.usuario.cheasytaxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Usuario on 23/11/2015.
 */
public class Conexion {
    Connection con=null;
    public Connection conexion(){
        try {
            //cargar nuestro driver
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://sql5c75c.carrierzone.com/cheasytaxi_ceitamcom1802685","ceitamcom1802685","chessani93");
            System.out.println("conexion establecida");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error de conexion");

        }
        return con;
    }
}