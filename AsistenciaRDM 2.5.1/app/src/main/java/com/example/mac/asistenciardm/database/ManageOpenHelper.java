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
        db.execSQL("create table tb_estadoEvento (id_estadoEvento integer primary key," +
                " estadoEvento varchar(100), descripcion varchar(200)) ");
        db.execSQL("create table tb_evento (id_evento integer primary key," +
                "evento varchar(200), fecha varchar(20), alcance varchar(100), descripcion varchar(300), id_estadoEvento integer)");
        db.execSQL("create table tb_familia (id_familia integer primary key," +
                " familia varchar (100), nomPapa varchar(200), nomMama varchar(200), nomApoderado varchar(200))");
        db.execSQL("create table tb_alumno (id_alumno integer primary key," +
                " alumno varchar(200), nivel varchar(100), grado varchar(100), seccion varchar(100), id_familia integer)");
        db.execSQL("create table tb_estadoAsistencia (id_estadoAsistencia integer primary key," +
                " estadoAsistencia varchar(100))");
        db.execSQL("create table tb_asistenciaEvento (id_asistenciaEvento integer primary key," +
                "id_evento integer, id_familia integer,  id_estadoAsistencia)");


        db.execSQL("insert into tb_estadoAsistencia(id_estadoAsistencia, estadoAsistencia) " +
                "values ('1','invitado')");
        db.execSQL("insert into tb_estadoAsistencia(id_estadoAsistencia, estadoAsistencia) " +
                "values ('2','Asistio')");
        db.execSQL("insert into tb_estadoAsistencia(id_estadoAsistencia, estadoAsistencia) " +
                "values ('3','No asistio')");

        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('1','1','1','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('2','1','2','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('3','1','3','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('4','1','4','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('5','1','5','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('6','1','6','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('7','1','7','1')");
        db.execSQL("insert into tb_asistenciaEvento (id_asistenciaEvento, id_evento, id_familia, id_estadoAsistencia) " +
                "values ('8','1','8','1')");


        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('1', 'Familia 1', 'Papa 1', 'Mama 1', 'Apoderado 1')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('2', 'Familia 2', 'Papa 2', 'Mama 2', 'Apoderado 2')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('3', 'Familia 3', 'Papa 3', 'Mama 3', 'Apoderado 3')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('4', 'Familia 4', 'Papa 4', 'Mama 4', 'Apoderado 4')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('5', 'Familia 5', 'Papa 5', 'Mama 5', 'Apoderado 5')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('6', 'Familia 6', 'Papa 6', 'Mama 6', 'Apoderado 6')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('7', 'Familia 7', 'Papa 7', 'Mama 7', 'Apoderado 7')");
        db.execSQL("insert into tb_familia (id_familia, familia, nomPapa, nomMama, nomApoderado)" +
                "values ('8', 'Familia 8', 'Papa 8', 'Mama 8', 'Apoderado 8')");

        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('1','Alumno 1','P','1','A','1')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('2','Alumno 2','P','2','A','2')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('3','Alumno 3','P','3','A','3')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('4','Alumno 4','P','4','A','4')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('5','Alumno 5','I','3','A','5')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('6','Alumno 6','I','4','A','6')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('7','Alumno 7','S','1','A','7')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('8','Alumno 8','S','1','A','8')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('9','Alumno 9','S','2','A','1')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('10','Alumno 10','P','1','A','4')");
        db.execSQL("insert into tb_alumno (id_alumno, alumno, nivel, grado, seccion, id_familia)" +
                "values ('11','Alumno 11','P','6','A','5')");


        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)" +
                "values ('1','Activo','Evento esta activo')");
        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)" +
                "values ('2','Inactivo','Evento esta inactivo')");
        db.execSQL("insert into tb_estadoEvento (id_estadoEvento, estadoEvento, descripcion)" +
                "values ('3','Cerrado','Evento esta cerrado')");

        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)" +
                "values ('1','Evento 1','2017-04-03','Todos','Evento','1')");
        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)" +
                "values ('2','Evento 2','2017-04-03','Todos','Evento','2')");
        db.execSQL("insert into tb_evento (id_evento, evento, fecha, alcance, descripcion, id_estadoEvento)" +
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
