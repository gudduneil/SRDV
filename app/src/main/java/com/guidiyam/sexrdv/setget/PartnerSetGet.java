package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 19/7/17.
 */

public class PartnerSetGet {

    String id;
    String image;
    String name;
    boolean select;
    String email;

    public PartnerSetGet(String id, String image, String name, String email, String number, boolean select) {
        this.id = id;
        this.image = image;
        this.name = name;

        this.email = email;
        this.number = number;
        this.select = select;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    String number;

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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
