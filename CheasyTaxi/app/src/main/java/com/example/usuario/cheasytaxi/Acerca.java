package com.example.usuario.cheasytaxi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Acerca extends AppCompatActivity {
    private Button btnMenu;
    private Switch aSwitch;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;

    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        aSwitch = (Switch) findViewById(R.id.switch1);
        layout = (RelativeLayout) findViewById(R.id.layout);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        btnMenu = (Button) findViewById(R.id.btnMenu);

        this.btnMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acerca.this, Practicas.class);
                startActivity(intent);
            }
        });

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()) {
                    Toast.makeText(Acerca.this, "Encenciendo las luces", Toast.LENGTH_SHORT).show();
                    layout.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                    textView2.setTextColor(Color.BLACK);
                    textView3.setTextColor(Color.BLACK);
                    textView4.setTextColor(Color.BLACK);
                    textView5.setTextColor(Color.BLACK);
                    textView6.setTextColor(Color.BLACK);
                    textView7.setTextColor(Color.BLACK);
                    textView8.setTextColor(Color.BLACK);
                    textView9.setTextColor(Color.BLACK);
                    textView10.setTextColor(Color.BLACK);
                    textView11.setTextColor(Color.BLACK);
                    textView12.setTextColor(Color.BLACK);
                    textView13.setTextColor(Color.BLACK);
                    textView14.setTextColor(Color.BLACK);
                }

                else {
                    Toast.makeText(Acerca.this, "Apagando las luces", Toast.LENGTH_SHORT).show();
                    layout.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    textView2.setTextColor(Color.WHITE);
                    textView3.setTextColor(Color.WHITE);
                    textView4.setTextColor(Color.WHITE);
                    textView5.setTextColor(Color.WHITE);
                    textView6.setTextColor(Color.WHITE);
                    textView7.setTextColor(Color.WHITE);
                    textView8.setTextColor(Color.WHITE);
                    textView9.setTextColor(Color.WHITE);
                    textView10.setTextColor(Color.WHITE);
                    textView11.setTextColor(Color.WHITE);
                    textView12.setTextColor(Color.WHITE);
                    textView13.setTextColor(Color.WHITE);
                    textView14.setTextColor(Color.WHITE);
                }

            }

        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acerca, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



