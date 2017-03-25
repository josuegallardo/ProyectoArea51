package com.example.mac.asistenciardm.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public ArrayList<Usuarios> obtenerUsuarioClave(String username) {
        //public ArrayList<Usuarios> obtener(String username) {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        // Cursor cursor = db.rawQuery("select * from tb_usuario", null);
        Cursor cursor = db.rawQuery("select * from tb_usuario where username='"+ username +"'", null);
        //Cursor cursor = db.rawQuery("select * from tb_usuario where id_usuario='1'", null);
        //Creamos una lista de tipo Datos
        ArrayList<Usuarios> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        if (cursor.moveToFirst()) {
            do {
                //Creamos un objeto donde se almacenaran los datos
                Usuarios usuarios = new Usuarios();
                //Setteamos todos los datos
                usuarios.setId_usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")));
                usuarios.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                usuarios.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                usuarios.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                usuarios.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
                usuarios.setId_tipoUsuario(cursor.getInt(cursor.getColumnIndex("id_tipoUsuario")));
                //Añadimos los datos a la lista
                lista.add(usuarios);
                //Recorre el bucle siempre y cuando haya registros
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public String obtenerUsuarioClave2(String username) {
        //public ArrayList<Usuarios> obtener(String username) {
        //asignamos permiso de solo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();
        //Creamos nuestra sentencia SQL
        // Cursor cursor = db.rawQuery("select * from tb_usuario", null);
        Cursor cursor = db.rawQuery("select * from tb_usuario where username='"+ username +"'", null);
        //Cursor cursor = db.rawQuery("select * from tb_usuario where id_usuario='1'", null);
        //Creamos una lista de tipo Datos
        String lista = new String();

        //ArrayList<Usuarios> lista = new ArrayList<>();
        //Ejecutamos para verificar si es que hay registros
        //if (cursor.moveToFirst()) {
          //  do {
            //    //Creamos un objeto donde se almacenaran los datos
              //  Usuarios usuarios = new Usuarios();
               // //Setteamos todos los datos
                //usuarios.setId_usuario(cursor.getInt(cursor.getColumnIndex("id_usuario")));
                //usuarios.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                //usuarios.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                //usuarios.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                //usuarios.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
                //usuarios.setId_tipoUsuario(cursor.getInt(cursor.getColumnIndex("id_tipoUsuario")));
                //Añadimos los datos a la lista
                //lista.add(usuarios);
                //Recorre el bucle siempre y cuando haya registros
            //} while (cursor.moveToNext());
        //}

        return lista;
    }
    public Usuarios obtenerUsuarioClave3(String username) {
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
