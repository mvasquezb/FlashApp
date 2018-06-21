package com.oligark.flashapp.model;

public class Pet {
    private int id;
    private String nombre;
    private String animal;
    private String raza;
    private String sexo;
    private String imageUrl;


    public Pet(String nombre, String animal, String raza, String sexo, String imageUrl) {
        this.nombre = nombre;
        this.animal = animal;
        this.raza = raza;
        this.sexo = sexo;
        this.imageUrl = imageUrl;
    }
    public int getId(){
        return id;
    }

    public Pet(String nombre) {
        this.nombre = nombre;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
