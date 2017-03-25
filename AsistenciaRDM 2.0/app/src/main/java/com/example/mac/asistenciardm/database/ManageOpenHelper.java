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
