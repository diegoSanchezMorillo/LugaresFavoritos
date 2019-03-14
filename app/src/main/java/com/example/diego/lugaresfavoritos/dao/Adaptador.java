package com.example.diego.lugaresfavoritos.dao;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.diego.lugaresfavoritos.R;
import com.example.diego.lugaresfavoritos.model.Lugares;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private RatingBar puntuacion;
        public ViewHolder(View itemView){
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtNombreLista);
            puntuacion = (RatingBar) itemView.findViewById(R.id.ratingPuntuacion);
        }

    }

    public List<Lugares> lugaresList;

    public Adaptador(List<Lugares> lugaresList){this.lugaresList=lugaresList;};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lugares,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        holder.nombre.setText((CharSequence) lugaresList.get(position).getNombre());
        holder.puntuacion.setRating((float) lugaresList.get(position).getValoracion());
    }


    @Override
    public int getItemCount(){
        return lugaresList.size();
    }
}
