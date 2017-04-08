package com.example.mac.asistenciardm.modelos;

/**
 * Created by JOSUE on 5/04/2017.
 */

public class AsistentesEvento {
    private int idAsistenciaEvento;
    private String evento;
    private int idFamilia;

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public int getIdAsistenciaEvento() {
        return idAsistenciaEvento;
    }

    public void setIdAsistenciaEvento(int idAsistenciaEvento) {
        this.idAsistenciaEvento = idAsistenciaEvento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    private String familia;
    private String estadoAsistencia;

}
