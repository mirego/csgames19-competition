package com.csgames.brock;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Drink {
    String id = null;
    String label = null;
    String type = null;
    String color = null;
    Double opacity = null;
    String imageURL = null;
    ArrayList<String> sprites = null;

    @NonNull
    @Override
    public String toString() {
        return id + label + type + color + opacity + imageURL + sprites;
    }
}

