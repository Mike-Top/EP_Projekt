package com.begreen2;

import java.util.ArrayList;

public class Liste {

    ArrayList<String> list;
    ArrayList<String> dateList;

    public Liste(){
        list = new ArrayList<>();
        dateList = new ArrayList<>();
    }


    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public void setDate(ArrayList<String> date) {
        this.dateList = date;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public ArrayList<String> getDate() {
        return dateList;
    }
}
