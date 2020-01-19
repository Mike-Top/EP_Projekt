package com.begreen2;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SpoonacularApi {

    // Durch @SerializedName - Annotation wird kein Konstruktor gebraucht
    private List < RezeptsucheResults > results;

    public SpoonacularApi(List < RezeptsucheResults > results) {
        this.results = results;
    }
    public List < RezeptsucheResults > getResults() {
        return results;
    }
}


class RezeptsucheResults{
    @SerializedName("title")
    private String title;
    @SerializedName("readyInMinutes")
    private int readyInMinutes;
    @SerializedName("servings")
    private int servings;
    @SerializedName("image")
    private String image;

    public String getTitle() {
        return title;
    }
    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}



// API Rückgabe REZEPTSUCHE

//{7 items
//        "results":
//        [            <----- ArrayList
//          0:{
//            "id":864592
//            "title":"Black Bean Mushroom Burgers with Chipotle Mayo"
//            "readyInMinutes":25
//            "servings":6
//            "image":"black-bean-mushroom-burgers-with-chipotle-mayo-864592.jpg"
//            "imageUrls":[...]1 item
//        }
//        1:{6 items
//            "id":642539
//            "title":"Falafel Burger"
//            "readyInMinutes":45
//            "servings":4
//            "image":"Falafel-Burger-642539.png"
//            "imageUrls":[...]1 item
//        }
//        2:{...}6 items
//        3:{...}6 items
//        4:{...}6 items
//        5:{...}6 items
//        6:{...}6 items
//        7:{...}6 items
//        ]
//        "baseUri":"https://spoonacular.com/recipeImages/"
//        "offset":0
//        "number":10
//        "totalResults":8
//        "processingTimeMs":43
//        "expires":1579572054818
//}


// Quellen:
// https://www.youtube.com/watch?v=53BsyxwSBJk  Kotlin
// https://www.youtube.com/watch?v=f-kcvxYZrB4
// https://codinginflow.com/tutorials/android/gson/part-1-simple-serialization-deserialization