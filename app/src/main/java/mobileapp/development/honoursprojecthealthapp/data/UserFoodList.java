package mobileapp.development.honoursprojecthealthapp.data;

public class UserFoodList {

    private String userFoodItemName;

    private int userFoodItemImage;


    public UserFoodList() {
        super();
    }

    public String getUserFoodItemName() {
        return userFoodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.userFoodItemName = userFoodItemName;
    }

    public int getFoodItemImage() {
        return userFoodItemImage;
    }

    public void setFoodItemImage(int foodItemImage) {
        this.userFoodItemImage = userFoodItemImage;
    }

    @Override
    public String toString() {
        return "FoodList{" +
                "foodListFoodName=" + userFoodItemName +
                ", gameInfoPlatform=" + userFoodItemImage +
                "}";
    }

}
