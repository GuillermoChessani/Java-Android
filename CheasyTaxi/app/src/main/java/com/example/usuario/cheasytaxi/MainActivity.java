package com.example.usuario.cheasytaxi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnInicioSesion ;
        Usuario usuario;
    private Button btnRegistro;
    private EditText txtNombre;
    private EditText txtPassword;
    private Button btnAcerca;
    String NUsuario;
    String NPass;
    int id;
    private boolean valido = false;
  // String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnInicioSesion = (Button) this.findViewById(R.id.btnSesion);
        this.btnRegistro = (Button) this.findViewById(R.id.btnRegistro);
        this.txtNombre = (EditText) this.findViewById(R.id.txtNombre);
        this.txtPassword = (EditText) this.findViewById(R.id.txtPassword);
        this.btnAcerca = (Button) this.findViewById(R.id.btnAcerca);


        this.btnAcerca.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Acerca.class);
                startActivity(intent);
            }
        });

        this.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
     this.btnInicioSesion.setOnClickListener(new View.OnClickListener() {


                    public void onClick(View arg0)
                    {

                        usuario = new Usuario();
                     //  Hilo Hilo = new Hilo();
                     //  Hilo.execute(0,50);
                       // Hilo.doInBackground();


                       Intent intent = new Intent(MainActivity.this, PerfildeUsuario.class);
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

public class Hilo extends AsyncTask<Integer,String, Long>{
   String NUsuario,NPass;

    @Override
    protected void onPreExecute(){
        String NUsuario = txtNombre.getText().toString();
        String  NPass = txtPassword.getText().toString();
    }

    @Override
    protected Long doInBackground(Integer... params) {
        String sql;
        String[] registro = new String[2];
        List ids = null,usuarios=null,contraseñas=null;
       // "SELECT * FROM Usuarios WHERE correo = '" + correo + "' AND contrasena = SHA1('" + pass + "');"
       // sql="SELECT * from clientes where usuario ='"+NUsuario+"' AND contraseña ='"+NPass+"'";

        try {
            Conexion con = new Conexion();
            Connection reg = con.conexion();
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from clientes where usuario ='"+NUsuario+"' AND contraseña ='" + NPass + "'");

            if (rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("contraseña"));
                // usuario.setCiudad(rs.getString("ciudad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getInt("telefono"));
                //rs = st.executeQuery("SELECT * FROM Notas WHERE usuarioId = " + usuario.getUsuarioId() + ";");
               valido = true;
            }

                if (valido == true) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Invalidas", Toast.LENGTH_SHORT).show();
                }

                publishProgress("");
                // Terminar lo antes posible si se ha llamadao al cancel() del asynctask


    }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0L;
    }

    @Override
    protected void onProgressUpdate(String... progress){
        Toast.makeText(MainActivity.this, progress[0], Toast.LENGTH_LONG).show();
        //tvResultado.append(nombre+" "+apellidos+"\n");
    }

    @Override
    protected void onPostExecute(Long result){

    }
}

}

