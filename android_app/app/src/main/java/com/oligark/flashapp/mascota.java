package com.oligark.flashapp;

public class mascota {
    private int id;
    private String nombre;
    private String animal;
    private String raza;
    private String sexo;

    public mascota(String nombre, String animal, String raza, String sexo, String image_url) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.sexo = sexo;
        this.image_url = image_url;
    }

    public int getId(){
        return id;
    }
    public mascota(String nombre) {
        this.nombre = nombre;
    }

    private String image_url;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
