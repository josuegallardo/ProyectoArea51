package com.example.mac.asistenciardm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Evento;
import com.example.mac.asistenciardm.modelos.Usuarios;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.appcompat.R.styleable.MenuItem;

public class EventoActivity extends AppCompatActivity {

    //   @BindView(R.id.textView)
    // TextView textView;
    //@BindView(R.id.spSeleccionEvento)
    //Spinner spSeleccionEvento;


    @BindView(R.id.rbActivo)
    RadioButton rbActivo;
    @BindView(R.id.rbTodos)
    RadioButton rbTodos;
    @BindView(R.id.rgEventos)
    RadioGroup rgEventos;
    @BindView(R.id.spSeleccionEvento)
    Spinner spSeleccionEvento;
    @BindView(R.id.etEvento)
    TextView etEvento;
    @BindView(R.id.etFecha)
    TextView etFecha;
    @BindView(R.id.etAlcacne)
    TextView etAlcacne;
    @BindView(R.id.etDescripcion)
    TextView etDescripcion;
    @BindView(R.id.etEstadoEvento)
    TextView etEstadoEvento;
    @BindView(R.id.btOkEvento)
    Button btOkEvento;

public static String estadoo;
    public static int idEvento = 0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_asistencia, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
        setContentView(R.layout.activity_evento);
        ButterKnife.bind(this);
        setTitle("Seleccionar evento");
        //Intent intent = getIntent();
        //int aidiUsuario = getIntent().getIntExtra("idUsuario", -1);
        int aidiUsuario = MainActivity.idUsuarioo;
        String rvusuario = getIntent().getStringExtra("usuario");

        if (aidiUsuario != 1) {
            rbActivo.setEnabled(false);
            rbTodos.setEnabled(false);
        }
        datoss.clear();
        datoss = sentenciaSQL.obtenerUsuariosActivos();
        ArrayList<String> usuarios = new ArrayList<>();
        for (Combo item : datoss) {
            usuarios.add(item.getTexto());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(EventoActivity.this, R.layout.support_simple_spinner_dropdown_item, usuarios);
        spSeleccionEvento.setAdapter(arrayAdapter);
    }

    private
    ArrayList<Combo> datoss = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        rbActivo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbActivo.isChecked() == true) {
                    SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
                    datoss.clear();
                    datoss = sentenciaSQL.obtenerUsuariosActivos();
                    ArrayList<String> usuarios = new ArrayList<>();
                    for (Combo item : datoss) {
                        usuarios.add(item.getTexto());
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(EventoActivity.this, R.layout.support_simple_spinner_dropdown_item, usuarios);
                    spSeleccionEvento.setAdapter(arrayAdapter);
                } else if (rbTodos.isChecked() == true) {
                    SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
                    datoss.clear();
                    datoss = sentenciaSQL.obtenerUsuarios();
                    ArrayList<String> usuarios = new ArrayList<>();
                    for (Combo item : datoss) {
                        usuarios.add(item.getTexto());
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(EventoActivity.this, R.layout.support_simple_spinner_dropdown_item, usuarios);
                    spSeleccionEvento.setAdapter(arrayAdapter);
                }


            }
        });



        spSeleccionEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEvento = datoss.get(position).getId();
                SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
                Evento evento = sentenciaSQL.datosEvento(idEvento);
                if (evento != null) {
                    String eventoo = sentenciaSQL.datosEvento(idEvento).getEvento();
                    String fecha = sentenciaSQL.datosEvento(idEvento).getFecha();
                    String alcance = sentenciaSQL.datosEvento(idEvento).getAlcance();
                    String descripcion = sentenciaSQL.datosEvento(idEvento).getDescripcion();
                    String estado = sentenciaSQL.datosEvento(idEvento).getEstadoEvento();
                    etEvento.setText(eventoo);
                    etFecha.setText(fecha);
                    etAlcacne.setText(alcance);
                    etDescripcion.setText(descripcion);
                    etEstadoEvento.setText(estado);
                    estadoo = estado;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.actionNuevoEvento:
              Intent intent =new Intent(EventoActivity.this, CrearEvento.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }


    @OnClick(R.id.btOkEvento)
    public void onClick() {
        Intent intent = new Intent(EventoActivity.this, MainAsistenciaActivity.class);
        //String usuario1 = getIntent().getStringExtra("usuario");
        String usuario1;
        usuario1=MainActivity.usuariooo;
        SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
        Usuarios usuarioss = sentenciaSQL.obtenerUsuarioClave(usuario1);
        String password = sentenciaSQL.obtenerUsuarioClave(usuario1).getPassword();
        String nombre = sentenciaSQL.obtenerUsuarioClave(usuario1).getNombre();
        String apellidos = sentenciaSQL.obtenerUsuarioClave(usuario1).getApellidos();
        int tipoUsuario = sentenciaSQL.obtenerUsuarioClave(usuario1).getId_tipoUsuario();
        int idUsuario = sentenciaSQL.obtenerUsuarioClave(usuario1).getId_usuario();
        intent.putExtra("usuario", usuario1);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        intent.putExtra("tipoUsuario", tipoUsuario);
        intent.putExtra("idUsuario", idUsuario);
        intent.putExtra("idEvento", idEvento);
        intent.putExtra("estadoo", estadoo);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
}
