package com.oligark.flashapp.model;

public class Service {
    private int id;
    private String nombreServicio;

    public Service(int id, String nombreServicio) {
        this.id = id;
        this.setNombreServicio(nombreServicio);
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
