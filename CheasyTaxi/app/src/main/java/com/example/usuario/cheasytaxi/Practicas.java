package com.example.usuario.cheasytaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Practicas extends AppCompatActivity {
    private Button btnAlarm,btnPlot, btnProximidad, btnVoz, btnSensores,btnSockets,btnTouch,btnTabs,btnGrid,btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicas);

        this.btnAlarm = (Button) this.findViewById(R.id.btnAlarm);

        this.btnPlot = (Button) this.findViewById(R.id.btnPlot);
        this.btnProximidad = (Button) this.findViewById(R.id.btnProximidad);
        this.btnSensores = (Button) this.findViewById(R.id.btnSensores);
        this.btnSockets = (Button) this.findViewById(R.id.btnSockets);
        this.btnTouch = (Button) this.findViewById(R.id.btnTouch);
        this.btnVoz = (Button) this.findViewById(R.id.btnUsoVoz);
        this.btnTabs = (Button) this.findViewById(R.id.btnTabs);
        this.btnGrid = (Button) this.findViewById(R.id.btnGrid);
        this.btnList = (Button) this.findViewById(R.id.btnList);

        this.btnVoz.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Map.class);
                startActivity(intent);

            }
        });
        this.btnGrid.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Gridview.class);
                startActivity(intent);

            }
        });

        this.btnList.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Listview.class);
                startActivity(intent);

            }
        });

        this.btnTouch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Touch.class);
                startActivity(intent);

            }
        });
        this.btnSensores.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Sensores.class);
                startActivity(intent);

            }
        });

        this.btnProximidad.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Proximidad.class);
                startActivity(intent);

            }
        });

        this.btnAlarm.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Alarma.class);
                startActivity(intent);

            }
        });

        this.btnPlot.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Graficas.class);
                startActivity(intent);

            }
        });

        this.btnSockets.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Cliente.class);
                startActivity(intent);

            }
        });
        this.btnTabs.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Practicas.this, Tabs.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practicas, menu);
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
