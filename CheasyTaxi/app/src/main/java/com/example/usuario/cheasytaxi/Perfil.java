package com.example.usuario.cheasytaxi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {
   private EditText Text;
   private Button button;
    private TextView txtView;
    OperadoresSQLiteHelper usuarios = new OperadoresSQLiteHelper(this, "operadordb", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        this.button = (Button) this.findViewById(R.id.button);
        this.txtView = (TextView) this.findViewById(R.id.txtCodigo);
               this.button.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                SQLiteDatabase db = usuarios.getReadableDatabase();

                Cursor c = db.rawQuery("SELECT nombre FROM Operadores; ", null);

                if( c.getCount() > 0 ) {
                    c.moveToFirst();
                    do {
                        String nombre = c.getString(1);
                       /* int codigo = c.getInt(0);
                        String nombre = c.getString(1);
                        String usuario = c.getString(2);
                        String password = c.getString(3);
                        String ciudad = c.getString(4);
                        String email = c.getString(5);
                        int telefono  = c.getInt(6);


                        txtView.setText(txtView.getText() + "\n Código: " + codigo + " \n Nombre: " + nombre +"\n Usuario: " +usuario+"\n Password: "+password+" \n Ciudad: " +ciudad+" \n E-Mail: "+email+" \n Teléfono: "+telefono+"\n \n");
*/ txtView.setText( "Nombre"+nombre);
                    } while (c.moveToNext());
                }
                db.close();

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
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
}
