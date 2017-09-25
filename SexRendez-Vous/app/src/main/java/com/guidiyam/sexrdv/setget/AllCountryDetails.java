package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 7/8/17.
 */

public class AllCountryDetails {


    String countryId,countryName,dialCode;

    public AllCountryDetails(String countryId, String countryName,String dialCode) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.dialCode=dialCode;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }
}
