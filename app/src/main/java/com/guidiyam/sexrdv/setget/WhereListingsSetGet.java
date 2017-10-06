package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 25/9/17.
 */

public class WhereListingsSetGet {
    String name,image;

    public WhereListingsSetGet(String name, String image) {
        this.name = name;
        this.image = image;
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
}
