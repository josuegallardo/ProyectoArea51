package com.example.mac.asistenciardm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Combo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventoActivity extends AppCompatActivity {

 //   @BindView(R.id.textView)
   // TextView textView;
    @BindView(R.id.spSeleccionEvento)
    Spinner spSeleccionEvento;
    @BindView(R.id.btOkEvento)
    Button btOkEvento;
    @BindView(R.id.usuario)
    TextView usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        ButterKnife.bind(this);
        setTitle("Seleccionar evento");
        //Intent intent = getIntent();
        int aidiUsuario = getIntent().getIntExtra("idUsuario", -1);
        String rvusuario = getIntent().getStringExtra("usuario");
        usuario.setText(rvusuario);
        if (aidiUsuario == 1){
            btOkEvento.setEnabled(false);
        }
        SentenciaSQL sentenciaSQL = new SentenciaSQL(EventoActivity.this);
        //Debo hacer un FOR
        ArrayList<Combo> datoss  = new ArrayList<>();
        datoss = sentenciaSQL.obtenerUsuarios();
        ArrayList<String> usuarios = new ArrayList<>();
        for (Combo item:datoss ){
            usuarios.add(item.getTexto());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(EventoActivity.this, R.layout.support_simple_spinner_dropdown_item, usuarios);
        spSeleccionEvento.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.btOkEvento)
    public void onClick() {
        Intent intent = new Intent(EventoActivity.this, MainAsistenciaActivity.class);
        startActivity(intent);
    }
}
