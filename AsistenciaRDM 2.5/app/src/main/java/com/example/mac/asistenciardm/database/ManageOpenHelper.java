package com.example.mac.asistenciardm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MAC on 17/03/17.
 */

public class ManageOpenHelper extends SQLiteOpenHelper {
    public static final String EMPTY = "";

    public ManageOpenHelper(Context context) {
        //1 = versión 1 de nuestra base de datos
        super(context, "AsistenciaRDM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_usuario (id_usuario integer primary key autoincrement, " +
                "username varchar(100), " +
                "password varchar(100), " +
                "nombre varchar(100), apellidos varchar(100), id_tipoUsuario integer)");
        db.execSQL("create table tb_tipoUsuario (id_tipoUsuario integer primary key , " +
                "tipoUsuario varchar(100), descripcion varchar(200))");
        db.execSQL("create table tb_estadoEvento (id_estadoEvento integer primary key,"+
                " estadoEvento varchar(100), descripcion varchar(200)) ");
        db.execSQL("create table tb_evento (id_evento integer primary key,"+
                "evento varchar(200), fecha varchar(20), alcance varchar(100), descripcion varchar(300), id_estadoEvento integer)");

        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)"+
                "values ('1','Activo','Evento esta activo')");
        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)"+
                "values ('2','Inactivo','Evento esta inactivo')");
        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)"+
                "values ('3','Cerrado','Evento esta cerrado')");

        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)"+
                "values ('1','Evento 1','2017-04-03','Todos','Evento','1')");
        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)"+
                "values ('2','Evento 2','2017-04-03','Todos','Evento','2')");
        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)"+
                "values ('3','Evento 3','2017-04-03','Todos','Evento','3')");

        db.execSQL("insert into tb_tipoUsuario (id_tipoUsuario, tipoUsuario, descripcion) " +
                "values ('1','Administrador','Acceso total')");
        db.execSQL("insert into tb_tipoUsuario (id_tipoUsuario, tipoUsuario, descripcion) " +
                "values ('2','Registrador','registra los sucesos')");
        db.execSQL("insert into tb_tipoUsuario (id_tipoUsuario, tipoUsuario, descripcion) " +
                "values ('3','Supervisor','Solo ve, no puede modificar nada')");

        db.execSQL("insert into tb_usuario (username, password, nombre, apellidos, id_tipoUsuario) " +
                "values('admin', '123456', 'Administrador', 'Sistema', '1')");
        db.execSQL("insert into tb_usuario (username, password, nombre, apellidos, id_tipoUsuario) " +
                "values('registrador', '123456', 'Registrador', 'Sistema', '2')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2) {
            //Sentencias a realizar
        }
    }
}
