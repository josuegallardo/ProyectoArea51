package com.example.mac.asistenciardm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.asistenciardm.fragmentosMenu.EstadisticaFragment;
import com.example.mac.asistenciardm.fragmentosMenu.ModificarFragmento;
import com.example.mac.asistenciardm.fragmentosMenu.VerificarFragment;

import butterknife.ButterKnife;

public class MainAsistenciaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int idEventoo;

    //@BindView(R.id.fgFragmento)
    //android.widget.fragment fgFragmento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_asistencia);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       idEventoo = getIntent().getIntExtra("idEvento", -1);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        // public void onClick(View view) {
        //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //         .setAction("Action", null).show();
        //}
        //});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view=navigationView.getHeaderView(0);
        TextView tvUsuario=(TextView) view.findViewById(R.id.nombreUserMenu);
        TextView tvTipoUsuario=(TextView) view.findViewById(R.id.tipoUsuario);
        String rvusuario = getIntent().getStringExtra("nombre");
        String rvTipoUsurio = getIntent().getStringExtra("apellidos");
        tvUsuario.setText(rvusuario);
        tvTipoUsuario.setText(rvTipoUsurio);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        VerificarFragment verificarFragment = new VerificarFragment();
        fragmentTransaction.replace(R.id.fgFragmento, verificarFragment);
        //fragmentTransaction.add(R.id.fgFragmento, verificarFragment);
    fragmentTransaction.isAddToBackStackAllowed();
        fragmentTransaction.commit();
        setTitle("Registro");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_asistencia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        VerificarFragment verificarFragment = new VerificarFragment();
        ModificarFragmento modificarFragmento = new ModificarFragmento();
        EstadisticaFragment estadisticaFragment = new EstadisticaFragment();


        if (id == R.id.nav_registrar) {
            //Intent  intent = new Intent(MainAsistenciaActivity.this, VerificarFragment.class);
            //intent.putExtra("idEvento", idEvento);
            //startActivity(intent);
            int idEvento = getIntent().getIntExtra("idEvento", -1);
            Bundle bundle = new Bundle();
            bundle.putInt("idEvento",idEvento);
            VerificarFragment verificarFragment1 = new VerificarFragment();
            verificarFragment1.setArguments(bundle);

            fragmentTransaction.replace(R.id.fgFragmento, verificarFragment);
            fragmentTransaction.commit();
            setTitle("Registro");

            Toast.makeText(this, "Registrar", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_modificar) {
            fragmentTransaction.replace(R.id.fgFragmento, modificarFragmento);
            fragmentTransaction.commit();
            setTitle("Modificar");
            Toast.makeText(this, "Modificar", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_estadistica) {
            fragmentTransaction.replace(R.id.fgFragmento, estadisticaFragment);
            fragmentTransaction.commit();
            setTitle("Estadistica");
             Toast.makeText(this, "Estadistica", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_informacionContacto) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
