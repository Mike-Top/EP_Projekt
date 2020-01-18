package com.begreen2;

import com.google.gson.annotations.SerializedName;

public class TestApiData {

    @SerializedName("data")
    private TestApi data;

    public TestApiData(TestApi data) {
        this.data = data;
    }

    public TestApi getData() {
        return data;
    }
}


class TestApi {

    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("first_name")
    private String firstname;
//    @SerializedName("last_name")
//    private String lastname;
//    @SerializedName("avatar")
//    private String avatar;


    public TestApi(int id, String email, String firstname, String lastname, String avatar) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }
}


// API Rückgabe:
//{
//        "data": {
//              "id": 2,
//              "email": "janet.weaver@reqres.in",
//              "first_name": "Janet",
//              "last_name": "Weaver",
//              "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
//        }
//}