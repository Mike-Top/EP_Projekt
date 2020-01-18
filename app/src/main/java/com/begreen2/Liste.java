package com.begreen2;

import java.io.Serializable;
import java.util.ArrayList;

public class Liste implements Serializable {

//    ArrayList<String> list;
//    ArrayList<String> dateList;

    private ArrayList<ArrayList> listNameAndDate;


    public Liste(){
        listNameAndDate = new ArrayList<ArrayList>();
    }

    public ArrayList<ArrayList> getListNameAndDate() {
        return listNameAndDate;
    }


//    public Liste(){
//        list = new ArrayList<>();
//        dateList = new ArrayList<>();
//    }

//    public void setList(ArrayList<String> list) {
//        this.list = list;
//    }
//
//    public void setDate(ArrayList<String> date) {
//        this.dateList = date;
//    }
//
//    public ArrayList<String> getList() {
//        return list;
//    }
//
//    public ArrayList<String> getDate() {
//        return dateList;
//    }
}
