package com.example.mac.asistenciardm.modelos;

/**
 * Created by JOSUE on 24/04/2017.
 */

public class Familia {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Familia(int id, String familia) {
        this.id = id;
        this.familia = familia;
    }

    int id;
    String familia;
}
