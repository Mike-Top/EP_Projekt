package com.begreen2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpoonacularApiIngredients {


    private List < RezeptsucheIngredients > rezeptsucheIngredientsResults;


    public SpoonacularApiIngredients( List < RezeptsucheIngredients > results ) {
        this.rezeptsucheIngredientsResults = results;
    }

    public List < RezeptsucheIngredients > getResults() {
        return rezeptsucheIngredientsResults;
    }
}


class RezeptsucheIngredients {
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("usedIngredientCount")
    private int usedIngredientCount;

//    private List<UsedIngredients> usedIngredients;
//    private List<MissedIngredients> missedIngredients;
//
//    public RezeptsucheIngredients(List<UsedIngredients> usedIngredients, List<MissedIngredients> missedIngredients) {
//        this.usedIngredients = usedIngredients;
//        this.missedIngredients = missedIngredients;
//    }

    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }
//    public List<UsedIngredients> getUsedIngredients() {
//        return usedIngredients;
//    }
//    public List<MissedIngredients> getMissedIngredients() {
//        return missedIngredients;
//    }
}


class UsedIngredients {
    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private int amount;

    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
}


class MissedIngredients {
    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private int amount;

    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
}


