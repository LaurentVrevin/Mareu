package fr.laurentvrevin.mareu.model;

import androidx.annotation.NonNull;

public class Rooms {

    private String name;

    public Rooms(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
