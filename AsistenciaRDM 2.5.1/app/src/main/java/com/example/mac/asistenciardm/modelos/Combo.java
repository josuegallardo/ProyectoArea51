package com.example.mac.asistenciardm.modelos;

/**
 * Created by alumno on 3/27/17.
 */

public class Combo {
    int id;

    public Combo(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    String texto;

}
