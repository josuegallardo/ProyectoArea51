package com.example.mac.asistenciardm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mac.asistenciardm.EventoActivity;
import com.example.mac.asistenciardm.adapters.HijosAdapter;
import com.example.mac.asistenciardm.modelos.AsistentesEvento;
import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Evento;
import com.example.mac.asistenciardm.modelos.Hijos;
import com.example.mac.asistenciardm.modelos.Usuarios;

import java.lang.reflect.Array;
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

    public ArrayList<AsistentesEvento> listarFamiliasEvento(int idEvento) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select A.id_asistenciaEvento, E.evento, F.familia, EA.estadoAsistencia, A.id_familia " +
                "from tb_asistenciaEvento A, tb_evento E, tb_familia F, tb_estadoAsistencia EA " +
                "where A.id_evento = E.id_evento and A.id_familia = F.id_familia and A.id_estadoAsistencia = EA.id_estadoAsistencia and A.id_estadoAsistencia = 1" +
                " and A.id_evento =" + idEvento + "", null);
        ArrayList<AsistentesEvento> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                AsistentesEvento asistentesEvento = new AsistentesEvento();
                asistentesEvento.setIdAsistenciaEvento(cursor.getInt(cursor.getColumnIndex("id_asistenciaEvento")));
                asistentesEvento.setFamilia(cursor.getString(cursor.getColumnIndex("familia")));
                asistentesEvento.setIdFamilia(cursor.getInt(cursor.getColumnIndex("id_familia")));
                lista.add(asistentesEvento);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<AsistentesEvento> listarFamiliasAsistieronEvento(int idEvento) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select A.id_asistenciaEvento, E.evento, F.familia, EA.estadoAsistencia, A.id_familia " +
                "from tb_asistenciaEvento A, tb_evento E, tb_familia F, tb_estadoAsistencia EA " +
                "where A.id_evento = E.id_evento and A.id_familia = F.id_familia and A.id_estadoAsistencia = EA.id_estadoAsistencia and A.id_estadoAsistencia = 2" +
                " and A.id_evento =" + idEvento + "", null);
        ArrayList<AsistentesEvento> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                AsistentesEvento asistentesEvento = new AsistentesEvento();
                asistentesEvento.setIdAsistenciaEvento(cursor.getInt(cursor.getColumnIndex("id_asistenciaEvento")));
                asistentesEvento.setFamilia(cursor.getString(cursor.getColumnIndex("familia")));
                asistentesEvento.setIdFamilia(cursor.getInt(cursor.getColumnIndex("id_familia")));
                lista.add(asistentesEvento);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<AsistentesEvento> filtarFamiliasEvento(String texto, int idEvento) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select A.id_asistenciaEvento, E.evento, F.familia, EA.estadoAsistencia, A.id_familia " +
                "from tb_asistenciaEvento A, tb_evento E, tb_familia F, tb_estadoAsistencia EA " +
                "where A.id_evento = E.id_evento and A.id_familia = F.id_familia and A.id_estadoAsistencia = EA.id_estadoAsistencia and A.id_estadoAsistencia = '1'" +
                " and A.id_evento =" + idEvento + "", null);
        ArrayList<AsistentesEvento> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                AsistentesEvento asistentesEvento = new AsistentesEvento();
                asistentesEvento.setIdAsistenciaEvento(cursor.getInt(cursor.getColumnIndex("id_asistenciaEvento")));
                asistentesEvento.setFamilia(cursor.getString(cursor.getColumnIndex("familia")));
                asistentesEvento.setIdFamilia(cursor.getInt(cursor.getColumnIndex("id_familia")));
                lista.add(asistentesEvento);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<Hijos> listarHijos(int idFamilia) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_alumno where id_familia= " + idFamilia + "", null);
        ArrayList<Hijos> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Hijos hijos = new Hijos();
                hijos.setId(cursor.getInt(cursor.getColumnIndex("id_alumno")));
                hijos.setNombre(cursor.getString(cursor.getColumnIndex("alumno")));
                hijos.setNivel(cursor.getString(cursor.getColumnIndex("nivel")));
                hijos.setGrado(cursor.getString(cursor.getColumnIndex("grado")));
                hijos.setSeccion(cursor.getString(cursor.getColumnIndex("seccion")));
                lista.add(hijos);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<Combo> obtenerUsuariosActivos() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_evento where id_evento = '1'", null);
        ArrayList<Combo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                lista.add(new Combo(cursor.getInt(cursor.getColumnIndex("id_evento")), cursor.getString(cursor.getColumnIndex("evento"))));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<Combo> obternerEstadoAsistencia() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_estadoAsistencia", null);
        ArrayList<Combo> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                lista.add(new Combo(cursor.getInt(cursor.getColumnIndex("id_estadoAsistencia")), cursor.getString(cursor.getColumnIndex("estadoAsistencia"))));
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
                lista.add(new Combo(cursor.getInt(cursor.getColumnIndex("id_evento")), cursor.getString(cursor.getColumnIndex("evento"))));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public Evento datosEvento(int idEvento) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select E.id_evento, E.evento, E.fecha, E.alcance, E.descripcion, EV.estadoEvento" +
                " from tb_evento E, tb_estadoEvento EV" +
                " where E.id_estadoEvento = EV.id_estadoEvento and E.id_evento =" + idEvento + " limit 1", null);
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
            } while (cursor.moveToNext());
        }
        return evento;
    }

    public Usuarios obtenerUsuarioClave(String username) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_usuario where username='" + username + "' limit 1", null);

        Usuarios usuarios = null;
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

    public void actualizarAsistencia(int id_estadoAsistencia, int id_asistenciaEvento) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_estadoAsistencia", id_estadoAsistencia);

        db.update("tb_asistenciaEvento", contentValues, "id_asistenciaEvento=" + id_asistenciaEvento, null);
    }

    public int familiasAsitieron(int id_evento) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(id_familia) as 'idFamilia' from tb_asistenciaEvento where id_evento =" + id_evento + "", null);
        int idEvento = 0;
        if (cursor.moveToFirst()) {
            do {
                idEvento = cursor.getInt(cursor.getColumnIndex("idFamilia"));
            } while (cursor.moveToNext());
        }
        return idEvento;
    }
    public int familiasAsitieronPorTipo(int id_evento, int id_estadoAsistencia) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(id_familia) as 'idFamilia' from tb_asistenciaEvento where id_evento =" + id_evento + " and id_estadoAsistencia ="+ id_estadoAsistencia +"", null);
        int idEvento = 0;
        if (cursor.moveToFirst()) {
            do {
                idEvento = cursor.getInt(cursor.getColumnIndex("idFamilia"));
            } while (cursor.moveToNext());
        }
        return idEvento;
    }

}
