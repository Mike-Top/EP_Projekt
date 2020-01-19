package com.begreen2;

import java.io.Serializable;
import java.util.ArrayList;

public class Produktdaten implements Serializable {

//    ArrayList<String> list;
//    ArrayList<String> dateList;

//    private ArrayList<ArrayList> listNameAndDate;

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
