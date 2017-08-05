package com.example.usuario.cheasytaxi;

/**
 * Created by Usuario on 13/11/2015.
 */
public class Operador {
    String Nombre;
    double Lat;
    double Long;
    String Unidad;

    public Operador(String Nombre, double Lat, double Long, String Unidad){
        this.Nombre=Nombre;
        this.Lat = Lat;
        this.Long=Long;
        this.Unidad = Unidad;
     }

    public String getNombre(){
        return Nombre;
    }
    public double getLat(){
        return Lat;
    }
    public double getLong(){
        return Long;
    }
    public String getUnidad(){
        return Unidad;
    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

}
