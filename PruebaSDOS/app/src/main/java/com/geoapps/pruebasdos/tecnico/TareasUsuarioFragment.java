package com.geoapps.pruebasdos.tecnico;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;
import com.geoapps.pruebasdos.administrador.TareasAdapter;


public class TareasUsuarioFragment extends Fragment {

    RecyclerView recyclerView;
    TareasUsuarioAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public TareasUsuarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tareas_usuario, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_tareas_usuario);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TareasUsuarioAdapter(MyApp.dataManager.bdm.obtenerTareasByUsuarioId(getContext().getSharedPreferences("INITIAL_DATA", 0).getString("ID_USER", "0")));
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        return rootView;
    }

}
