package com.geoapps.pruebasdos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geoapps.pruebasdos.administrador.AdministradorActivity;
import com.geoapps.pruebasdos.tecnico.TecnicoActivity;

import data.DataManager;
import models.*;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        usernameEditText = (EditText) this.findViewById(R.id.et_username);
        passwordEditText = (EditText) this.findViewById(R.id.et_password);
        Button ingresarButton = (Button) this.findViewById(R.id.btn_ingresar);

        ingresarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!username.isEmpty() && !password.isEmpty())
                {
                    Usuario usuario = MyApp.dataManager.bdm.obtenerUsuarioByUsername(username);
                    if (usuario != null)
                    {
                        if (usuario.getPassword().equals(password))
                        {
                            getSharedPreferences("INITIAL_DATA", 0).edit().putString("ID_USER", usuario.getId()).commit();
                            if (usuario.getTipoUsuario().getNombre().equals("Administrador"))
                            {
                                openActivity(AdministradorActivity.class);
                            } else {
                                openActivity(TecnicoActivity.class);
                            }
                        } else
                            showToast("Username y/o contraseña incorrectos.");
                    }
                    else
                    {
                        showToast("Usuario no encontrado");
                    }
                }
                else
                    showToast("Ingresa tu username y contraseña para ingresar.");
            }
        });

        SharedPreferences preferences = getSharedPreferences("INITIAL_DATA", 0);
        String id_user = preferences.getString("ID_USER", "0");

        if(!id_user.equals("0"))
        {
            if(MyApp.dataManager.bdm.obtenerUsuario(id_user).getTipoUsuario().getId().equals("1"))
                startActivity(new Intent(this, AdministradorActivity.class));
            else
                startActivity(new Intent(this, TecnicoActivity.class));
        }
    }

    void openActivity(java.lang.Class activity)
    {
        Intent i = new Intent(this, activity);
        this.startActivity(i);
    }

    void showToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
