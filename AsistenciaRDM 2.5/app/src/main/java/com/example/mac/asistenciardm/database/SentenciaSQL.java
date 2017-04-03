package com.example.mac.asistenciardm.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Evento;
import com.example.mac.asistenciardm.modelos.Usuarios;

import java.util.ArrayList;

/**
 * Created by MAC on 17/03/17.
 */

public class SentenciaSQL {
    private ManageOpenHelper conexion;

    public SentenciaSQL(Context context) {
        //Iniciamos la conexión a la bd
        conexion = new ManageOpenHelper(context);
    }

    public ArrayList<Combo> obtenerUsuariosActivos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_evento where id_evento = '1'", null);
        ArrayList<Combo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                lista.add(new Combo(cursor.getInt(cursor.getColumnIndex("id_evento")),cursor.getString(cursor.getColumnIndex("evento"))));
            } while (cursor.moveToNext());
        }
        return lista;
    }
    public ArrayList<Combo> obtenerUsuarios() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_evento", null);
        ArrayList<Combo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                lista.add(new Combo(cursor.getInt(cursor.getColumnIndex("id_evento")),cursor.getString(cursor.getColumnIndex("evento"))));
            } while (cursor.moveToNext());
        }
        return lista;
    }

public Evento datosEvento(int idEvento){
    SQLiteDatabase db = conexion.getReadableDatabase();
    Cursor cursor = db.rawQuery("select e.id_evento, e.evento, e.fecha, e.alcance, e.descripcion, ev.estadoEvento" +
            "from tb_evento e, tb_estadoEvento ev" +
            "where e.id_estadoEvento = ev.id_estadoEvento and e.id_evento ='"+ idEvento +"' limit 1", null);
    Evento evento = null;
    if (cursor.moveToFirst()) {
        do {
            evento = new Evento();
            evento.setId(cursor.getInt(cursor.getColumnIndex("id_evento")));
            evento.setEvento(cursor.getString(cursor.getColumnIndex("evento")));
            evento.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
            evento.setAlcance(cursor.getString(cursor.getColumnIndex("alcance")));
            evento.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
            evento.setEstadoEvento(cursor.getString(cursor.getColumnIndex("estadoEvento")));
        }while (cursor.moveToNext());
    }
    return evento;
}

    public Usuarios obtenerUsuarioClave(String username) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_usuario where username='"+ username +"' limit 1", null);

        Usuarios usuarios =null;
        if (cursor.moveToFirst()) {
            do {
                usuarios = new Usuarios();
                usuarios.setId_usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")));
                usuarios.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                usuarios.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                usuarios.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                usuarios.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
                usuarios.setId_tipoUsuario(cursor.getInt(cursor.getColumnIndex("id_tipoUsuario")));
            } while (cursor.moveToNext());
        }
        return usuarios;
    }
}
