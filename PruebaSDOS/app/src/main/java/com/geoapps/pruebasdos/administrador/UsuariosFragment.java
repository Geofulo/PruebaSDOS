package com.geoapps.pruebasdos.administrador;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;

import data.DataManager;

public class UsuariosFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public UsuariosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_usuarios, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_usuarios);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UsuariosAdapter(MyApp.dataManager.bdm.obtenerUsuariosExceptAdministrador());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
