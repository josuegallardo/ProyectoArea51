package com.example.mac.asistenciardm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Evento;
import com.example.mac.asistenciardm.modelos.Usuarios;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);


        setContentView(R.layout.activity_evento);
        ButterKnife.bind(this);
        setTitle("Seleccionar evento");
        //Intent intent = getIntent();
        int aidiUsuario = getIntent().getIntExtra("idUsuario", -1);
        String rvusuario = getIntent().getStringExtra("usuario");

        if (aidiUsuario != 1) {
            rbActivo.setEnabled(false);
            rbTodos.setEnabled(false);
        }
        //SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
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
    int idEvento = 0;

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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick(R.id.btOkEvento)
    public void onClick() {
        Intent intent = new Intent(EventoActivity.this, MainAsistenciaActivity.class);
        String usuario1 = getIntent().getStringExtra("usuario");
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
        startActivity(intent);

    }
}
