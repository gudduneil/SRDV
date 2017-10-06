package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 26/9/17.
 */

public class ReasonSetGet {

    String reasonId, en_name, fr_name, image_status, image, deselected_image;
    boolean touch;

    public ReasonSetGet(String reasonId, String en_name, String fr_name, String image_status, String image, String deselected_image, boolean touch) {
        this.reasonId = reasonId;
        this.en_name = en_name;
        this.fr_name = fr_name;
        this.image_status = image_status;
        this.image = image;
        this.deselected_image = deselected_image;
        this.touch = touch;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getFr_name() {
        return fr_name;
    }

    public void setFr_name(String fr_name) {
        this.fr_name = fr_name;
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeselected_image() {
        return deselected_image;
    }

    public void setDeselected_image(String deselected_image) {
        this.deselected_image = deselected_image;
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }
}
