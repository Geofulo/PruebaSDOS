package com.geoapps.pruebasdos.administrador;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;

import java.io.Serializable;
import java.util.List;

import models.Tarea;
import models.Usuario;

/**
 * Created by geovanni on 18/03/17.
 */

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {

    public static Tarea[] tareas;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayout;
        public TextView nombreTextView;
        public TextView encargadoTextView;

        public ViewHolder(LinearLayout l)
        {
            super(l);
            linearLayout = l;
            nombreTextView = (TextView) l.findViewById(R.id.tv_nombre_adapter);
            encargadoTextView = (TextView) l.findViewById(R.id.tv_encargado_adapter);

            linearLayout.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                Intent i = new Intent(v.getContext().getApplicationContext(), VerTareaActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("tarea", tareas[getAdapterPosition()]);
                v.getContext().getApplicationContext().startActivity(i);
                }
            });
        }
    }

    public TareasAdapter(Tarea[] tareas) {
        this.tareas = tareas;
    }

    @Override
    public TareasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tarea, parent, false);
        TareasAdapter.ViewHolder vh = new TareasAdapter.ViewHolder((LinearLayout) v);

        return vh;
    }

    @Override
    public void onBindViewHolder(TareasAdapter.ViewHolder holder, int position) {
        holder.nombreTextView.setText(tareas[position].getNombre());
        holder.encargadoTextView.setText(tareas[position].getEncargado().getFullname());
    }

    @Override
    public int getItemCount() {
        return tareas.length;
    }

}
