package com.example.diego.lugaresfavoritos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LugaresSQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lugaresfavoritos";

    public LugaresSQLiteHelper(Context contexto) {
        super(contexto, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos las tablas
        db.execSQL("CREATE TABLE " + Esquema.tablaLugar.NOMBRE_TABLA_LUGARES + " ("

                + Esquema.tablaLugar.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Esquema.tablaLugar.NOMBRE + " TEXT NOT NULL,"
                + Esquema.tablaLugar.ID_CATEGORIA + " INTEGER NOT NULL,"
                + Esquema.tablaLugar.LONGITUD + " DOUBLE ,"
                + Esquema.tablaLugar.LATITUD + " DOUBLE ,"
                + Esquema.tablaLugar.VALORACION + " INTEGER,"
                + Esquema.tablaLugar.COMENTARIOS + " TEXT,"
                + "UNIQUE (" + Esquema.tablaLugar.ID + "))");

        db.execSQL("CREATE TABLE " + Esquema.tablaLugar.NOMBRE_TABLA_CATEGORIAS + " ("

                + Esquema.tablaLugar.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Esquema.tablaLugar.NOMBRE + " TEXT NOT NULL,"
                + "UNIQUE (" + Esquema.tablaLugar.ID + "))");
        //Añadimos las categorias
        db.execSQL("INSERT INTO categorias(id, nombre) VALUES(1,'Parques')");
        db.execSQL("INSERT INTO categorias(id, nombre) VALUES(2,'Monumentos')");
        db.execSQL("INSERT INTO categorias(id, nombre) VALUES(3,'Paraje')");
        db.execSQL("INSERT INTO categorias(id, nombre) VALUES(4,'Bares')");
        db.execSQL("INSERT INTO categorias(id, nombre) VALUES(5,'Otros...')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE lugares");
        db.execSQL("DROP TABLE categorias");

    }




}
