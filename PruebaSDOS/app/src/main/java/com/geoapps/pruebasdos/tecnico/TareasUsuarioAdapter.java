package com.geoapps.pruebasdos.tecnico;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;
import com.geoapps.pruebasdos.administrador.TareasAdapter;
import com.geoapps.pruebasdos.administrador.VerTareaActivity;

import models.Tarea;

/**
 * Created by geovanni on 21/03/17.
 */

public class TareasUsuarioAdapter extends RecyclerView.Adapter<TareasUsuarioAdapter.ViewHolder> {

    public static Tarea[] tareas;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayout;
        public TextView nombreTextView;
        public TextView descripcionTextView;
        public Button completarButton;

        public ViewHolder(LinearLayout l)
        {
            super(l);
            linearLayout = l;
            nombreTextView = (TextView) l.findViewById(R.id.tv_nombre_adapter);
            descripcionTextView = (TextView) l.findViewById(R.id.tv_descripcion_adapter);
            completarButton = (Button) l.findViewById(R.id.btn_completar_tarea);

            completarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(MyApp.dataManager.bdm.actualizarTarea(tareas[getAdapterPosition()].getId(), true)) {
                        completarButton.setBackgroundColor(Color.GRAY);
                        completarButton.setText("Completada");
                    }
                }
            });
        }
    }

    public TareasUsuarioAdapter(Tarea[] tareas) {
        this.tareas = tareas;
    }

    @Override
    public TareasUsuarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tarea_usuario, parent, false);
        TareasUsuarioAdapter.ViewHolder vh = new TareasUsuarioAdapter.ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TareasUsuarioAdapter.ViewHolder holder, int position) {
        holder.nombreTextView.setText(tareas[position].getNombre());
        holder.descripcionTextView.setText(tareas[position].getDescripcion());
        if(tareas[position].isCompletada()) {
            holder.completarButton.setBackgroundColor(Color.GRAY);
            holder.completarButton.setText("Completada");
        }
    }

    @Override
    public int getItemCount() {
        return tareas.length;
    }
}
