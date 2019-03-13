package com.example.diego.lugaresfavoritos.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.diego.lugaresfavoritos.InformacionActivity;
import com.example.diego.lugaresfavoritos.PrincipalActivity;
import com.example.diego.lugaresfavoritos.model.Lugares;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class LugaresDAO extends AppCompatActivity {


    List<Lugares> lugares = new ArrayList<>();
    public void insertarLugar(Lugares lugar,LugaresSQLiteHelper con){

        //Abrimos la base de datos 'favoritos' en modo escritura

        SQLiteDatabase bd = con.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("nombre",lugar.getNombre());
        registro.put("id_categoria",lugar.getId_categoria());
        registro.put("longitud",lugar.getLongitud());
        registro.put("latitud",lugar.getLatitud());
        registro.put("valoracion",lugar.getValoracion());
        registro.put("comentarios",lugar.getComentarios());
        bd.insert("lugares",null,registro);
        bd.close();
    }

    public void actualizaLugar(Lugares lugar,LugaresSQLiteHelper con){

        //Abrimos la base de datos 'favoritos' en modo escritura

        SQLiteDatabase bd = con.getWritableDatabase();


        bd.execSQL("UPDATE lugares SET nombre="+"'"+lugar.getNombre()+"'"+","+
                "id_categoria="+lugar.getId_categoria()+","+
                "longitud="+"'"+lugar.getLongitud()+"'"+","+
                "latitud="+"'"+lugar.getLatitud()+"'"+","+
                "valoracion="+lugar.getValoracion()+","+
                "comentarios="+"'"+lugar.getComentarios()+"'"+" "+
                "WHERE id="+lugar.getId()
        );
        bd.close();
    }

    public void eliminaLugar(int id,LugaresSQLiteHelper con){

        //Abrimos la base de datos 'favoritos' en modo escritura

        SQLiteDatabase bd = con.getWritableDatabase();


        bd.execSQL("DELETE FROM lugares WHERE id="+id);
        bd.close();
    }

    public List<Lugares> mostrarLugares(LugaresSQLiteHelper con, int id, List<Lugares> lugares){

        SQLiteDatabase db = con.getReadableDatabase();
        Cursor cursor2 = db.rawQuery("SELECT * FROM lugares WHERE id_categoria="+id,null);


        if (cursor2.moveToFirst()){
            do{
                lugares.add(new Lugares(cursor2.getInt(0),cursor2.getString(1),cursor2.getInt(2),
                        cursor2.getDouble(3),cursor2.getDouble(4),cursor2.getInt(5),cursor2.getString(6)));
            }while (cursor2.moveToNext());
        }
        return lugares;

    }

    public List<Lugares> mostrarLugaresMarcadores(LugaresSQLiteHelper con){

        SQLiteDatabase db = con.getReadableDatabase();
        Cursor cursor2 = db.rawQuery("SELECT * FROM lugares",null);


        if (cursor2.moveToFirst()){
            do{
                lugares.add(new Lugares(cursor2.getInt(0),cursor2.getString(1),cursor2.getInt(2),
                        cursor2.getDouble(3),cursor2.getDouble(4),cursor2.getInt(5),cursor2.getString(6)));
            }while (cursor2.moveToNext());
        }
        return lugares;

    }
}
