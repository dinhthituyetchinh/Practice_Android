package model;

import java.util.List;

public class Pokemon {
    private String id, name, image;
    private List<String> types;
    private String backgroundColor;
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> type) {
        this.types = type;
    }

    public Pokemon() {
    }

    public Pokemon(String id, String name, String image, List<String> types, String backgroundColor) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.types = types;
        this.backgroundColor = backgroundColor;
    }
}
