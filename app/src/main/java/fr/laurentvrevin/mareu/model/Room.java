package fr.laurentvrevin.mareu.model;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Room {

    private String name;
    private String avatarColor;

    public Room(String name, String avatarColor){

        this.name = name;
        this.avatarColor = avatarColor;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
