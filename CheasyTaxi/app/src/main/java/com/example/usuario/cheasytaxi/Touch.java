package com.example.usuario.cheasytaxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

public class Touch extends AppCompatActivity {
    private float scaleFactor = 1.0f;
    ScaleGestureDetector sgd;

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        texto = (TextView) findViewById(R.id.texto);

        sgd = new ScaleGestureDetector(this, new ScaleListener() );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_touch, menu);
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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        sgd.onTouchEvent(event);

        String accion=null;


        int x = (int) event.getX();
        int y = (int) event.getY();

        int a = event.getActionMasked();
        int index = event.getActionIndex();

        switch (a) {
            case MotionEvent.ACTION_UP:
                accion = "ACTION_UP";
                break;

            case MotionEvent.ACTION_DOWN:
                accion = "ACTION_DOWN";
                break;

            case MotionEvent.ACTION_MOVE:
                accion = "ACTION_MOVE";
                break;

            case MotionEvent.ACTION_POINTER_UP:
                accion = "ACTION_POINTER_UP";
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                accion = "ACTION_POINTER_DOWN";
                break;

        }

        String touchStatus = "ACCION: " + accion + " - INDEX:" + index + " - X: " + x + " Y:" + y;
        texto.setText(texto.getText() + "\n" + touchStatus);


        return true;
    }

    public void onClick(View v){
        texto.setText("");

    }

    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            // don't let the object get too small or too large.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20*scaleFactor);

            return true;
        }
    }
}


