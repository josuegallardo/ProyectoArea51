package com.example.mac.asistenciardm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etUsuarioLoguin)
    EditText etUsuarioLoguin;
    @BindView(R.id.etClaveLogin)
    EditText etClaveLogin;
    @BindView(R.id.btIniciarSesion)
    Button btIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btIniciarSesion)
    public void onClick() {
        Intent intent=new Intent(MainActivity.this, EventoActivity.class);
        startActivity(intent);
    }
}
