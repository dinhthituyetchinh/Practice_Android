package com.example.day13;

import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;

import androidx.databinding.BindingAdapter;

import java.util.List;

public class Pokemon {
    // Lưu ý tên biến phải đặt trùng với tên để trên Api
    private String id, name, image;
    private List<String> types;

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

    public Pokemon(String id, String name, String img, List<String> types) {
        this.id = id;
        this.name = name;
        this.image = img;
        this.types = types;
    }
    private Drawable background;

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }
    @BindingAdapter("backgroundResource")
    public static void setBackgroundResource(RelativeLayout layout, Drawable backgroundResource) {
        if (backgroundResource != null) {
            layout.setBackground(backgroundResource);
        }
    }
}

