package com.example.usuario.cheasytaxi;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Proximidad extends AppCompatActivity implements SensorEventListener {
    RelativeLayout fondo;
    Button activador, desactivador;
    Sensor s;
    SensorManager sensorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximidad);
        fondo = (RelativeLayout) findViewById(R.id.fondo);
        activador = (Button) findViewById(R.id.Activar);
        desactivador = (Button) findViewById(R.id.Desactivar);

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        activador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sensorM.registerListener(Proximidad.this, s, SensorManager.SENSOR_DELAY_NORMAL);
                activador.setText("Activado");
            }
        });

        desactivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorM.unregisterListener(Proximidad.this);
                desactivador.setText("Desactivado");

                fondo.setBackgroundColor(Color.GRAY);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float valor = Float.parseFloat(String.valueOf(event.values[0]));
        if (valor <= 10)
            fondo.setBackgroundColor(Color.BLACK);
        else
            fondo.setBackgroundColor(Color.RED);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
