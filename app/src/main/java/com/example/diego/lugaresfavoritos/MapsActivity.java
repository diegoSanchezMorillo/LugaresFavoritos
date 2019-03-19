package com.example.diego.lugaresfavoritos;


import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.example.diego.lugaresfavoritos.dao.LugaresDAO;
import com.example.diego.lugaresfavoritos.dao.LugaresSQLiteHelper;
import com.example.diego.lugaresfavoritos.model.Lugares;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LugaresDAO proceso;
    String nombre;
    double latitud,longitud;
    LugaresSQLiteHelper con;
    List<Lugares> lugares = new ArrayList<>() ;
    List<LatLng> marcadores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        con = new LugaresSQLiteHelper(this);
        
        


        for(int x=0;x<lugares.size();x++){
            nombre= lugares.get(1).getNombre();
            latitud = lugares.get(3).getLatitud();
            longitud = lugares.get(4).getLongitud();
            LatLng marcador = new LatLng (latitud,longitud);
            mMap.addMarker(new MarkerOptions().position(marcador).title(nombre));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador));

            mMap.animateCamera(CameraUpdateFactory.zoomIn());

        }

    }


}
