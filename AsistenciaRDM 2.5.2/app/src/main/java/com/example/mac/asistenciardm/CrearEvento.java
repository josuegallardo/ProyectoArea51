package com.example.mac.asistenciardm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mac.asistenciardm.database.SentenciaSQL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrearEvento extends AppCompatActivity {

    @BindView(R.id.etNombreEvento)
    EditText etNombreEvento;
    @BindView(R.id.etFecha)
    EditText etFecha;
    @BindView(R.id.etAlcance)
    EditText etAlcance;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);
        ButterKnife.bind(this);
        setTitle("Crear Evento");
    }

    @OnClick(R.id.btOkEvento)
    public void onViewClicked() {
        SentenciaSQL sentenciaSQL = new SentenciaSQL(CrearEvento.this);
        String evento = etNombreEvento.getText().toString();
        String fecha = etFecha.getText().toString();
        String alcance = etAlcance.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        sentenciaSQL.registrarEvento(evento, fecha, alcance, descripcion);
        Toast.makeText(this, "Se registro el evento", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CrearEvento.this, EventoActivity.class);
        startActivity(intent);



    }
}
