package com.example.bai1_day14;

import java.util.List;

public class Pokemon {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String id, name, color;

    public Pokemon(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
