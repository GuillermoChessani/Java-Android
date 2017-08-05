package com.example.usuario.cheasytaxi;

import com.google.android.gms.maps.model.LatLng;

public class Punto{

    double latitud;
    double longitud;


    public Punto(){
        this.latitud= latitud;
        this.longitud= longitud;
    }

    public Punto(double latitud,double longitud){
        this.latitud= latitud;
        this.longitud= longitud;
    }

    public void setlatitud(double latitud){
        this.latitud = latitud;
    }

    public void setlongitud(double longitud){
        this.longitud = longitud;
    }

    public double getlatitud(){
        return latitud;
    }

    public double getlongitud(){
        return longitud;
    }

    public double distanciaEuclideana(double lat1, double lat2, double long1,double long2){

        double res = (Math.pow(lat2 - lat1, 2) + Math.pow(long2 - long1, 2));
        return res;
    }
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
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