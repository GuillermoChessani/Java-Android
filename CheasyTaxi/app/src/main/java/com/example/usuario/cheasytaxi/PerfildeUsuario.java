package com.example.usuario.cheasytaxi;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PerfildeUsuario extends ActionBarActivity {

    // TextView tvResultado;
    EditText txt,Enombrec,Eusuario,Eemail,Etelefono;
    Button btnMap;

    private static final String url = "jdbc:mysql://sql5c75c.carrierzone.com/cheasytaxi_ceitamcom1802685";
    private static final String user = "ceitamcom1802685";
    private static final String password = "chessani93";
    String[] nombrecs = new String[20];
    String nombrec,usuario,email;


    int telefono,id=1;
Button btnGuardar,btnEditar,btnConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilde_usuario);

        //tvResultado = (TextView) findViewById(R.id.tvResultado);
        txt = (EditText) findViewById(R.id.editText);
        Enombrec = (EditText) findViewById(R.id.editNombre);
        Eusuario = (EditText) findViewById(R.id.editUsuario);
        Eemail = (EditText) findViewById(R.id.editEmail);
        Etelefono = (EditText) findViewById(R.id.editTelefono);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnGuardar=(Button) findViewById(R.id.btnGuardar);
        btnConsulta=(Button) findViewById(R.id.btnConsulta);
        this.btnMap = (Button) this.findViewById(R.id.btnMapa);
        btnGuardar.setEnabled(false);
        btnConsulta.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);


        MiHilo h = new MiHilo();
        //  id = Integer.parseInt(txt.getText().toString());
        h.execute(0, 50);

        this.btnEditar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Enombrec.setTextColor(Color.BLACK);
                Enombrec.setEnabled(true);
                //Eusuario.setTextColor(Color.BLACK);
                Eusuario.setEnabled(true);
                Etelefono.setTextColor(Color.BLACK);
                Etelefono.setEnabled(true);
                Eemail.setTextColor(Color.BLACK);
                Eemail.setEnabled(true);
              btnEditar.setEnabled(false);
                btnGuardar.setEnabled(true);
            }
        });

        this.btnGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                MiHiloU update = new MiHiloU();
              //  id = Integer.parseInt(txt.getText().toString());
                update.execute(0, 50);

                Toast.makeText(PerfildeUsuario.this, "Información Actualizada", Toast.LENGTH_SHORT).show();
                btnEditar.setEnabled(true);
            }
        });

        this.btnMap.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(PerfildeUsuario.this, MapsActivity.class);
                Bundle b = new Bundle();
                b.putString("NOMBREC", nombrec);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick(View v) {

        MiHilo h = new MiHilo();
      //  id = Integer.parseInt(txt.getText().toString());
        h.execute(0,50);
    }

    class MiHilo extends AsyncTask<Integer, String, Long> {
        @Override
        protected void onPreExecute(){
            // tvResultado.setText("");
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
                ResultSet rs = st.executeQuery("SELECT * FROM clientes where id ="+id);
                while (rs.next()) {
                    // nombrec = rs.getString(1);
                    //en cada iteracion tenemos un resultado concreto
                    //si la query es un "select una_columna, otra_columna from ..." recuerda que rs tiene funciones tales como
                    nombrec = (rs.getString("nombre"));
                    usuario = (rs.getString("usuario"));
                    email = (rs.getString("email"));
                    telefono = rs.getInt("telefono");
                    //System.out.println("Nombre: " + rs.getString("nombrec"));
                    //  System.out.println("Usuario: " + rs.getString("usuario"));
                    //  System.out.println("Contraseña: " + rs.getString("email"));
                    // rs.getDouble(1) //devuelve otra_columna, haciendo cast a Double
                    //etc, que reciben el indice de los valor, y nos devueven el valor haciendo el cast correspondiente.

                    publishProgress("" + ++count);
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
            Enombrec.setText(nombrec);
            Eusuario.setText(usuario);
            Eemail.setText(email);
            Etelefono.setText(""+telefono);

            Enombrec.setTextColor(Color.BLACK);
            Enombrec.setEnabled(false);
            Eusuario.setTextColor(Color.BLACK);
            Eusuario.setEnabled(false);
            Etelefono.setTextColor(Color.BLACK);
            Etelefono.setEnabled(false);
            Eemail.setTextColor(Color.BLACK);
            Eemail.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Long result){

        }
    }

    class MiHiloU extends AsyncTask<Integer, String, Long> {
        String nombrea = Enombrec.getText().toString();
        String usuarioa = Eusuario.getText().toString();
        String emaila = Eemail.getText().toString();
        int telefonoa = Integer.parseInt(Etelefono.getText().toString());
        @Override
        protected void onPreExecute(){


        }

        @Override
        protected Long doInBackground(Integer... params) {
            int count=0;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                String sql ="UPDATE clientes Set nombre ='" + nombrea + "',usuario ='" + usuarioa + "',email ='" + emaila + "',telefono ='" + telefonoa + "' Where id =" + id;
                PreparedStatement pst = con.prepareStatement(sql);
                publishProgress("" + ++count);
                pst.executeUpdate();

            }


            catch (Exception e) {
                Log.e("MYSQL", "ERROR " + e.toString());
                e.printStackTrace();
            }

            return 0L;
        }

        @Override
        protected void onProgressUpdate(String... progress){

            Enombrec.setText(nombrea);
            Eusuario.setText(usuarioa);
            Eemail.setText(emaila);
            Etelefono.setText(""+telefonoa);




        }

        @Override
        protected void onPostExecute(Long result){
           // btnGuardar.setVisibility(View.GONE);
           // btnEditar.setVisibility(View.VISIBLE);
            btnGuardar.setEnabled(false);

            MiHilo h = new MiHilo();
         //   id = Integer.parseInt(txt.getText().toString());
            h.execute(0, 50);

           /* Enombrec.setText(nombrea);
            Eusuario.setText(usuarioa);
            Eemail.setText(emaila);
            Etelefono.setText(telefonoa);

            Enombrec.setEnabled(false);
            Eusuario.setEnabled(false);
            Eemail.setEnabled(false);
            Etelefono.setEnabled(false);*/
        }
    }


}
