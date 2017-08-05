package com.example.usuario.cheasytaxi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;

public class MapsActivity extends FragmentActivity {
    private Button btnConfirmar;
    TextView text, fondo;
    String Ubicacion,aux ;
    double latitud, longitud;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    ArrayList<LatLng> markerPoints;
    String nombrec;
   // / int id;
    Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle bundle = this.getIntent().getExtras();
        nombrec= bundle.getString("NOMBREC");

        markerPoints = new ArrayList<LatLng>();
        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setVisibility(View.INVISIBLE);
        fondo = (TextView) findViewById(R.id.fondo);
        fondo.setVisibility(View.INVISIBLE);
        text = (TextView) findViewById(R.id.textView);
                setUpMapIfNeeded();
//
        mMap.setOnMapClickListener(new OnMapClickListener() {
        @Override
        public void onMapClick(LatLng point) {

            // Already two locations
            if(markerPoints.size()>1){
                markerPoints.clear();
                mMap.clear();
            }
            markerPoints.add(new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude())); // Adding new item to the ArrayList

            // Adding new item to the ArrayList
            markerPoints.add(point);

            // Creating MarkerOptions
            MarkerOptions options = new MarkerOptions();

            // Setting the position of the marker
            options.position(point);

            if(markerPoints.size()==1){
                options.icon(BitmapDescriptorFactory.fromFile("@drawable/icon"));
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            }else if(markerPoints.size()==2){

                options.icon(BitmapDescriptorFactory.fromFile("@drawable/icon2"));
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

            // Add new marker to the Google Map Android API V2
            mMap.addMarker(options);

            // Checks, whether start and end locations are captured
            if(markerPoints.size() >= 2){
                LatLng origin = markerPoints.get(0);
                LatLng dest = markerPoints.get(1);
                final int R = 6371; // Radious of the earth

                Double latDistance = toRad(dest.latitude-origin.latitude);
                Double lonDistance = toRad(dest.longitude-origin.longitude);
                Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                        Math.cos(toRad(origin.latitude)) * Math.cos(toRad(dest.latitude)) *
                                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                Double distance = R * c;

             /*   PolylineOptions line = new PolylineOptions().add(new LatLng(origin.latitude, origin.longitude),
                        new LatLng(dest.latitude,dest.longitude))
                        .width(10).color(Color.BLUE);

                mMap.addPolyline(line);
                double tarifa = distance * 8.74;
                Toast.makeText(MapsActivity.this,"Distancia Aprox: "+distance+ "\n Con una Tarifa de $ "+tarifa,Toast.LENGTH_LONG).show();
*/
                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();

                downloadTask.execute(url);
            }
            }
 //

    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

 private String getDirectionsUrl(LatLng origin,LatLng dest){

     String str_origin = "origin="+origin.latitude+","+origin.longitude;

     String str_dest = "destination="+dest.latitude+","+dest.longitude;

     String sensor = "sensor=false";

     String parameters = str_origin+"&"+str_dest+"&"+sensor;

     String output = "json";

     String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
           e.printStackTrace();
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }



    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){    // Get distance from the list
                        distance = (String)point.get("distance");

                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");

                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }
                    lineOptions.addAll(points);
                    lineOptions.width(10);
                lineOptions.color(Color.RED);
            }


            String Distancia[] = distance.split("km");
           final double distancia = Double.parseDouble(Distancia[0]);
            String Duracion[] = duration.split("mins");
            final double duracion = Double.parseDouble(Duracion[0]);
            final double tarifa = distancia * 8.74;

            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setRoundingMode(RoundingMode.DOWN);
            NumberFormat numberFormat2 = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            numberFormat.setRoundingMode(RoundingMode.DOWN);
            String sduracion = numberFormat2.format(duracion);
            String starifa = numberFormat.format(tarifa);

            //text.setText("Distancia: "+ distancia + " kilómetros\nDuración: "+numberFormat2.format(duracion)+ " minutos\nTarifa: $"+numberFormat.format(tarifa));
            mMap.addPolyline(lineOptions);

            if (tarifa != 0){

                new AlertDialog.Builder(MapsActivity.this)
                        .setTitle("Confirmación")
                        .setMessage("Distancia: "+ distancia + " kilómetros \nDuración: "+sduracion+ " minutos\nTarifa: $"+starifa+
                                "\n¿Desea confirmar su solicitud?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MapsActivity.this, Confirmacion.class);
                                Bundle b = new Bundle();
                                b.putDouble("LATITUD",mMap.getMyLocation().getLatitude());
                                b.putDouble("LONGITUD",mMap.getMyLocation().getLongitude());
                                b.putString("NOMBREC",nombrec);
                                b.putString("DISTANCIA", "" + distancia);
                                b.putString("DURACION", "" + duracion);
                                b.putString("TARIFA",""+tarifa);
                                intent.putExtras(b);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_mapmode)
                        .show();
               // btnConfirmar.setVisibility(View.VISIBLE);
              //  fondo.setVisibility(View.VISIBLE);
            }



            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0)
                {

                  //  dialogo.show(fragmentManager, "tagAlerta");
                   /* Intent intent = new Intent(MapsActivity.this, Confirmacion.class);
                    Bundle b = new Bundle();
                    b.putString("DISTANCIA",""+distancia);
                    b.putString("DURACION",""+duracion);
                    b.putString("TARIFA",""+tarifa);
                    intent.putExtras(b);
                    startActivity(intent);
                    */
                }
            });

        }
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

        LatLng Madero = new LatLng(22.254369, -97.848645);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Madero)
                .zoom(13)
                .build();

        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(camUpd3);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            public void onMapLongClick(LatLng point) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        MapsActivity.this, "Click Largo\n" + "Lat: " + point.latitude + "\n" + "Lng: " + point.longitude + "\n"
                                + "X: " + coord.x + " - Y: " + coord.y, Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).title("Marker"));
                mMap.setTrafficEnabled(true);
             }
         });



    }
}
