package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 22/9/17.
 */

public class PartnerListing {
    String name,image;

    public PartnerListing(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
