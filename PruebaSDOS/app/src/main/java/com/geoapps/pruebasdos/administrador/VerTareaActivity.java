package com.geoapps.pruebasdos.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;

import models.Tarea;

public class VerTareaActivity extends AppCompatActivity {

    TextView nombreTextView;
    TextView descripcionTextView;
    TextView asignadoTextView;

    Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tarea);

        tarea = (Tarea) getIntent().getSerializableExtra("tarea");

        this.setTitle("Tarea \"" + tarea.getNombre() + " \"");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ver_tarea);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreTextView = (TextView) findViewById(R.id.tv_value_nombre_ver_tarea);
        descripcionTextView = (TextView) findViewById(R.id.tv_value_descripcion_ver_tarea);
        asignadoTextView = (TextView) findViewById(R.id.tv_value_asignado_ver_tarea);

        nombreTextView.setText(tarea.getNombre());
        descripcionTextView.setText(tarea.getDescripcion());
        asignadoTextView.setText(tarea.getEncargado().getFullname());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_ver_tarea, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_eliminar:
                if(MyApp.dataManager.bdm.eliminarTarea(tarea.getUsuario_id()))
                {
                    Intent i = getSupportParentActivityIntent();
                    navigateUpTo(i);
                }
                else
                    Toast.makeText(this, "Ocurrió un error al intentar eliminar la tarea", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
