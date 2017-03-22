package com.geoapps.pruebasdos.administrador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geoapps.pruebasdos.R;

import models.Usuario;

/**
 * Created by geovanni on 17/03/17.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> {

    private Usuario[] usuarios;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayout;
        public TextView fullnameTextView;
        public TextView usernameTextView;
        public TextView tipoUsuarioTextView;

        public ViewHolder(LinearLayout l)
        {
            super(l);
            linearLayout = l;
            fullnameTextView = (TextView) l.findViewById(R.id.tv_fullname_adapter);
            usernameTextView = (TextView) l.findViewById(R.id.tv_username_adapter);
            tipoUsuarioTextView = (TextView) l.findViewById(R.id.tv_tipousuario_adapter);
        }
    }

    public UsuariosAdapter(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UsuariosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_usuario, parent, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fullnameTextView.setText(usuarios[position].getFullname());
        holder.usernameTextView.setText(usuarios[position].getUsername());
        holder.tipoUsuarioTextView.setText(usuarios[position].getTipoUsuario().getNombre());
    }

    @Override
    public int getItemCount() {
        return usuarios.length;
    }
}
