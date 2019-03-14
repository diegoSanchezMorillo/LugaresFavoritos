package com.example.diego.lugaresfavoritos.dao;

import android.provider.BaseColumns;

public class Esquema {

    public static abstract class tablaLugar implements BaseColumns{

        public static final String NOMBRE_TABLA_LUGARES = "lugares";
        public static final String NOMBRE_TABLA_CATEGORIAS = "categorias";
        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String ID_CATEGORIA = "id_categoria";
        public static final String LONGITUD = "longitud";
        public static final String LATITUD = "latitud";
        public static final String VALORACION = "valoracion";
        public static final String COMENTARIOS = "comentarios";
    }
}
