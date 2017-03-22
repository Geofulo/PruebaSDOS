package com.geoapps.pruebasdos.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.geoapps.pruebasdos.MyApp;
import com.geoapps.pruebasdos.R;

import models.Usuario;

public class CrearTareaActivity extends AppCompatActivity {

    EditText nombreEditText;
    EditText descripcionEditText;

    Spinner usuariosSpinner;
    UsuariosCrearTareaAdapter adapter;

    Usuario usuarioSeleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        this.setTitle("Nueva tarea");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_crear_tarea);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreEditText = (EditText) findViewById(R.id.et_nombre_tarea);
        descripcionEditText = (EditText) findViewById(R.id.et_descripcion_tarea);
        usuariosSpinner = (Spinner) findViewById(R.id.spinner_usuarios);

        adapter = new UsuariosCrearTareaAdapter(this, R.layout.adapter_usuarios_crear_tarea, MyApp.dataManager.bdm.obtenerUsuariosExceptAdministrador());
        usuariosSpinner.setAdapter(adapter);
        usuariosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usuarioSeleccionado = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crear_tarea, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_guardar)
        {
            if (usuarioSeleccionado != null)
            {
                String nombre = nombreEditText.getText().toString();
                String descripcion = descripcionEditText.getText().toString();
                String usuario_id = usuarioSeleccionado.getId();
                if (!nombre.isEmpty() || !descripcion.isEmpty())
                {
                    MyApp.dataManager.bdm.agregarTarea(nombre, descripcion, usuario_id, false);
                    Intent i = getSupportParentActivityIntent();
                    navigateUpTo(i);
                }
                else
                    Toast.makeText(this, "Debes ingresar el nombre y la descripción de la tarea", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this, "Debes seleccionar un usuario", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
