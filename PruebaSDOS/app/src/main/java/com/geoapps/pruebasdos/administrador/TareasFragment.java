package com.geoapps.pruebasdos.administrador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;

import data.DataManager;


public class TareasFragment extends Fragment {

    RecyclerView recyclerView;
    TareasAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public TareasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tareas, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_tareas);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TareasAdapter(MyApp.dataManager.bdm.obtenerTareas());
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_tareas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_crear_tarea:
                getContext().startActivity(new Intent(getContext(), CrearTareaActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
