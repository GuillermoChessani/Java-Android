package com.example.usuario.cheasytaxi;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ThemedSpinnerAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registro extends AppCompatActivity {
    private Spinner Ciudades;
    private Button btnRegistrar;
    private TextView txtNombre;
    private TextView txtUsuario;
    private TextView txtPass1;
    private TextView txtPass2;
    private RadioButton rbtnMasculino;
    private RadioButton rbtnFemenino;
    private TextView txtEmail;
    private TextView txtTelefono;
    private String ciudadseleccionada;
    final UsuariosSQLiteHelper usuarios = new UsuariosSQLiteHelper(this, "usuariodb", null, 1);

    private static final String url = "jdbc:mysql://sql5c75c.carrierzone.com/cheasytaxi_ceitamcom1802685";
    private static final String user = "ceitamcom1802685";
    private static final String password = "chessani93";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


            txtNombre = (TextView) findViewById(R.id.txtNombre);
            txtUsuario = (TextView) findViewById(R.id.txtUsuario);
            txtPass1 = (TextView) findViewById(R.id.txtPass1);
            txtPass2 = (TextView) findViewById(R.id.txtPass2);
            rbtnMasculino = (RadioButton) findViewById(R.id.radioButton1);
            rbtnFemenino = (RadioButton) findViewById(R.id.radioButton2);
            Ciudades = (Spinner)findViewById(R.id.spinner);
            txtEmail = (TextView) findViewById(R.id.txtEmail);
            txtTelefono = (TextView) findViewById(R.id.txtTelefono);
            btnRegistrar = (Button) findViewById(R.id.BtnRegistro);
        Usuario usuario = new Usuario();
        final String[] Ciudadeses =
                new String[]{"Tampico","Madero","Altamira"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, Ciudadeses);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        Ciudades.setAdapter(adaptador);
        Ciudades.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

                        Toast.makeText(Registro.this, "Seleccionado: " +
                                parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        ciudadseleccionada = parent.getItemAtPosition(position).toString();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });




        this.btnRegistrar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {


            MiHilo h = new MiHilo();
            Usuario usuario = new Usuario();
            h.execute(0, 50);





            Intent intent = new Intent(Registro.this, MainActivity.class);
            startActivity(intent);



               }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class MiHilo extends AsyncTask<Integer, String, Long> {
        String Nombre = txtNombre.getText().toString();
        String User=(txtUsuario.getText().toString());
        String Password=(txtPass1.getText().toString());
      ////  String Password2= ((txtPass2.getText().toString()));
        String Ciudad=(ciudadseleccionada);
        String Email=(txtEmail.getText().toString());
        int Telefono=(Integer.parseInt(txtTelefono.getText().toString()));


        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Long doInBackground(Integer... params) {
            int count=0;
            String sql="INSERT INTO clientes(nombre,usuario,contraseña,ciudad, email, telefono)"
                    + "VALUES(?,?,?,?,?,?)";
            try {


                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);


                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, Nombre);
                pst.setString(2, User);
                pst.setString(3, Password);
                pst.setString(4, Ciudad);
                pst.setString(5, Email);
                pst.setInt(6, Telefono);
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
            Toast.makeText(Registro.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
        }
    }
}
