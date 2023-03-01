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

    public void setUserFoodItemName(String userFoodItemName) {
        this.userFoodItemName = userFoodItemName;
    }

    public int getUserFoodItemImage() {
        return userFoodItemImage;
    }

    public void setUserFoodItemImage(int userFoodItemImage) {
        this.userFoodItemImage = userFoodItemImage;
    }

    @Override
    public String toString() {
        return "UserFoodList{" +
                "userFoodListFoodName=" + userFoodItemName +
                ", userFoodItemImage=" + userFoodItemImage +
                "}";
    }

}
