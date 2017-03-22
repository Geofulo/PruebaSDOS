package com.geoapps.pruebasdos.webservices;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;
import com.geoapps.pruebasdos.administrador.UsuariosAdapter;


public class WebServicesFragment extends Fragment
{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public WebServicesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_web_services, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_webservice);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WebServicesAdapter(MyApp.dataManager.bdm.obtenerItemsWS());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
