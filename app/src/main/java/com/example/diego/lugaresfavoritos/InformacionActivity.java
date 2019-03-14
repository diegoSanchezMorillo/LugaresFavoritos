package com.example.diego.lugaresfavoritos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.lugaresfavoritos.dao.LugaresDAO;
import com.example.diego.lugaresfavoritos.dao.LugaresSQLiteHelper;
import com.example.diego.lugaresfavoritos.model.Categorias;
import com.example.diego.lugaresfavoritos.model.Lugares;

public class InformacionActivity extends AppCompatActivity {

    TextView nombre,categoriaview,longitud,latitud,valoracion,comentarios;
    RatingBar puntuacion;
    LugaresSQLiteHelper con;
    LugaresDAO proceso;
    int id;
    Lugares lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Declaramos los elementos de la vista
        nombre = (TextView) findViewById(R.id.txtNombreMostrado);
        categoriaview = (TextView) findViewById(R.id.txtCategoriaMostrado);
        longitud = (TextView) findViewById(R.id.txtLongitudMostrado);
        latitud = (TextView) findViewById(R.id.txtLatitudMostrado);
        comentarios = (TextView) findViewById(R.id.txtComentariosMostrado);
        puntuacion = (RatingBar) findViewById(R.id.ratingValoracion);
        //Recogemos el dato pasado por el intent
        Intent recibir = getIntent();
        id = recibir.getIntExtra("id",0);
        //Creamos la conexión
        con = new LugaresSQLiteHelper(this);
        //Abrimos la base de datos con permisos de lectura
        SQLiteDatabase bd = con.getReadableDatabase();
        //Pasamos la sentencia sql y recorremos toda la tabla que tenga el id que le pasamos
        Cursor fila = bd.rawQuery("select * from lugares WHERE id="+id, null);
        lugar = new Lugares();
        //Guardamos los datos recogidos
        if(fila.moveToFirst()){
            do{
                lugar.setId(fila.getInt(0));
                lugar.setNombre(fila.getString(1));
                lugar.setId_categoria(fila.getInt(2));
                lugar.setLongitud(fila.getDouble(3));
                lugar.setLatitud(fila.getDouble(4));
                lugar.setValoracion(fila.getInt(5));
                lugar.setComentarios(fila.getString(6));

            }while(fila.moveToNext());
        }
        //Mostramos los datos en los TextView
        nombre.setText(lugar.getNombre());
        longitud.setText(String.valueOf(lugar.getLongitud()));
        latitud.setText(String.valueOf(lugar.getLatitud()));
        puntuacion.setRating(lugar.getValoracion());
        comentarios.setText(lugar.getComentarios());

        //Pasamos la sentencia sql y recorremos toda la tabla que tenga el id que le pasamos
         fila = bd.rawQuery("select * from categorias WHERE id="+lugar.getId_categoria(),null);
        Categorias categoria = new Categorias();
        if (fila.moveToFirst()){
            do {
                categoria.setId(fila.getInt(0));
                categoria.setNombre(fila.getString(1));
            }while(fila.moveToNext());
        }
        //Mostramos los datos en los TextView
        categoriaview.setText(categoria.getNombre());
        bd.close();

    }


    @Override
    //Creamos el menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secundario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Cuando se clickea el botón modificar del menú superior
        if (id == R.id.modificar) {
            //Abrimos la Activity para modificar y le pasamos el parámetro id
            Intent i = new Intent(InformacionActivity.this,ModificarActivity.class);
            i.putExtra("id",lugar.getId());
            startActivity(i);

            return true;
        }else if (id==R.id.eliminar){
            //Creamos la conexión
            LugaresSQLiteHelper conexion = new LugaresSQLiteHelper(this);
            //Seleccionamos que elemento queremos borrar por el id
            int id_borrar = lugar.getId();
            //Llamamos al método para eliminar y le pasamos los parámetros
            eliminaLugar(id_borrar,conexion);
            //Mostramos un toast de que todo ha salido correctamente
            Toast toast1 =
                    Toast.makeText(getApplicationContext(), R.string.eliminado, Toast.LENGTH_SHORT);
            toast1.show();
            //Abrimos el Activity principal
            Intent intent = new Intent(InformacionActivity.this,PrincipalActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void eliminaLugar(int id,LugaresSQLiteHelper con){

        //Abrimos la base de datos en modo escritura
        SQLiteDatabase bd = con.getWritableDatabase();
        //Ejecutamos la sentencia sql
        bd.execSQL("DELETE FROM lugares WHERE id="+id);
        bd.close();
    }


}
