package com.geoapps.pruebasdos.tecnico;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.geoapps.pruebasdos.LoginActivity;
import com.geoapps.pruebasdos.R;
import com.geoapps.pruebasdos.administrador.AdministradorPageAdapter;
import com.geoapps.pruebasdos.administrador.TareasFragment;
import com.geoapps.pruebasdos.administrador.UsuariosFragment;
import com.geoapps.pruebasdos.webservices.WebServicesFragment;

public class TecnicoActivity extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);

        this.setTitle("Técnico");

        toolbar = (Toolbar) findViewById(R.id.toolbar_tecnico);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.vp_tecnico);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_tecnico);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        AdministradorPageAdapter adapter = new AdministradorPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TareasUsuarioFragment(), "Tareas");
        adapter.addFragment(new WebServicesFragment(), "WebService");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_administrador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_signout:
                getSharedPreferences("INITIAL_DATA", 0).edit().putString("ID_USER", "0").commit();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
