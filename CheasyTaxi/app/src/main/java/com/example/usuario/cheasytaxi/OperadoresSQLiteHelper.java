package com.example.usuario.cheasytaxi;

/**
 * Created by Usuario on 23/11/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OperadoresSQLiteHelper extends SQLiteOpenHelper {
    public static int contador = 0;
    String Nombre = "nombre";
    String Unidad = "Unidad";
    String Matricula = "Matricula";
    String Color = "Color";
    double Latitud = 22.345;
    double Longitud = 99.56;
    String Usuario = "Usuario";
    String Contraseña = "Pass";

    String sqlCreate = "CREATE TABLE Operadores (codigo INTEGER PRIMARY KEY," +
                                                "nombre TEXT," +
                                                "unidad TEXT," +
                                                "matricula TEXT," +
                                                "color TEXT," +
                                                "latitud REAL, longitud REAL, usuario TEXT, contraseña TEXT)";


    String Insert = "Insert into Operadores(nombre,unidad,matricula,color,latitud,longitud,usuario, contraseña)" +
            "values("+Nombre+","+Unidad+","+Matricula+","+Color+","+Latitud+","+Longitud+","+Usuario+","+Contraseña+")";

    public OperadoresSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
       // db.execSQL(Insert);
        contador = 1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Operadores");
        db.execSQL(sqlCreate);
       // db.execSQL(Insert);
        contador = 2;
    }
}


