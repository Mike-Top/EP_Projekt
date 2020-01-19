package com.begreen2;

import java.io.Serializable;

public class Produktdaten implements Serializable {

    private String prduktName;
    private String Datum;

    public Produktdaten(String prduktName, String datum) {
        this.prduktName = prduktName;
        Datum = datum;
    }

    public String getPrduktName() {
        return prduktName;
    }

    public String getDatum() {
        return Datum;
    }

    public void setPrduktName(String prduktName) {
        this.prduktName = prduktName;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }
}
