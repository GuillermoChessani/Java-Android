package com.example.usuario.cheasytaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Graficas extends AppCompatActivity {
Button btnSensorGrafica, btnGraficaPastel, btnGraficaDinamica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        btnGraficaDinamica = (Button) findViewById(R.id.btnDynamicChart);
        btnGraficaPastel= (Button) findViewById(R.id.btnPieChart);
        btnSensorGrafica = (Button) findViewById(R.id.btnSensorChart);

        this.btnGraficaPastel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Graficas.this, GraficaPastel.class);
                startActivity(intent);

            }
        });

        this.btnGraficaDinamica.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Graficas.this, DynamicChart.class);
                startActivity(intent);

            }
        });

        this.btnSensorGrafica.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Graficas.this, Grafica.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graficas, menu);
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
}
