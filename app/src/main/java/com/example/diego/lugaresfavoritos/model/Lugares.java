package com.example.diego.lugaresfavoritos.model;

public class Lugares {
    int id;
    String nombre;
    Double longitud;
    Double latitud;
    int valoracion;
    String comentarios;
    int id_categoria;

    public Lugares() {

    }

    public Lugares(int id,String nombre,int id_categoria, Double longitud, Double latitud, int valoracion, String comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.longitud = longitud;
        this.latitud = latitud;
        this.valoracion = valoracion;
        this.comentarios = comentarios;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
