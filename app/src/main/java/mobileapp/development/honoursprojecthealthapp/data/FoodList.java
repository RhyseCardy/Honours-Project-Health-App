package mobileapp.development.honoursprojecthealthapp.data;

public class FoodList {

    private String foodItemName;

    private int foodItemImage;


    //FILE POTENTIALLY NOT NEEDED!!!!!
    //DO NOT WORK IN THIS FILE!!!!!!!!



    public FoodList() {
        super();
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public int getFoodItemImage() {
        return foodItemImage;
    }

    public void setFoodItemImage(int foodItemImage) {
        this.foodItemImage = foodItemImage;
    }

    @Override
    public String toString() {
        return "FoodList{" +
                "foodListFoodName=" + foodItemName +
                ", foodItemImage=" + foodItemImage +
                "}";
    }

}
