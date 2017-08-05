package com.example.usuario.cheasytaxi;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class Map extends AppCompatActivity {
    private Button btnPedirTaxi;
    private Button btnCircular;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1;
    private FrameLayout Fondo;
    private TextView texto, direccion;
    String inicio ="Usted quiso decir: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.texto = (TextView) this.findViewById(R.id.texto);
        this.direccion = (TextView) this.findViewById(R.id.direccion);
        this.Fondo = (FrameLayout) this.findViewById(R.id.Fondo);
        this.btnPedirTaxi = (Button) this.findViewById(R.id.btnPedirTaxi);

                this.btnPedirTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognitionActivity();
                btnPedirTaxi.setEnabled(false);
               }
        });


    }

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"¿A donde deseas ir?");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK){

            ArrayList<String> matches = data.getStringArrayListExtra
                    (RecognizerIntent.EXTRA_RESULTS);
            String [ ] palabras = matches.get(0).toString().split(" ");
            String res="";
            for(int ij = 0; ij < palabras.length; ++ij){
                res = res + " " + palabras[ij];
            }
           // Toast.makeText(this, "Usted quiso decir: " +res+" ?", Toast.LENGTH_SHORT).show();
            texto.setText(inicio);
            direccion.setText(res+" ?");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
