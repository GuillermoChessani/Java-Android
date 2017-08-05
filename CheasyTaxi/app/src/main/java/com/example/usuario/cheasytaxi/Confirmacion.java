package com.example.usuario.cheasytaxi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;

public class Confirmacion extends AppCompatActivity {
    private static final String url = "jdbc:mysql://sql5c75c.carrierzone.com/cheasytaxi_ceitamcom1802685";
    private static final String user = "ceitamcom1802685";
    private static final String password = "chessani93";
    String nombrec;
    private GoogleMap mMap;
    TextView txtOperador;
    TextView txtTaxista,txtMatricula,txtColor,txtModelo;
    Operador[] Operador = new Operador[20];
    double menor = 10000;
    double mayor = 0;
    int aux = 0;
    double Lat, Lon;
    Punto[] Puntos = new Punto[20];
    String[] nombre = new String[20];
    String[]Matriculas = new String[20];
    String[]Modelos = new String[20];
    String[]Colores = new String[20];
    String distancia, duracion, tarifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        Bundle bundle = this.getIntent().getExtras();
        distancia= bundle.getString("DISTANCIA");
        duracion =bundle.getString("DURACION");
        tarifa = bundle.getString("TARIFA");



        this.txtOperador = (TextView) findViewById(R.id.txtOperador);
        txtTaxista = (TextView) findViewById(R.id.txtTaxista);
        txtMatricula = (TextView) findViewById(R.id.txtMatricula);
        txtModelo = (TextView) findViewById(R.id.txtModelo);
        txtColor = (TextView) findViewById(R.id.txtColor);

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        Lat = bundle.getDouble("LATITUD");
        Lon = bundle.getDouble("LONGITUD");
        nombrec= bundle.getString("NOMBREC");

        //Operador[0]= new Operador("José Martinez",22.400504, -99.599749,"XCJ2299");
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

        ejecutar();

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void ejecutar(){
        MiHilo Hilo = new MiHilo();
        Hilo.execute(0,50);
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
        // double Lat =mMap.getMyLocation().getLatitude();
        // double Lon = mMap.getMyLocation().getLongitude();

        LatLng Pos = new LatLng(Lat, Lon);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Pos)
                .zoom(17)
                .build();


        Punto Punto = new Punto(Lat, Lon);

        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(camUpd3);
        //mMap.addMarker(new MarkerOptions().position(new LatLng(Lat,Lon)));

        for (int i = 0; i < Operador.length; i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(Operador[i].Lat, Operador[i].Long)).title(Operador[i].getNombre()));

        }

        // Toast.makeText(Confirmacion.this, "TAXI MAS CERCANO "+Operador[aux].Nombre, Toast.LENGTH_SHORT).show();
        // txtOperador.setText("Operador más cercano\nNombre: "+Operador[aux].Nombre+ "\nUnidad N° "+(aux+1)+"\nMatrícula: "+Operador[aux].getUnidad());

///  AQUI HACER METODO QUE LLAME A UN UPDATE Y SELECT (RESPECTIVAMENTE) para confirmar la posicion del taxi-cliente


        if (haversine(Lat, Lon, Operador[aux].Lat, Operador[aux].Long) <= .1) {
            Toast.makeText(Confirmacion.this, "EL TAXI HA LLEGADO", Toast.LENGTH_SHORT).show();
///
            Intent Intent = new Intent(Confirmacion.this, Viaje.class);
            Bundle b = new Bundle();
            b.putString("NOMBREC", nombrec);
            Intent.putExtras(b);
            startActivity(Intent);


        }
    }


    class MiHilo extends AsyncTask<Integer, String, Long> {
    @Override
    protected void onPreExecute() {
        txtOperador.setText("");
    }

    @Override
    protected Long doInBackground(Integer... params) {
        int count = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM operadores,unidades");

            while (rs.next()) {
                nombre[count] = rs.getString("nombre");
                Puntos[count] = new Punto(rs.getDouble("latitud"), rs.getDouble("longitud"));
                Matriculas[count]= rs.getString("matricula");
                Modelos[count]= rs.getString("modelo");
                Colores[count]= rs.getString("color");
                publishProgress("" + ++count);

                if (isCancelled())
                    break;
            }
        } catch (Exception e) {
            Log.e("MYSQL", "ERROR " + e.toString());
            e.printStackTrace();
        }

        return 0L;
    }

    @Override
    protected void onProgressUpdate(String... progress) {

    }

    @Override
    protected void onPostExecute(Long result) {
        double menor = 10000;
        double mayor = 0;
        int aux = 0;
        for (int i = 1; i < Puntos.length; i++) {
            // txtOperador.append(nombre[i] + ": " + Puntos[i].latitud + " , " + Puntos[i].longitud + "\n");

            Punto Punto = new Punto(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
            double res = haversine(Puntos[i].latitud, Puntos[i].longitud, Punto.latitud, Punto.longitud);
            if (res > mayor) {
                mayor = res;
            }
            if (res < menor) {
                menor = res;
                aux = i;
                //
            }
        }
        //txtOperador.append("\nEL TAXISTA MAS CERCANO ES: " + nombre[aux]);
        txtTaxista.append(nombre[aux]);
        txtMatricula.append(Matriculas[aux]);
        txtModelo.append(Modelos[aux]);
        txtColor.append(Colores[aux]);

        llegada();

}}
    public void llegada(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                new AlertDialog.Builder(Confirmacion.this)
                        .setTitle("Confirmación")
                        .setMessage("¿El taxi ha llegado?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Confirmacion.this, Viaje.class);
                                Bundle b = new Bundle();
                                b.putString("DISTANCIA",""+distancia);
                                b.putString("DURACION",""+duracion);
                                b.putString("TARIFA",""+tarifa);
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                llegada();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();

            }

        }, 30000);
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
    }}