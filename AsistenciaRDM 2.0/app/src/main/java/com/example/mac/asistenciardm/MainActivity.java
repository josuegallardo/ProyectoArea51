package com.example.mac.asistenciardm;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Usuarios;

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

    //@BindView(R.id.lvLista)
    //ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btIniciarSesion)
    public void onClick() {

        String usuario = etUsuarioLoguin.getText().toString();
        String clave = etClaveLogin.getText().toString();
        if (usuario.trim().length() == 0) {
            etUsuarioLoguin.setError("Ingrese un usuario");
            return;
        } else {
            etUsuarioLoguin.setError(null);
        }
        if (clave.trim().length() == 0) {
            etClaveLogin.setError("Ingrese la clave");
        } else {
            etClaveLogin.setError(null);
        }

        SentenciaSQL sentenciaSQL = new SentenciaSQL(MainActivity.this);
        Usuarios usuarios = sentenciaSQL.obtenerUsuarioClave3(usuario);
        if (usuarios != null) {
            String username = usuarios.getUsername();

            String password = sentenciaSQL.obtenerUsuarioClave3(usuario).getPassword();
            String nombre = sentenciaSQL.obtenerUsuarioClave3(usuario).getNombre();
            String apellidos = sentenciaSQL.obtenerUsuarioClave3(usuario).getApellidos();
            int tipoUsuario = sentenciaSQL.obtenerUsuarioClave3(usuario).getId_tipoUsuario();
            int idUsuario = sentenciaSQL.obtenerUsuarioClave3(usuario).getId_usuario();

            if (clave.equals(password)) {
                Toast.makeText(this, "Bienvenido: " + nombre + " " + apellidos, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EventoActivity.class);
                intent.putExtra(usuario, "username");
                startActivity(intent);

            } else {
                Toast.makeText(this, "Usuario o clave incorrecto", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Usuario o clave incorrecto", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


        //tvPassword.setText(sentenciaSQL.obtenerUsuarioClave3(usuario).getPassword());
        //UsuarioAdapter usuarioAdapter = new UsuarioAdapter(MainActivity.this, sentenciaSQL.obtenerUsuarioClave(usuario));
        //lvLista.setAdapter(usuarioAdapter);
        //tvPassword.setText(lvLista.getFirstVisiblePosition(usuarioAdapter));
        //tvPassword.setText(sentenciaSQL.obtenerUsuarioClave2(usuario));
    }
}
