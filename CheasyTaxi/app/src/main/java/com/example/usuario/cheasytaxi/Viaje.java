package com.example.usuario.cheasytaxi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Calendar;

public class Viaje extends AppCompatActivity {
    private static final String url = "jdbc:mysql://sql5c75c.carrierzone.com/cheasytaxi_ceitamcom1802685";
    private static final String user = "ceitamcom1802685";
    private static final String password = "chessani93";
    static int id =2;
    Button btnTerminar;
TextView txtCliente, txtOperador, txtMatricula, txtUnidad, txtModelo, txtColor, txtFecha, txtDistancia, txtPrecio, txtDuracion;
    String cliente ="Guillermo Chessani", operador, matricula, unidad, modelo, color, fecha;
    double distancia, precio, duracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje);
        Bundle bundle = this.getIntent().getExtras();
        distancia= Double.parseDouble(bundle.getString("DISTANCIA"));
        duracion =Double.parseDouble(bundle.getString("DURACION"));
        precio = Double.parseDouble(bundle.getString("TARIFA"));

        btnTerminar = (Button) findViewById(R.id.btnTerminar);

        txtCliente = (TextView) findViewById(R.id.VtxtNombreC);
        txtOperador = (TextView) findViewById(R.id.VtxtNombreO);
        txtMatricula = (TextView) findViewById(R.id.VtxtMatricula);
        txtUnidad = (TextView) findViewById(R.id.VtxtUnidad);
        txtModelo = (TextView) findViewById(R.id.VtxtModelo);
        txtColor = (TextView) findViewById(R.id.VtxtColor);
        txtFecha = (TextView) findViewById(R.id.VtxtFecha);
        txtDistancia = (TextView) findViewById(R.id.VtxtDistancia);
        txtPrecio = (TextView) findViewById(R.id.VtxtPrecio);
        txtDuracion = (TextView) findViewById(R.id.VtxtDuracion);


        //cal.get(cal.DATE)+"-"+cal.get(cal.MONTH)+"-"+;


        MiHilo h = new MiHilo();

        h.execute(0, 50);

        this.btnTerminar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Viaje.this)
                        .setTitle("Confirmación")
                        .setMessage("¿Desea registrar el viaje?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Insert i = new Insert();

                                i.execute(0, 50);

                                Intent intent = new Intent(Viaje.this, Calificar.class);
                                                                startActivity(intent);

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
               }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viaje, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MiHilo extends AsyncTask<Integer, String, Long> {
        @Override
        protected void onPreExecute(){
           txtCliente.setText("Cliente: ");
        }

        @Override
        protected Long doInBackground(Integer... params) {
            int count=0;

            try {
                // The newInstance() call is a work around for some broken Java implementations
                //con esto nos aseguramos de que se crean los recursos estaticos necesarios

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement();
                String sql="SELECT * FROM clientes,corridas,operadores,unidades where corridas.idcorrida="+id;
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    // nombrec = rs.getString(1);
                    //en cada iteracion tenemos un resultado concreto
                    //si la query es un "select una_columna, otra_columna from ..." recuerda que rs tiene funtxtCliente.setText(rs.getString("clientes.nombre"));
                   // cliente=rs.getString("clientes.nombre");
                    operador = "Pedro Méndez";
                    matricula = "GTK4723";
                    unidad =(rs.getString("unidades.id"));
                    color =(rs.getString("unidades.color"));
                    modelo =(rs.getString("unidades.modelo"));
                    //fecha =(""+rs.getDate("corridas.fecha"));
                    Calendar cal= Calendar.getInstance();
                    String dia=cal.get(cal.YEAR)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.DATE);
                    String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
                    fecha = dia+ " "+hora;
                   // distancia = (distancia);
                    //precio =("$"+precio);
                   // duracion =(""+duracion+" minutos");
                    //  System.out.println("Usuario: " + rs.getString("usuario"));
                    //  System.out.println("Contraseña: " + rs.getString("email"));
                    // rs.getDouble(1) //devuelve otra_columna, haciendo cast a Double
                    //etc, que reciben el indice de los valor, y nos devueven el valor haciendo el cast correspondiente.


                    // Terminar lo antes posible si se ha llamadao al cancel() del asynctask

                    if (isCancelled())
                        break;
                }
            }

            catch (Exception e) {
                Log.e("MYSQL", "ERROR " + e.toString());
                e.printStackTrace();
            }

            return 0L;
        }

        @Override
        protected void onProgressUpdate(String... progress){

            // tvResultado.append("Nombre: "+nombrec+"\nUsuario: "+usuario+"\nE-mail: "+email+"\nTeléfono: "+telefono+"\n");


        }

        @Override
        protected void onPostExecute(Long result){
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setRoundingMode(RoundingMode.DOWN);
            NumberFormat numberFormat2 = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(0);
            numberFormat.setRoundingMode(RoundingMode.DOWN);
            String sduracion = numberFormat2.format(duracion);
            String starifa = numberFormat.format(precio);

            txtCliente.append(cliente);
            txtOperador.append(" "+operador);
            txtMatricula.append(" " + matricula);
            txtUnidad.append(" " + unidad);
            txtModelo.append(" " + modelo);
            txtColor.append(" " + color);
            txtFecha.append(" " + fecha);
            txtDistancia.append(" "+distancia+" kilómetros");
            txtPrecio.append("$ " + starifa);
            txtDuracion.append(" " + sduracion + " minutos");


        }
    }

    class Insert extends AsyncTask<Integer, String, Long> {
        int idc =1;
        int ido = 3;

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Long doInBackground(Integer... params) {
            int count=0;
            String sql="INSERT INTO corridas(idcliente,idoperador,fecha, distancia, precio, duracion)"
                    + "VALUES(?,?,?,?,?,?)";
            try {


                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                Calendar cal= Calendar.getInstance();
                String dia=cal.get(cal.YEAR)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.DATE);
                String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
                fecha = dia+ " "+hora;

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, idc);
                pst.setInt(2, ido);
                pst.setString(3, fecha);
                pst.setDouble(4, distancia);
                pst.setDouble(5, precio);
                pst.setDouble(6, duracion);
                pst.executeUpdate();


                publishProgress("" + ++count);
            }

            catch (Exception e) {
                Log.e("MYSQL", "ERROR " + e.toString());
                e.printStackTrace();
            }

            return 0L;
        }

        @Override
        protected void onProgressUpdate(String... progress){


        }

        @Override
        protected void onPostExecute(Long result){
            Toast.makeText(Viaje.this, "Corrida Registrado ", Toast.LENGTH_SHORT).show();
        }
    }




}
