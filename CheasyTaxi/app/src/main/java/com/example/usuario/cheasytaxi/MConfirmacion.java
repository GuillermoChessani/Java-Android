package com.example.usuario.cheasytaxi;

import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MConfirmacion extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Operador[] Operador = new Operador[20];
    double menor = 10000;
    double mayor =0;
    int aux=0;
    String duracion, distancia, tarifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mconfirmacion);
        setUpMapIfNeeded();
        Bundle bundle = this.getIntent().getExtras();
        distancia= bundle.getString("DISTANCIA");
        duracion =bundle.getString("DURACION");
        tarifa = bundle.getString("TARIFA");

        Operador[0]= new Operador("José Martinez",22.282716, -97.872907,"XCJ2299");
        Operador[1]= new Operador("Juan Bautista",22.279817, -97.854216 ,"YHK2294");
        Operador[2]= new Operador("Pedro Mendez",22.256709, -97.849925 ,"GTK4723");
        Operador[3]= new Operador("Rafael Lopez",22.266953, -97.873709 ,"OUI5869");
        Operador[4]= new Operador("Raul Castro",22.283735, -97.870068 ,"YWX8589");
        Operador[5]= new Operador("Saul Hernandez",22.379476, -97.901055 ,"YTU8534");
        Operador[6]= new Operador("Hernan Cruces",22.279506, -97.824682 ,"IYW4859");
        Operador[7]= new Operador("Armando Rodriguez",22.310534, -97.880329 ,"YXW8595");
        Operador[8]= new Operador("Rodrigo Perez",22.275605, -97.874894 ,"IKU8506");
        Operador[9]= new Operador("Carlos Paez", 22.267258, -97.861137,"POY9887");
        Operador[10]= new Operador("Ramiro Flores",22.323185, -97.879787 ,"RTX7532");
        Operador[11]= new Operador("Angel Cruz", 22.265464, -97.855021,"TRJ7583");
        Operador[12]= new Operador("David Lara", 22.215452, -97.857364,"HJC7586");
        Operador[13]= new Operador("Cristobal Herrera", 22.247451, -97.855072,"YXT8596");
        Operador[14]= new Operador("Miguel Garcia",22.243166, -97.877342 ,"YUX8584");
        Operador[15]= new Operador("Leonardo Medina",22.247357, -97.836962 ,"RYX8523");
        Operador[16]= new Operador("Nicolas Chavez",22.261910, -97.875439 ,"XRC8521");
        Operador[17]= new Operador("Joel Duran", 22.229416, -97.859534,"HXC9502");
        Operador[18]= new Operador("Rodolfo Ortega",22.282946, -97.848703 ,"ICX8489");
        Operador[19]= new Operador("Jesus Ortiz",22.317877, -97.868644 ,"XJC7595");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
        double Lat =mMap.getMyLocation().getLatitude();
        double Long = mMap.getMyLocation().getLongitude();

        Punto Punto = new Punto(Lat,Long);

        LatLng Pos = new LatLng(Lat,Long);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Pos)
                .zoom(10)
                .build();

        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(camUpd3);

        mMap.addMarker(new MarkerOptions().position(new LatLng(Operador[0].getLat(),Operador[0].getLong())).title(Operador[0].getNombre()));

        for(int i=0;i<Operador.length;i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(Operador[i].getLat(),Operador[i].getLong())).title(Operador[i].getNombre()));
            double res = haversine(Operador[i].getLat(),Operador[i].getLong(), Punto.latitud, Punto.longitud);
            if (res>mayor){
                mayor=res;
            }
            if (res<menor){
                menor=res;
                aux = i;

            }
        }

        Toast.makeText(MConfirmacion.this, "TAXI MAS CERCANO "+Operador[aux].Nombre+"" , Toast.LENGTH_SHORT).show();
/*
            for (int i = 0; i < list.size(); i++) {
            //System.out.println(list.get(i).latitud+","+list.get(i).longitud);
             // System.out.println(nombres[i]);
            double res = haversine(list.get(i).latitud, list.get(i).longitud, Punto.latitud, Punto.longitud);
            // double res = haversine(Puntos[i].latitud, Puntos[i].longitud, Punto.latitud, Punto.longitud);
            System.out.println(i+1+ " -- " +res);
            if (res>mayor){
                mayor=res;
            }
            if (res<menor){
                menor=res;
                aux = i;
                //
            }
                }
*/
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
