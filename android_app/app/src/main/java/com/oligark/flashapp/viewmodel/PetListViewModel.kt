package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.model.Pet
import java.util.ArrayList

class PetListViewModel : ViewModel() {
    var petList = MutableLiveData<List<Pet>>()

    init {
        var pets = ArrayList<Pet>()
        pets.add(Pet("nombre", "animal", "raza", "sexo", "imgurl"))
        pets.add(Pet("nombre2", "animal2", "raza2", "sexo2", "imgurl2"))
        pets.add(Pet("nombre3", "animal3", "raza3", "sexo3", "imgurl3"))
        petList.value = pets
    }
}
