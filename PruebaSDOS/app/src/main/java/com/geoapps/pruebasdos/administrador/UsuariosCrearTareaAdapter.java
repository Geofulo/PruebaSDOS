package com.geoapps.pruebasdos.administrador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geoapps.pruebasdos.R;

import models.Tarea;
import models.Usuario;

/**
 * Created by geovanni on 20/03/17.
 */

public class UsuariosCrearTareaAdapter extends ArrayAdapter<Usuario>
{
    Usuario[] usuarios;

    public UsuariosCrearTareaAdapter(Context context, int resource, Usuario[] usuarios) {
        super(context, resource);
        this.usuarios = usuarios;
    }

    public int getCount(){
        return usuarios.length;
    }

    public Usuario getItem(int position){
        return usuarios[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(this.getContext());
        label.setText(usuarios[position].getFullname() + " - " + usuarios[position].getTareas().length + " tareas");
        label.setPadding(20, 20, 20, 20);
        //label.setHeight(40);
        return label;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //LayoutInflater inflater = this.getin
        TextView label = new TextView(this.getContext());
        //label.setTextColor(Color.BLACK);
        label.setText(usuarios[position].getFullname() + " - " + usuarios[position].getTareas().length + " tareas");
        label.setPadding(20, 20, 20, 20);
        //label.setHeight(40);
        return label;
    }

}
