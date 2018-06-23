package com.oligark.flashapp.model;

import com.google.gson.annotations.SerializedName;

public class Pet {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String nombre;
    @SerializedName("animal_type")
    private AnimalType animal;
    @SerializedName("breed")
    private String raza;
    @SerializedName("gender")
    private String sexo;
    @SerializedName("pictureUrl")
    private String imageUrl;


    public Pet(String nombre, AnimalType animal, String raza, String sexo, String imageUrl) {
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

    public AnimalType getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalType animal) {
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
