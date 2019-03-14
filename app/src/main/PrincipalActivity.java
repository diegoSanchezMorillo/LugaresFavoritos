package com.example.diego.lugaresfavoritos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diego.lugaresfavoritos.dao.Adaptador;
import com.example.diego.lugaresfavoritos.dao.LugaresDAO;
import com.example.diego.lugaresfavoritos.dao.LugaresSQLiteHelper;
import com.example.diego.lugaresfavoritos.model.Lugares;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    Spinner spinnerCategoria;
    LugaresSQLiteHelper con;
    Button botonMaps;
    int categoria;
    RecyclerView recyclerViewLugares;
    List<Lugares> lugares = new ArrayList<>();
    Adaptador adaptador;
    Context context = this;
    LugaresDAO proceso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Al pulsar sobre el botón llamamos a la Activity CreacionActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PrincipalActivity.this,CreacionActivity.class);
                startActivity(i);
            }
        });
        //Creamos la conexión
        con = new LugaresSQLiteHelper(this);
        spinnerCategoria  = (Spinner) findViewById(R.id.spinnerCategorias);
        recyclerViewLugares = findViewById(R.id.recyclerLista);
        botonMaps = (Button) findViewById(R.id.btnMaps);
        //Creamos un arrayList para guardar las categorias
        ArrayList<String> categorias = new ArrayList<>();
        //Abrimos la base de datos con permisos de lectura
        SQLiteDatabase bd = con.getReadableDatabase();
        //Pasamos la sentencia sql y recorremos toda la tabla guardando las categorias
        Cursor fila = bd.rawQuery("select * from categorias", null);
        if(fila.moveToFirst()){
            do{
                categorias.add( fila.getString(1));
            }while(fila.moveToNext());
        }
        //Cerramos la conexión
        bd.close();

        //Mostramos el arrayList de categorias en el spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(dataAdapter);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                categoria = pos+1;
                proceso = new LugaresDAO();
                //Mostramos los lugares mediante el adaptador en el RecyclerView
                recyclerViewLugares.setLayoutManager(new LinearLayoutManager(context));
                adaptador = new Adaptador(proceso.mostrarLugares(con,categoria,lugares));
                recyclerViewLugares.setAdapter(adaptador);
                //Cuando pulsamos sobre algún elemento del RecyclerView nos llama a otra Activity y le pasa un parámetro
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PrincipalActivity.this,InformacionActivity.class);
                        i.putExtra("id",lugares.get(recyclerViewLugares.getChildAdapterPosition(v)).getId());
                        startActivity(i);

                    }
                });

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Al hacer click sobre el boton llamamos a la Activity MapsActivity
        botonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrincipalActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
