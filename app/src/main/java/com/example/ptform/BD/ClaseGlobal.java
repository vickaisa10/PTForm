package com.example.ptform.BD;

import android.app.Application;

public class ClaseGlobal extends Application {

    //Aqui guardo temporalmente las coordenadas de la ubicacion

    private Double este ;
    private Double  Norte ;
    private String elabora;
    private String Proyecto;
    private String Fecha;

    public Double getEste() {
        return este;
    }

    public void setEste(Double este) {
        this.este = este;
    }

    public Double getNorte() {
        return Norte;
    }

    public void setNorte(Double norte) {
        Norte = norte;
    }

    public String getElabora() {
        return elabora;
    }

    public void setElabora(String elabora) {
        this.elabora = elabora;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String proyecto) {
        Proyecto = proyecto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}



